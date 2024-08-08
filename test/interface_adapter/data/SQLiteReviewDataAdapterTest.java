package interface_adapter.data;

import entity.review.Review;
import entity.review.ReviewGeminiFactory;
import entity.review.ReviewUserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SQLiteReviewDataAdapterTest {

    private SQLiteReviewDataAdapter adapter;

    @BeforeEach
    public void setUp() {
        ReviewUserFactory userReviewFactory = new ReviewUserFactory();
        ReviewGeminiFactory geminiReviewFactory = new ReviewGeminiFactory();
        adapter = new SQLiteReviewDataAdapter();
    }

    @Test
    public void testAdaptToReviewWithUserReview() throws SQLException {
        ResultSet rs = mock(ResultSet.class);
        when(rs.getString("restaurantId")).thenReturn("1");
        when(rs.getString("author")).thenReturn("Kera Kim");
        when(rs.getString("content")).thenReturn("Great food!");
        when(rs.getBoolean("isSummarized")).thenReturn(false);

        Review review = adapter.adaptToReview(rs);

        assertNotNull(review);
        assertEquals("1", review.getRestaurantId());
        assertEquals("Kera Kim", review.getAuthor());
        assertEquals("Great food!", review.getContent());
        assertFalse(review.isSummarized());
    }

    @Test
    public void testAdaptToReviewWithSummarizedReview() throws SQLException {
        ResultSet rs = mock(ResultSet.class);
        when(rs.getString("restaurantId")).thenReturn("1");
        when(rs.getString("author")).thenReturn(null);
        when(rs.getString("content")).thenReturn("Great food!");
        when(rs.getBoolean("isSummarized")).thenReturn(true);

        Review review = adapter.adaptToReview(rs);

        assertNotNull(review);
        assertEquals("1", review.getRestaurantId());
        assertEquals("Gemini", review.getAuthor());
        assertEquals("Great food!", review.getContent());
        assertTrue(review.isSummarized());
    }

    @Test
    public void testAdaptToReviewWithSQLException() throws SQLException {
        ResultSet rs = mock(ResultSet.class);
        when(rs.getString(anyString())).thenThrow(new SQLException("Test SQL exception"));

        assertThrows(SQLException.class, () -> adapter.adaptToReview(rs));
    }
}
