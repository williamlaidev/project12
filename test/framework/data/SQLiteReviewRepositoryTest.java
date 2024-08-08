package framework.data;

import entity.review.Review;
import entity.operation_result.OperationResult;
import entity.operation_result.OperationResultSuccessFactory;
import entity.operation_result.OperationResultFailureFactory;
import interface_adapter.data.SQLiteReviewDataAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SQLiteReviewRepositoryTest {

    @Mock
    private DatabaseConfig databaseConfig;

    @Mock
    private SQLiteReviewDataAdapter dataAdapter;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private Statement statement;

    @Mock
    private ResultSet resultSet;

    private SQLiteReviewRepository repository;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(databaseConfig.connect()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);
        repository = new SQLiteReviewRepository(databaseConfig, dataAdapter, new OperationResultSuccessFactory(), new OperationResultFailureFactory());
    }

    @Test
    public void testFindUserReviews() throws SQLException {
        Review review = new Review("1", "Kera", "User review content.", false);

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(dataAdapter.adaptToReview(resultSet)).thenReturn(review);

        List<Review> result = repository.findUserReviews("1");

        verify(preparedStatement, times(1)).setString(1, "1");
        verify(preparedStatement, times(1)).executeQuery();

        assertEquals(1, result.size());
        assertEquals(review, result.get(0));
    }


    @Test
    public void testDeleteUserById() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean result = repository.deleteUserById("1");

        verify(preparedStatement, times(1)).setString(1, "1");
        verify(preparedStatement, times(1)).executeUpdate();

        assertTrue(result);
    }

    @Test
    public void testDeleteSummarizedById() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean result = repository.deleteSummarizedById("1");

        verify(preparedStatement, times(1)).setString(1, "1");
        verify(preparedStatement, times(1)).executeUpdate();

        assertTrue(result);
    }

    @Test
    public void testDeleteAllById() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean result = repository.deleteAllById("1");

        verify(preparedStatement, times(1)).setString(1, "1");
        verify(preparedStatement, times(1)).executeUpdate();

        assertTrue(result);
    }

}
