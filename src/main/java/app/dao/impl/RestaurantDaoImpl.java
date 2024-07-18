package app.dao.impl;

import app.dao.RestaurantDao;
import entity.Restaurant;
import entity.Location;
import entity.DishType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDaoImpl implements RestaurantDao {
    private Connection connection;

    public RestaurantDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Restaurant restaurant) {
        String query = "INSERT INTO restaurants (restaurantId, name, latitude, longitude, address, dishType, averageRating, photoUrl, summarizedReview) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, restaurant.getRestaurantId());
            stmt.setString(2, restaurant.getName());
            stmt.setDouble(3, restaurant.getLocation().getLatitude());
            stmt.setDouble(4, restaurant.getLocation().getLongitude());
            stmt.setString(5, restaurant.getAddress());
            stmt.setString(6, restaurant.getDishType().name());
            stmt.setDouble(7, restaurant.getAverageRating());
            stmt.setString(8, restaurant.getPhotoUrl());
            stmt.setString(9, restaurant.getSummarizedReview());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Restaurant findById(String restaurantId) {
        String query = "SELECT * FROM restaurants WHERE restaurantId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, restaurantId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Restaurant(
                        rs.getString("restaurantId"),
                        rs.getString("name"),
                        new Location(rs.getDouble("latitude"), rs.getDouble("longitude")),
                        rs.getString("address"),
                        DishType.valueOf(rs.getString("dishType")),
                        rs.getDouble("averageRating"),
                        rs.getString("photoUrl"),
                        rs.getString("summarizedReview")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Restaurant> findAll() {
        List<Restaurant> restaurants = new ArrayList<>();
        String query = "SELECT * FROM restaurants";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                restaurants.add(new Restaurant(
                        rs.getString("restaurantId"),
                        rs.getString("name"),
                        new Location(rs.getDouble("latitude"), rs.getDouble("longitude")),
                        rs.getString("address"),
                        DishType.valueOf(rs.getString("dishType")),
                        rs.getDouble("averageRating"),
                        rs.getString("photoUrl"),
                        rs.getString("summarizedReview")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurants;
    }

    @Override
    public void update(Restaurant restaurant) {
        String query = "UPDATE restaurants SET name = ?, latitude = ?, longitude = ?, address = ?, dishType = ?, averageRating = ?, photoUrl = ?, summarizedReview = ? WHERE restaurantId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, restaurant.getName());
            stmt.setDouble(2, restaurant.getLocation().getLatitude());
            stmt.setDouble(3, restaurant.getLocation().getLongitude());
            stmt.setString(4, restaurant.getAddress());
            stmt.setString(5, restaurant.getDishType().name());
            stmt.setDouble(6, restaurant.getAverageRating());
            stmt.setString(7, restaurant.getPhotoUrl());
            stmt.setString(8, restaurant.getSummarizedReview());
            stmt.setString(9, restaurant.getRestaurantId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String restaurantId) {
        String query = "DELETE FROM restaurants WHERE restaurantId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, restaurantId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
