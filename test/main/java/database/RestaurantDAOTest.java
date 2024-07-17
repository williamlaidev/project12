package main.java.database;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantDAOTest {

    private RestaurantDAO restaurantDAO;
    private Connection conn;

    @BeforeEach
    void setUp() {
        String url = "jdbc:sqlite:/home/user/java101/project12/src/main/resources/db/mydatabase.sqlite";
        try {
            conn = DriverManager.getConnection(url);
            restaurantDAO = new RestaurantDAO(conn);

            // Create a testapi table (if needed)
            restaurantDAO.createRestaurantTable(); // This method should create your restaurants table
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Failed to connect to database or create testapi table.");
        }
    }

    @AfterEach
    void tearDown() {
        // Close connection after each testapi
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testInsertRestaurant() {
        // Create a testapi restaurant object
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Test Restaurant");
        restaurant.setLatitude(37.7749);
        restaurant.setLongitude(-122.4194);
        restaurant.setAddress("123 Test St, Testville");
        restaurant.setDishType(DishType.AMERICAN);
        restaurant.setAverageRating(4.5);
        restaurant.setPhotoUrl("https://example.com/photo.jpg");
        restaurant.setSummarizedReview("A great place to eat!");

        // Insert the restaurant into the database
        restaurantDAO.insertRestaurant(restaurant);

        // Retrieve the restaurant from the database
        Restaurant retrievedRestaurant = restaurantDAO.getRestaurantById(1); // Assuming restaurantId 1 is inserted

        // Assert that the retrieved restaurant matches the inserted restaurant
        assertNotNull(retrievedRestaurant);
        assertEquals(restaurant.getName(), retrievedRestaurant.getName());
        assertEquals(restaurant.getLatitude(), retrievedRestaurant.getLatitude(), 0.001); // Delta for double comparison
        assertEquals(restaurant.getLongitude(), retrievedRestaurant.getLongitude(), 0.001);
        assertEquals(restaurant.getAddress(), retrievedRestaurant.getAddress());
        assertEquals(restaurant.getDishType(), retrievedRestaurant.getDishType());
        assertEquals(restaurant.getAverageRating(), retrievedRestaurant.getAverageRating(), 0.001);
        assertEquals(restaurant.getPhotoUrl(), retrievedRestaurant.getPhotoUrl());
        assertEquals(restaurant.getSummarizedReview(), retrievedRestaurant.getSummarizedReview());
    }
}