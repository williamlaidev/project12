package entity.restaurant;

import entity.DishType;
import entity.location.Location;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RestaurantDefaultFactoryTest {

    @Test
    public void testCreateValidRestaurant() {
        Location location = new Location(40.7128, -74.0060);
        RestaurantFactory factory = new RestaurantDefaultFactory();
        Restaurant restaurant = factory.createRestaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Valid Restaurant", location, "123 Main Street", DishType.CHINESE, 4.5, "http://example.com/photo.jpg");

        assertNotNull(restaurant);
        assertEquals("ChIJN1t_tDeuEmsRUsoyG83frY4", restaurant.getRestaurantId());
        assertEquals("Valid Restaurant", restaurant.getName());
        assertEquals(location, restaurant.getLocation());
        assertEquals("123 Main Street", restaurant.getAddress());
        assertEquals(DishType.CHINESE, restaurant.getDishType());
        assertEquals(4.5, restaurant.getAverageRating());
        assertEquals("http://example.com/photo.jpg", restaurant.getPhotoUrl());
    }

    @Test
    public void testCreateRestaurantWithNullName() {
        Location location = new Location(40.7128, -74.0060);
        RestaurantFactory factory = new RestaurantDefaultFactory();
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createRestaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", null, location, "123 Main Street", DishType.CHINESE, 4.5, "http://example.com/photo.jpg");
        });
    }

    @Test
    public void testCreateRestaurantWithNullAddress() {
        Location location = new Location(40.7128, -74.0060);
        RestaurantFactory factory = new RestaurantDefaultFactory();
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createRestaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Valid Restaurant", location, null, DishType.CHINESE, 4.5, "http://example.com/photo.jpg");
        });
    }

    @Test
    public void testCreateRestaurantWithInvalidAverageRating() {
        Location location = new Location(40.7128, -74.0060);
        RestaurantFactory factory = new RestaurantDefaultFactory();
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createRestaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Valid Restaurant", location, "123 Main Street", DishType.CHINESE, 6.0, "http://example.com/photo.jpg");
        });
    }

    @Test
    public void testCreateRestaurantWithNullPhotoUrl() {
        Location location = new Location(40.7128, -74.0060);
        RestaurantFactory factory = new RestaurantDefaultFactory();
        Restaurant restaurant = factory.createRestaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Valid Restaurant", location, "123 Main Street", DishType.CHINESE, 4.5, null);

        assertNotNull(restaurant);
        assertEquals("ChIJN1t_tDeuEmsRUsoyG83frY4", restaurant.getRestaurantId());
        assertEquals("Valid Restaurant", restaurant.getName());
        assertEquals(location, restaurant.getLocation());
        assertEquals("123 Main Street", restaurant.getAddress());
        assertEquals(DishType.CHINESE, restaurant.getDishType());
        assertEquals(4.5, restaurant.getAverageRating());
        assertEquals("", restaurant.getPhotoUrl());
    }

    @Test
    public void testCreateRestaurantWithEmptyId() {
        Location location = new Location(40.7128, -74.0060);
        RestaurantFactory factory = new RestaurantDefaultFactory();
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createRestaurant("", "Valid Restaurant", location, "123 Main Street", DishType.CHINESE, 4.5, "http://example.com/photo.jpg");
        });
    }
}
