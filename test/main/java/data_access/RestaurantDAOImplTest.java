package main.java.data_access;

import main.java.entity.DishType;
import main.java.entity.Location;
import main.java.entity.Restaurant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantDAOImplTest {

    private RestaurantDAO restaurantDAO;

    @BeforeEach
    void setUp() {
        restaurantDAO = new RestaurantDAOImpl();
    }

    @AfterEach
    void tearDown() {
        restaurantDAO.clearAllRestaurants();
    }

    @Test
    void addRestaurant() {
        Location location = new Location(40.7128, -74.0060);
        restaurantDAO.addRestaurant("Test Restaurant", location, "123 Test St", DishType.AMERICAN, 4.5, "http://photo.url", "Great food!");

        Restaurant restaurant = restaurantDAO.getRestaurantById(1);
        assertNotNull(restaurant);
        assertEquals("Test Restaurant", restaurant.getName());
        assertEquals(location, restaurant.getLocation());
        assertEquals("123 Test St", restaurant.getAddress());
        assertEquals(DishType.AMERICAN, restaurant.getDishType());
        assertEquals(4.5, restaurant.getAverageRating());
        assertEquals("http://photo.url", restaurant.getPhotoUrl());
        assertEquals("Great food!", restaurant.getSummarizedReview());
    }

    @Test
    void getRestaurantById() {
        Location location = new Location(40.7128, -74.0060);
        restaurantDAO.addRestaurant("Test Restaurant", location, "123 Test St", DishType.AMERICAN, 4.5, "http://photo.url", "Great food!");

        Restaurant restaurant = restaurantDAO.getRestaurantById(1);
        assertNotNull(restaurant);
        assertEquals(1, restaurant.getRestaurantId());
        assertEquals("Test Restaurant", restaurant.getName());
    }

    @Test
    void getRestaurantByName() {
        Location location = new Location(40.7128, -74.0060);
        restaurantDAO.addRestaurant("Test Restaurant", location, "123 Test St", DishType.AMERICAN, 4.5, "http://photo.url", "Great food!");

        Restaurant restaurant = restaurantDAO.getRestaurantByName("Test Restaurant");
        assertNotNull(restaurant);
        assertEquals("Test Restaurant", restaurant.getName());
        assertEquals(location, restaurant.getLocation());
    }

    @Test
    void getAllRestaurants() {
        Location location1 = new Location(40.7128, -74.0060);
        Location location2 = new Location(34.0522, -118.2437);
        restaurantDAO.addRestaurant("Restaurant One", location1, "123 Test St", DishType.AMERICAN, 4.5, "http://photo.url", "Great food!");
        restaurantDAO.addRestaurant("Restaurant Two", location2, "456 Another St", DishType.CHINESE, 3.8, "http://another.url", "Good food!");

        List<Restaurant> restaurants = restaurantDAO.getAllRestaurants();
        assertEquals(2, restaurants.size());
    }

    @Test
    void updateRestaurant() {
        Location location = new Location(40.7128, -74.0060);
        restaurantDAO.addRestaurant("Test Restaurant", location, "123 Test St", DishType.AMERICAN, 4.5, "http://photo.url", "Great food!");

        Restaurant restaurant = restaurantDAO.getRestaurantById(1);
        restaurant.setName("Updated Restaurant");
        boolean isUpdated = restaurantDAO.updateRestaurant(restaurant);

        assertTrue(isUpdated);
        Restaurant updatedRestaurant = restaurantDAO.getRestaurantById(1);
        assertNotNull(updatedRestaurant);
        assertEquals("Updated Restaurant", updatedRestaurant.getName());
    }

    @Test
    void deleteRestaurant() {
        Location location = new Location(40.7128, -74.0060);
        restaurantDAO.addRestaurant("Test Restaurant", location, "123 Test St", DishType.AMERICAN, 4.5, "http://photo.url", "Great food!");

        Restaurant restaurant = restaurantDAO.getRestaurantById(1);
        boolean isDeleted = restaurantDAO.deleteRestaurant(restaurant);

        assertTrue(isDeleted);
        assertNull(restaurantDAO.getRestaurantById(1));
    }

    @Test
    void clearAllRestaurants() {
        Location location1 = new Location(40.7128, -74.0060);
        Location location2 = new Location(34.0522, -118.2437);
        restaurantDAO.addRestaurant("Restaurant One", location1, "123 Test St", DishType.AMERICAN, 4.5, "http://photo.url", "Great food!");
        restaurantDAO.addRestaurant("Restaurant Two", location2, "456 Another St", DishType.CHINESE, 3.8, "http://another.url", "Good food!");

        boolean isCleared = restaurantDAO.clearAllRestaurants();
        assertTrue(isCleared);
        assertTrue(restaurantDAO.getAllRestaurants().isEmpty());
    }
}
