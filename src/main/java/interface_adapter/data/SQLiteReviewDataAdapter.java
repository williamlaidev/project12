package interface_adapter.data;

import entity.review.Review;
import entity.review.ReviewGeminiFactory;
import entity.review.ReviewUserFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implements ReviewDataAdapter for SQLite database.
 */
public class SQLiteReviewDataAdapter implements ReviewDataAdapter {

    private final ReviewUserFactory userReviewFactory = new ReviewUserFactory();
    private final ReviewGeminiFactory geminiReviewFactory = new ReviewGeminiFactory();

    /**
     * Creates a Review from a ResultSet row.
     *
     * @param rs the ResultSet with review data
     * @return a Review object
     * @throws SQLException if an SQL error occurs
     */
    @Override
    public Review adaptToReview(ResultSet rs) throws SQLException {
        String restaurantId = rs.getString("restaurantId");
        String author = rs.getString("author");
        String content = rs.getString("content");
        boolean isSummarized = rs.getBoolean("isSummarized");

        if (isSummarized) {
            return geminiReviewFactory.createReview(restaurantId, content);
        } else {
            return userReviewFactory.createReview(restaurantId, author, content);
        }
    }
}
