package interface_adapter.data;

import domain.RestaurantRepository;
import entity.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implements the {@link RestaurantRepository} interface using an SQLite database.
 * Provides methods to perform CRUD (Create, Read, Update, Delete) operations on restaurants.
 * It manages a SQLite database to store and retrieve restaurant data.
 */
public class SQLiteRestaurantRepository implements RestaurantRepository {
    private static final String DB_URL = "jdbc:sqlite:identifier.sqlite";
    private static final Logger logger = LoggerFactory.getLogger(SQLiteRestaurantRepository.class);

    /**
     * Initializes the repository by creating the restaurants table if it does not already exist.
     * The table schema includes columns for restaurant ID, name, location, address, dish type, average rating, and photo URL.
     */
    public SQLiteRestaurantRepository() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "CREATE TABLE IF NOT EXISTS restaurants ("
                    + "restaurantId TEXT PRIMARY KEY,"
                    + "name TEXT NOT NULL,"
                    + "latitude REAL NOT NULL,"
                    + "longitude REAL NOT NULL,"
                    + "address TEXT NOT NULL,"
                    + "dishType TEXT,"
                    + "averageRating REAL NOT NULL,"
                    + "photoUrl TEXT"
                    + ");";
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            logger.error("Error creating restaurants table", e);
        }
    }

    /**
     * Adds a new restaurant to the database.
     * Delegates to the {@link #save(Restaurant)} method for the actual insertion.
     *
     * @param restaurant the {@link Restaurant} to be added.
     * @return {@code true} if the restaurant was added successfully; {@code false} otherwise.
     */
    @Override
    public boolean add(Restaurant restaurant) {
        return save(restaurant);
    }

    /**
     * Finds a restaurant by its ID.
     *
     * @param id the ID of the restaurant to find.
     * @return an {@link Optional} containing the {@link Restaurant} if found, or an empty {@link Optional} if not found.
     */
    @Override
    public Optional<Restaurant> findById(String id) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "SELECT * FROM restaurants WHERE restaurantId = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Restaurant restaurant = mapResultSetToRestaurant(rs);
                return Optional.of(restaurant);
            }
        } catch (Exception e) {
            logger.error("Error finding restaurant by ID", e);
        }
        return Optional.empty();
    }

    /**
     * Finds a restaurant by its name.
     *
     * @param name the name of the restaurant to find.
     * @return an {@link Optional} containing the {@link Restaurant} if found, or an empty {@link Optional} if not found.
     */
    @Override
    public Optional<Restaurant> findByName(String name) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "SELECT * FROM restaurants WHERE name = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Restaurant restaurant = mapResultSetToRestaurant(rs);
                return Optional.of(restaurant);
            }
        } catch (Exception e) {
            logger.error("Error finding restaurant by name", e);
        }
        return Optional.empty();
    }

    /**
     * Finds a restaurant by its location.
     *
     * @param location the location of the restaurant to find.
     * @return an {@link Optional} containing the {@link Restaurant} if found, or an empty {@link Optional} if not found.
     */
    @Override
    public Optional<Restaurant> findByLocation(Location location) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "SELECT * FROM restaurants WHERE latitude = ? AND longitude = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, location.getLatitude());
            pstmt.setDouble(2, location.getLongitude());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Restaurant restaurant = mapResultSetToRestaurant(rs);
                return Optional.of(restaurant);
            }
        } catch (Exception e) {
            logger.error("Error finding restaurant by location", e);
        }
        return Optional.empty();
    }

    /**
     * Retrieves all restaurants from the database.
     *
     * @return an {@link Optional} containing a list of all {@link Restaurant}s, or an empty {@link Optional} if no restaurants are found.
     */
    @Override
    public Optional<List<Restaurant>> findAll() {
        List<Restaurant> restaurants = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "SELECT * FROM restaurants";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Restaurant restaurant = mapResultSetToRestaurant(rs);
                restaurants.add(restaurant);
            }
            return Optional.of(restaurants);
        } catch (Exception e) {
            logger.error("Error finding all restaurants", e);
        }
        return Optional.empty();
    }

    /**
     * Saves a restaurant to the database. This method performs an insertion of a new record.
     *
     * @param restaurant the {@link Restaurant} to be saved.
     * @return {@code true} if the restaurant was saved successfully; {@code false} otherwise.
     */
    @Override
    public boolean save(Restaurant restaurant) {
        String sql = "INSERT INTO restaurants (restaurantId, name, latitude, longitude, address, dishType, averageRating, photoUrl) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, restaurant.getRestaurantId());
            pstmt.setString(2, restaurant.getName());
            pstmt.setDouble(3, restaurant.getLocation().getLatitude());
            pstmt.setDouble(4, restaurant.getLocation().getLongitude());
            pstmt.setString(5, restaurant.getAddress());

            DishType dishType = restaurant.getDishType();
            if (dishType != null) {
                pstmt.setString(6, dishType.name());
            } else {
                pstmt.setNull(6, Types.VARCHAR);
            }

            pstmt.setDouble(7, restaurant.getAverageRating());
            pstmt.setString(8, restaurant.getPhotoUrl());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error("Error saving restaurant", e);
            return false;
        }
    }

    /**
     * Updates an existing restaurant in the database.
     *
     * @param restaurant the {@link Restaurant} to be updated.
     * @return {@code true} if the restaurant was updated successfully; {@code false} otherwise.
     */
    @Override
    public boolean update(Restaurant restaurant) {
        String sql = "UPDATE restaurants SET name = ?, latitude = ?, longitude = ?, address = ?, dishType = ?, averageRating = ?, photoUrl = ? WHERE restaurantId = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, restaurant.getName());
            pstmt.setDouble(2, restaurant.getLocation().getLatitude());
            pstmt.setDouble(3, restaurant.getLocation().getLongitude());
            pstmt.setString(4, restaurant.getAddress());

            DishType dishType = restaurant.getDishType();
            if (dishType != null) {
                pstmt.setString(5, dishType.name());
            } else {
                pstmt.setNull(5, Types.VARCHAR);
            }

            pstmt.setDouble(6, restaurant.getAverageRating());
            pstmt.setString(7, restaurant.getPhotoUrl());
            pstmt.setString(8, restaurant.getRestaurantId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error("Error updating restaurant", e);
            return false;
        }
    }

    /**
     * Deletes a restaurant from the database by its ID.
     *
     * @param id the ID of the restaurant to be deleted.
     * @return {@code true} if the restaurant was deleted successfully; {@code false} otherwise.
     */
    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM restaurants WHERE restaurantId = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            logger.error("Error deleting restaurant", e);
            return false;
        }
    }

    /**
     * Maps a {@link ResultSet} to a {@link Restaurant} object.
     *
     * @param rs the {@link ResultSet} containing the restaurant data.
     * @return a {@link Restaurant} object populated with the data from the {@link ResultSet}.
     * @throws SQLException if there is an error accessing the data in the {@link ResultSet}.
     */
    private Restaurant mapResultSetToRestaurant(ResultSet rs) throws SQLException {
        Location location = new Location(rs.getDouble("latitude"), rs.getDouble("longitude"));

        // Handle the possibility of dishType being null
        String dishTypeString = rs.getString("dishType");
        DishType dishType = dishTypeString != null ? DishType.fromDishTypeString(dishTypeString) : null;

        return RestaurantFactory.createRestaurantWithoutReviews(
                rs.getString("restaurantId"),
                rs.getString("name"),
                location,
                rs.getString("address"),
                dishType,
                rs.getDouble("averageRating"),
                rs.getString("photoUrl")
        );
    }
}
