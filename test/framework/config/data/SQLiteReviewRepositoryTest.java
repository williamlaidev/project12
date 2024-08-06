package framework.data;

import entity.review.Review;
import entity.operation_result.OperationResult;
import entity.operation_result.OperationResultFailureFactory;
import entity.operation_result.OperationResultSuccessFactory;
import interface_adapter.data.SQLiteReviewDataAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SQLiteReviewRepositoryTest {

    private SQLiteReviewRepository repository;
    private DatabaseConfig databaseConfig;
    private SQLiteReviewDataAdapter dataAdapter;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    @BeforeEach
    public void setUp() throws Exception {
        databaseConfig = mock(DatabaseConfig.class);
        dataAdapter = mock(SQLiteReviewDataAdapter.class);
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        statement = mock(Statement.class);
        resultSet = mock(ResultSet.class);

        when(databaseConfig.connect()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.createStatement()).thenReturn(statement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);

        repository = new SQLiteReviewRepository(databaseConfig, dataAdapter, new OperationResultSuccessFactory(), new OperationResultFailureFactory());
    }

    @Test
    public void testAddReview() throws Exception {
        Review review = new Review("1", "Kera", "Good food", false);

        when(preparedStatement.executeUpdate()).thenReturn(1);

        OperationResult result = repository.add(review);
        assertEquals("Review saved successfully", result.getMessage());
    }

    @Test
    public void testAddReviewFailure() throws Exception {
        Review review = new Review("1", "Kera", "Good food", false);

        when(preparedStatement.executeUpdate()).thenThrow(new SQLException("Error"));

        OperationResult result = repository.add(review);
        assertEquals("Error saving review", result.getMessage());
    }

    @Test
    public void testFindUserReviews() throws Exception {
        Review review = new Review("1", "Kera", "Good food", false);

        when(resultSet.next()).thenReturn(true, false);
        when(dataAdapter.adaptToReview(resultSet)).thenReturn(review);

        List<Review> result = repository.findUserReviews("1");
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    public void testFindSummarizedReview() throws Exception {
        Review review = new Review("1", "Kera", "Good food", true);

        when(resultSet.next()).thenReturn(true, false);
        when(dataAdapter.adaptToReview(resultSet)).thenReturn(review);

        Optional<Review> result = repository.findSummarizedReview("1");
        assertTrue(result.isPresent());
        assertEquals("1", result.get().getRestaurantId());
    }

    @Test
    public void testFindSummarizedReviewNotFound() throws Exception {
        when(resultSet.next()).thenReturn(false);

        Optional<Review> result = repository.findSummarizedReview("1");
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindAllReviews() throws Exception {
        Review review = new Review("1", "Kera", "Good food", true);

        when(resultSet.next()).thenReturn(true, false);
        when(dataAdapter.adaptToReview(resultSet)).thenReturn(review);

        List<Review> result = repository.findAll();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    public void testUpdateReview() throws Exception {
        Review review = new Review("1", "Kera", "Good food", false);

        when(resultSet.next()).thenReturn(true);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        OperationResult result = repository.update(review);
        assertEquals("Review updated successfully", result.getMessage());
    }

    @Test
    public void testUpdateReviewFailure() throws Exception {
        Review review = new Review("1", "Kera", "Good food", false);

        when(resultSet.next()).thenThrow(new SQLException("Error"));

        OperationResult result = repository.update(review);
        assertEquals("Error updating review", result.getMessage());
    }

    @Test
    public void testDeleteUserById() throws Exception {
        when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean result = repository.deleteUserById("1");
        assertTrue(result);
    }

    @Test
    public void testDeleteUserByIdFailure() throws Exception {
        when(preparedStatement.executeUpdate()).thenThrow(new SQLException("Error"));

        boolean result = repository.deleteUserById("1");
        assertFalse(result);
    }

    @Test
    public void testDeleteSummarizedById() throws Exception {
        when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean result = repository.deleteSummarizedById("1");
        assertTrue(result);
    }

    @Test
    public void testDeleteSummarizedByIdFailure() throws Exception {
        when(preparedStatement.executeUpdate()).thenThrow(new SQLException("Error"));

        boolean result = repository.deleteSummarizedById("1");
        assertFalse(result);
    }

    @Test
    public void testDeleteAllById() throws Exception {
        when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean result = repository.deleteAllById("1");
        assertTrue(result);
    }

    @Test
    public void testDeleteAllByIdFailure() throws Exception {
        when(preparedStatement.executeUpdate()).thenThrow(new SQLException("Error"));

        boolean result = repository.deleteAllById("1");
        assertFalse(result);
    }

    @Test
    public void testClearAllReviews() throws Exception {
        when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean result = repository.clearAll();
        assertTrue(result);
    }

    @Test
    public void testClearAllReviewsFailure() throws Exception {
        when(preparedStatement.executeUpdate()).thenThrow(new SQLException("Error"));

        boolean result = repository.clearAll();
        assertFalse(result);
    }
}
