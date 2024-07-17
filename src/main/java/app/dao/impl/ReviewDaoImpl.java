package app.dao.impl;

import app.dao.ReviewDao;
import main.java.entity.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDaoImpl implements ReviewDao {
    private Connection connection;

    public ReviewDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Review review) {
        String query = "INSERT INTO reviews (reviewId, restaurantId, userId, rating, comment) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, review.getReviewId());
            stmt.setString(2, review.getRestaurantId());
            stmt.setInt(3, review.getUserId());
            stmt.setDouble(4, review.getRating());
            stmt.setString(5, review.getComment());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Review findById(int reviewId) {
        String query = "SELECT * FROM reviews WHERE reviewId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, reviewId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Review(
                        rs.getInt("reviewId"),
                        rs.getString("restaurantId"),
                        rs.getInt("userId"),
                        rs.getDouble("rating"),
                        rs.getString("comment")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Review> findAll() {
        List<Review> reviews = new ArrayList<>();
        String query = "SELECT * FROM reviews";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                reviews.add(new Review(
                        rs.getInt("reviewId"),
                        rs.getString("restaurantId"),
                        rs.getInt("userId"),
                        rs.getDouble("rating"),
                        rs.getString("comment")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    @Override
    public void update(Review review) {
        String query = "UPDATE reviews SET restaurantId = ?, userId = ?, rating = ?, comment = ? WHERE reviewId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, review.getRestaurantId());
            stmt.setInt(2, review.getUserId());
            stmt.setDouble(3, review.getRating());
            stmt.setString(4, review.getComment());
            stmt.setInt(5, review.getReviewId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int reviewId) {
        String query = "DELETE FROM reviews WHERE reviewId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, reviewId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
