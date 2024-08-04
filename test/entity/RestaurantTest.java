package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

    @Test
    void testRestaurantCreation() {
        Location location = new Location(40.7128, -74.0060);
        DishType dishType = DishType.ITALIAN;
        Restaurant restaurant = new Restaurant("123", "Bella Italia", location, "456 Elm St", dishType, 4.5, "http://example.com/photo.jpg");

        assertEquals("123", restaurant.getRestaurantId());
        assertEquals("Bella Italia", restaurant.getName());
        assertEquals(location, restaurant.getLocation());
        assertEquals("456 Elm St", restaurant.getAddress());
        assertEquals(dishType, restaurant.getDishType());
        assertEquals(4.5, restaurant.getAverageRating());
        assertEquals("http://example.com/photo.jpg", restaurant.getPhotoUrl());
    }

    @Test
    void testRestaurantToString() {
        Location location = new Location(40.7128, -74.0060);
        DishType dishType = DishType.ITALIAN;
        Restaurant restaurant = new Restaurant("123", "Bella Italia", location, "456 Elm St", dishType, 4.5, "http://example.com/photo.jpg");

        String expected = "Restaurant{restaurantId='123', name='Bella Italia', location=Location{latitude=40.7128, longitude=-74.006}, address='456 Elm St', dishType=ITALIAN, averageRating=4.5, photoUrl='http://example.com/photo.jpg'";
        assertEquals(expected, restaurant.toString());
    }

    @Test
    void testRestaurantEquals() {
        Location location = new Location(40.7128, -74.0060);
        DishType dishType = DishType.ITALIAN;
        Restaurant restaurant1 = new Restaurant("123", "Bella Italia", location, "456 Elm St", dishType, 4.5, "http://example.com/photo.jpg");
        Restaurant restaurant2 = new Restaurant("123", "Bella Italia", location, "456 Elm St", dishType, 4.5, "http://example.com/photo.jpg");
        Restaurant restaurant3 = new Restaurant("124", "Spicy Szechuan", location, "789 Oak St", DishType.CHINESE, 3.5, "http://example.com/photo2.jpg");

        assertEquals(restaurant1, restaurant2);
        assertNotEquals(restaurant1, restaurant3);
    }

    @Test
    void testInvalidRestaurantId() {
        Location location = new Location(40.7128, -74.0060);
        DishType dishType = DishType.ITALIAN;

        assertThrows(IllegalArgumentException.class, () -> new Restaurant(null, "Bella Italia", location, "456 Elm St", dishType, 4.5, "http://example.com/photo.jpg"));
        assertThrows(IllegalArgumentException.class, () -> new Restaurant("", "Bella Italia", location, "456 Elm St", dishType, 4.5, "http://example.com/photo.jpg"));
    }

    @Test
    void testInvalidName() {
        Location location = new Location(40.7128, -74.0060);
        DishType dishType = DishType.ITALIAN;

        assertThrows(IllegalArgumentException.class, () -> new Restaurant("123", null, location, "456 Elm St", dishType, 4.5, "http://example.com/photo.jpg"));
        assertThrows(IllegalArgumentException.class, () -> new Restaurant("123", "", location, "456 Elm St", dishType, 4.5, "http://example.com/photo.jpg"));
    }

    @Test
    void testInvalidAddress() {
        Location location = new Location(40.7128, -74.0060);
        DishType dishType = DishType.ITALIAN;

        assertThrows(IllegalArgumentException.class, () -> new Restaurant("123", "Bella Italia", location, null, dishType, 4.5, "http://example.com/photo.jpg"));
        assertThrows(IllegalArgumentException.class, () -> new Restaurant("123", "Bella Italia", location, "", dishType, 4.5, "http://example.com/photo.jpg"));
    }

    @Test
    void testInvalidAverageRating() {
        Location location = new Location(40.7128, -74.0060);
        DishType dishType = DishType.ITALIAN;

        assertThrows(IllegalArgumentException.class, () -> new Restaurant("123", "Bella Italia", location, "456 Elm St", dishType, -1, "http://example.com/photo.jpg"));
        assertThrows(IllegalArgumentException.class, () -> new Restaurant("123", "Bella Italia", location, "456 Elm St", dishType, 6, "http://example.com/photo.jpg"));
    }
}
