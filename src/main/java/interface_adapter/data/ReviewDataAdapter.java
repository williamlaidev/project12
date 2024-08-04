package interface_adapter.data;

import entity.review.Review;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ReviewDataAdapter {
    Review adaptToReview(ResultSet rs) throws SQLException;
}
