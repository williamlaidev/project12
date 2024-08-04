package interface_adapter.data;

import entity.Review;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ReviewDataAdapter {
    Review adaptToReview(ResultSet rs) throws SQLException;
}
