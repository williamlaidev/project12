package interface_adapter.data;

import domain.ReviewRepository;
import entity.Review;
import entity.ReviewFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implements the {@link ReviewRepository} interface using an SQLite database.
 * Provides methods for performing CRUD (Create, Read, Update, Delete) operations on reviews.
 * Manages an SQLite database to store and retrieve review data.
 */
public class SQLiteReviewRepository implements ReviewRepository {
    private static final String DB_URL = "jdbc:sqlite:app_database.sqlite";
    private static final Logger logger = LoggerFactory.getLogger(SQLiteReviewRepository.class);

    /**
     * Initializes the repository by creating the reviews table if it does not already exist.
     * The table schema includes columns for restaurant ID, author, content, and summarized flag.
     */
    public SQLiteReviewRepository() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "CREATE TABLE IF NOT EXISTS reviews ("
                    + "restaurantId TEXT NOT NULL,"
                    + "author TEXT NOT NULL,"
                    + "content TEXT NOT NULL,"
                    + "isSummarized BOOLEAN NOT NULL,"
                    + "PRIMARY KEY (restaurantId, author)"
                    + ");";
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            logger.error("Error creating reviews table", e);
        }
    }

    /**
     * Adds a new review to the repository. This method uses the {@link #save(Review)} method
     * to persist the review in the database.
     *
     * @param review The review to add.
     * @return true if the review was added successfully, false otherwise.
     */
    @Override
    public boolean add(Review review) {
        return save(review); // Use save method and return its result
    }

    /**
     * Finds all user reviews for a specific restaurant.
     *
     * @param restaurantId The ID of the restaurant.
     * @return An {@link Optional} containing a list of user reviews if found, otherwise empty.
     */
    @Override
    public Optional<List<Review>> findUserReviews(String restaurantId) {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM reviews WHERE restaurantId = ? AND isSummarized = FALSE";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, restaurantId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                reviews.add(mapResultSetToReview(rs));
            }
            return reviews.isEmpty() ? Optional.empty() : Optional.of(reviews);
        } catch (SQLException e) {
            logger.error("Error finding user reviews", e);
            return Optional.empty();
        }
    }

    /**
     * Finds the summarized review for a specific restaurant.
     *
     * @param restaurantId The ID of the restaurant.
     * @return An {@link Optional} containing the summarized review if found, otherwise empty.
     */
    @Override
    public Optional<Review> findSummarizedReview(String restaurantId) {
        String sql = "SELECT * FROM reviews WHERE restaurantId = ? AND isSummarized = TRUE";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, restaurantId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapResultSetToReview(rs));
            }
        } catch (SQLException e) {
            logger.error("Error finding summarized review", e);
        }
        return Optional.empty();
    }

    /**
     * Finds all reviews in the repository.
     *
     * @return An {@link Optional} containing a list of all reviews if found, otherwise empty.
     */
    @Override
    public Optional<List<Review>> findAll() {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM reviews";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                reviews.add(mapResultSetToReview(rs));
            }
            return Optional.of(reviews);
        } catch (SQLException e) {
            logger.error("Error finding all reviews", e);
        }
        return Optional.empty();
    }

    /**
     * Saves or updates a review in the repository.
     *
     * @param review The review to save or update.
     * @return true if the operation was successful, false otherwise.
     */
    @Override
    public boolean save(Review review) {
        String sql = "INSERT OR REPLACE INTO reviews (restaurantId, author, content, isSummarized) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, review.getRestaurantId());
            pstmt.setString(2, review.getAuthor());
            pstmt.setString(3, review.getContent());
            pstmt.setBoolean(4, review.isSummarized());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error("Error saving review", e);
            return false;
        }
    }

    /**
     * Updates an existing review in the repository.
     *
     * @param review The review to update.
     * @return true if the review was updated successfully, false otherwise.
     */
    @Override
    public boolean update(Review review) {
        String checkSql = "SELECT content FROM reviews WHERE restaurantId = ? AND author = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement checkPstmt = conn.prepareStatement(checkSql)) {
            checkPstmt.setString(1, review.getRestaurantId());
            checkPstmt.setString(2, review.getAuthor());
            ResultSet rs = checkPstmt.executeQuery();

            if (rs.next()) {
                String updateSql = "UPDATE reviews SET content = ? WHERE restaurantId = ? AND author = ?";

                try (PreparedStatement updatePstmt = conn.prepareStatement(updateSql)) {
                    updatePstmt.setString(1, review.getContent());
                    updatePstmt.setString(2, review.getRestaurantId());
                    updatePstmt.setString(3, review.getAuthor());
                    int affectedRows = updatePstmt.executeUpdate();
                    return affectedRows == 1; // Ensure that exactly one row is updated
                }
            } else {
                return false; // Review does not exist
            }
        } catch (SQLException e) {
            logger.error("Error updating review", e);
            return false;
        }
    }

    /**
     * Deletes all user reviews for a specific restaurant.
     *
     * @param restaurantId The ID of the restaurant.
     * @return true if at least one review was deleted, false otherwise.
     */
    @Override
    public boolean deleteUserReviews(String restaurantId) {
        String sql = "DELETE FROM reviews WHERE restaurantId = ? AND isSummarized = FALSE";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, restaurantId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0; // Return true if at least one row was deleted
        } catch (SQLException e) {
            logger.error("Error deleting user reviews", e);
            return false;
        }
    }

    /**
     * Deletes the summarized review for a specific restaurant.
     *
     * @param restaurantId The ID of the restaurant.
     * @return true if the summarized review was deleted, false otherwise.
     */
    @Override
    public boolean deleteSummarizedReview(String restaurantId) {
        String sql = "DELETE FROM reviews WHERE restaurantId = ? AND isSummarized = TRUE";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, restaurantId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0; // Return true if at least one row was deleted
        } catch (SQLException e) {
            logger.error("Error deleting summarized review", e);
            return false;
        }
    }

    /**
     * Deletes all reviews for a specific restaurant.
     *
     * @param restaurantId The ID of the restaurant.
     * @return true if at least one review was deleted, false otherwise.
     */
    @Override
    public boolean deleteAllReviews(String restaurantId) {
        String sql = "DELETE FROM reviews WHERE restaurantId = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, restaurantId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0; // Return true if at least one row was deleted
        } catch (SQLException e) {
            logger.error("Error deleting all reviews", e);
            return false;
        }
    }

    /**
     * Maps a {@link ResultSet} to a {@link Review} object using the {@link ReviewFactory}.
     *
     * @param rs the {@link ResultSet} containing the review data.
     * @return a {@link Review} object populated with the data from the {@link ResultSet}.
     * @throws SQLException if there is an error accessing the data in the {@link ResultSet}.
     */
    private Review mapResultSetToReview(ResultSet rs) throws SQLException {
        String restaurantId = rs.getString("restaurantId");
        String author = rs.getString("author");
        String content = rs.getString("content");
        boolean isSummarized = rs.getBoolean("isSummarized");

        if (isSummarized) {
            return ReviewFactory.createGeminiSummarizedReview(restaurantId, content);
        } else {
            return ReviewFactory.createUserReview(restaurantId, author, content);
        }
    }
}
