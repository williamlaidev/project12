package framework.data;

import domain.RestaurantRepository;
import entity.*;
import entity.operation_result.OperationResult;
import entity.operation_result.OperationResultFactory;
import entity.operation_result.OperationResultFailureFactory;
import entity.operation_result.OperationResultSuccessFactory;
import entity.restaurant.Restaurant;
import interface_adapter.data.SQLiteRestaurantDataAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SQLiteRestaurantRepository implements RestaurantRepository {
    private static final Logger logger = LoggerFactory.getLogger(SQLiteRestaurantRepository.class);
    private final OperationResultFactory successFactory;
    private final OperationResultFactory failureFactory;
    private final DatabaseConfig databaseConfig;
    private final SQLiteRestaurantDataAdapter dataAdapter;

    public SQLiteRestaurantRepository() {
        this(new DatabaseConfig(), new SQLiteRestaurantDataAdapter(), new OperationResultSuccessFactory(), new OperationResultFailureFactory());
    }

    public SQLiteRestaurantRepository(DatabaseConfig databaseConfig, SQLiteRestaurantDataAdapter dataAdapter, OperationResultFactory successFactory, OperationResultFactory failureFactory) {
        this.databaseConfig = databaseConfig;
        this.dataAdapter = dataAdapter;
        this.successFactory = successFactory;
        this.failureFactory = failureFactory;
        initializeDatabase();
    }

    private void initializeDatabase() {
        try (Connection conn = databaseConfig.connect()) {
            if (conn != null) {
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
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute(sql);
                }
            }
        } catch (SQLException e) {
            logger.error("Error creating restaurants table", e);
        }
    }

    @Override
    public OperationResult add(Restaurant restaurant) {
        return save(restaurant);
    }

    @Override
    public Optional<Restaurant> findById(String id) {
        try (Connection conn = databaseConfig.connect()) {
            if (conn != null) {
                String sql = "SELECT * FROM restaurants WHERE restaurantId = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, id);
                    try (ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                            Restaurant restaurant = dataAdapter.adaptToRestaurant(rs);
                            return Optional.of(restaurant);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding restaurant by ID", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Restaurant> findAll() {
        List<Restaurant> restaurants = new ArrayList<>();
        try (Connection conn = databaseConfig.connect()) {
            if (conn != null) {
                String sql = "SELECT * FROM restaurants";
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(sql)) {
                    while (rs.next()) {
                        Restaurant restaurant = dataAdapter.adaptToRestaurant(rs);
                        restaurants.add(restaurant);
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding all restaurants", e);
        }
        return restaurants;
    }

    @Override
    public OperationResult save(Restaurant restaurant) {
        if (findById(restaurant.getRestaurantId()).isPresent()) {
            return update(restaurant);
        }
        String sql = "INSERT INTO restaurants (restaurantId, name, latitude, longitude, address, dishType, averageRating, photoUrl) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = databaseConfig.connect();
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
            return successFactory.create("Restaurant added successfully");
        } catch (SQLException e) {
            logger.error("Error saving restaurant", e);
            return failureFactory.create("Error saving restaurant: " + e.getMessage());
        }
    }

    @Override
    public OperationResult update(Restaurant restaurant) {
        String sql = "UPDATE restaurants SET name = ?, latitude = ?, longitude = ?, address = ?, dishType = ?, averageRating = ?, photoUrl = ? WHERE restaurantId = ?";

        try (Connection conn = databaseConfig.connect();
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
            return successFactory.create("Restaurant updated successfully");
        } catch (SQLException e) {
            logger.error("Error updating restaurant", e);
            return failureFactory.create("Error updating restaurant: " + e.getMessage());
        }
    }

    @Override
    public boolean deleteById(String id) {
        String sql = "DELETE FROM restaurants WHERE restaurantId = ?";

        try (Connection conn = databaseConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            logger.error("Error deleting restaurant", e);
            return false;
        }
    }

    @Override
    public boolean clearAll() {
        String sql = "DELETE FROM restaurants";

        try (Connection conn = databaseConfig.connect();
             Statement stmt = conn.createStatement()) {
            int affectedRows = stmt.executeUpdate(sql);
            return affectedRows >= 0;
        } catch (SQLException e) {
            logger.error("Error clearing all restaurants", e);
            return false;
        }
    }
}
