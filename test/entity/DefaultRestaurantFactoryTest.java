package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultRestaurantFactoryTest {

    @Test
    void testCreateRestaurant() {
        RestaurantFactory factory = new DefaultRestaurantFactory();
        Location location = new Location(40.7128, -74.0060);
        DishType dishType = DishType.ITALIAN;
        String restaurantId = "123";
        String name = "Bella Italia";
        String address = "456 Elm St";
        double averageRating = 4.5;
        String photoUrl = "http://example.com/photo.jpg";

        Restaurant restaurant = factory.createRestaurant(restaurantId, name, location, address, dishType, averageRating, photoUrl);

        assertEquals(restaurantId, restaurant.getRestaurantId());
        assertEquals(name, restaurant.getName());
        assertEquals(location, restaurant.getLocation());
        assertEquals(address, restaurant.getAddress());
        assertEquals(dishType, restaurant.getDishType());
        assertEquals(averageRating, restaurant.getAverageRating());
        assertEquals(photoUrl, restaurant.getPhotoUrl());
    }

    @Test
    void testCreateRestaurantWithNullPhotoUrl() {
        RestaurantFactory factory = new DefaultRestaurantFactory();
        Location location = new Location(40.7128, -74.0060);
        DishType dishType = DishType.ITALIAN;
        String restaurantId = "123";
        String name = "Bella Italia";
        String address = "456 Elm St";
        double averageRating = 4.5;
        String photoUrl = null;

        Restaurant restaurant = factory.createRestaurant(restaurantId, name, location, address, dishType, averageRating, photoUrl);

        assertEquals(restaurantId, restaurant.getRestaurantId());
        assertEquals(name, restaurant.getName());
        assertEquals(location, restaurant.getLocation());
        assertEquals(address, restaurant.getAddress());
        assertEquals(dishType, restaurant.getDishType());
        assertEquals(averageRating, restaurant.getAverageRating());
        assertEquals("", restaurant.getPhotoUrl());
    }

    @Test
    void testCreateRestaurantWithInvalidParameters() {
        RestaurantFactory factory = new DefaultRestaurantFactory();
        Location location = new Location(40.7128, -74.0060);
        DishType dishType = DishType.ITALIAN;

        assertThrows(IllegalArgumentException.class, () ->
                factory.createRestaurant(null, "Bella Italia", location, "456 Elm St", dishType, 4.5, "http://example.com/photo.jpg")
        );
        assertThrows(IllegalArgumentException.class, () ->
                factory.createRestaurant("123", null, location, "456 Elm St", dishType, 4.5, "http://example.com/photo.jpg")
        );
        assertThrows(IllegalArgumentException.class, () ->
                factory.createRestaurant("123", "Bella Italia", location, null, dishType, 4.5, "http://example.com/photo.jpg")
        );
        assertThrows(IllegalArgumentException.class, () ->
                factory.createRestaurant("123", "Bella Italia", location, "456 Elm St", null, 4.5, "http://example.com/photo.jpg")
        );
        assertThrows(IllegalArgumentException.class, () ->
                factory.createRestaurant("123", "Bella Italia", location, "456 Elm St", dishType, -1, "http://example.com/photo.jpg")
        );
        assertThrows(IllegalArgumentException.class, () ->
                factory.createRestaurant("123", "Bella Italia", location, "456 Elm St", dishType, 6, "http://example.com/photo.jpg")
        );
    }
}
