package interface_adapter.data;

import entity.Review;
import entity.GeminiReviewFactory;
import entity.UserReviewFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLiteReviewDataAdapter implements ReviewDataAdapter {

    private final UserReviewFactory userReviewFactory = new UserReviewFactory();
    private final GeminiReviewFactory geminiReviewFactory = new GeminiReviewFactory();

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
