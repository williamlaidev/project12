package interface_adapter.data;

import entity.review.Review;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Adapts SQL ResultSet to a Review entity.
 */
public interface ReviewDataAdapter {
    /**
     * Converts a ResultSet to a Review.
     * @param rs The ResultSet containing review data.
     * @return The Review entity.
     * @throws SQLException if an SQL error occurs.
     */
    Review adaptToReview(ResultSet rs) throws SQLException;
}
