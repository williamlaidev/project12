package framework.data;

import domain.ReviewRepository;
import entity.review.Review;
import entity.operation_result.OperationResult;
import entity.operation_result.OperationResultSuccessFactory;
import entity.operation_result.OperationResultFailureFactory;
import interface_adapter.data.SQLiteReviewDataAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SQLiteReviewRepository implements ReviewRepository {
    private static final Logger logger = LoggerFactory.getLogger(SQLiteReviewRepository.class);

    private final DatabaseConfig databaseConnection;
    private final SQLiteReviewDataAdapter dataAdapter;
    private final OperationResultSuccessFactory successFactory;
    private final OperationResultFailureFactory failureFactory;

    public SQLiteReviewRepository(DatabaseConfig databaseConnection,
                                  SQLiteReviewDataAdapter dataAdapter,
                                  OperationResultSuccessFactory successFactory,
                                  OperationResultFailureFactory failureFactory) {
        this.databaseConnection = databaseConnection;
        this.dataAdapter = dataAdapter;
        this.successFactory = successFactory;
        this.failureFactory = failureFactory;
        initializeDatabase();
    }

    private void initializeDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS reviews ("
                + "restaurantId TEXT NOT NULL,"
                + "author TEXT NOT NULL,"
                + "content TEXT NOT NULL,"
                + "isSummarized BOOLEAN NOT NULL,"
                + "PRIMARY KEY (restaurantId, author)"
                + ");";
        try (Connection conn = databaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            logger.error("Error creating reviews table", e);
        }
    }

    @Override
    public OperationResult add(Review review) {
        return save(review);
    }

    @Override
    public List<Review> findUserReviews(String restaurantId) {
        String sql = "SELECT * FROM reviews WHERE restaurantId = ? AND isSummarized = FALSE";
        return executeQuery(sql, restaurantId, false);
    }

    @Override
    public Optional<Review> findSummarizedReview(String restaurantId) {
        String sql = "SELECT * FROM reviews WHERE restaurantId = ? AND isSummarized = TRUE";
        List<Review> reviews = executeQuery(sql, restaurantId, true);
        return reviews.isEmpty() ? Optional.empty() : Optional.of(reviews.get(0));
    }

    @Override
    public List<Review> findAll() {
        String sql = "SELECT * FROM reviews";
        return executeQuery(sql, null, true);
    }

    @Override
    public OperationResult save(Review review) {
        String sql = "INSERT OR REPLACE INTO reviews (restaurantId, author, content, isSummarized) VALUES (?, ?, ?, ?)";
        try (Connection conn = databaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, review.getRestaurantId());
            pstmt.setString(2, review.getAuthor());
            pstmt.setString(3, review.getContent());
            pstmt.setBoolean(4, review.isSummarized());
            pstmt.executeUpdate();
            return successFactory.create("Review saved successfully");
        } catch (SQLException e) {
            logger.error("Error saving review", e);
            return failureFactory.create(e.getMessage());
        }
    }

    @Override
    public OperationResult update(Review review) {
        String checkSql = "SELECT content FROM reviews WHERE restaurantId = ? AND author = ?";
        String updateSql = "UPDATE reviews SET content = ? WHERE restaurantId = ? AND author = ?";

        try (Connection conn = databaseConnection.connect();
             PreparedStatement checkPstmt = conn.prepareStatement(checkSql)) {
            checkPstmt.setString(1, review.getRestaurantId());
            checkPstmt.setString(2, review.getAuthor());
            ResultSet rs = checkPstmt.executeQuery();

            if (rs.next()) {
                try (PreparedStatement updatePstmt = conn.prepareStatement(updateSql)) {
                    updatePstmt.setString(1, review.getContent());
                    updatePstmt.setString(2, review.getRestaurantId());
                    updatePstmt.setString(3, review.getAuthor());
                    int affectedRows = updatePstmt.executeUpdate();
                    return affectedRows == 1
                            ? successFactory.create("Review updated successfully")
                            : failureFactory.create("Review update failed");
                }
            } else {
                return failureFactory.create("Review does not exist");
            }
        } catch (SQLException e) {
            logger.error("Error updating review", e);
            return failureFactory.create(e.getMessage());
        }
    }

    @Override
    public boolean deleteUserById(String restaurantId) {
        return deleteByCriteria("isSummarized = FALSE", restaurantId);
    }

    @Override
    public boolean deleteSummarizedById(String restaurantId) {
        return deleteByCriteria("isSummarized = TRUE", restaurantId);
    }

    @Override
    public boolean deleteAllById(String restaurantId) {
        return deleteByCriteria(null, restaurantId);
    }

    @Override
    public boolean clearAll() {
        String sql = "DELETE FROM reviews";
        try (Connection conn = databaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            logger.error("Error clearing all reviews", e);
            return false;
        }
    }

    private boolean deleteByCriteria(String criteria, String restaurantId) {
        String sql = "DELETE FROM reviews WHERE restaurantId = ?" +
                (criteria != null ? " AND " + criteria : "");
        try (Connection conn = databaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, restaurantId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            logger.error("Error deleting reviews", e);
            return false;
        }
    }

    private List<Review> executeQuery(String sql, String restaurantId, boolean includeSummarized) {
        List<Review> reviews = new ArrayList<>();
        try (Connection conn = databaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (restaurantId != null) {
                pstmt.setString(1, restaurantId);
            }
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                reviews.add(dataAdapter.adaptToReview(rs));
            }
        } catch (SQLException e) {
            logger.error("Error executing query", e);
        }
        return reviews;
    }
}
