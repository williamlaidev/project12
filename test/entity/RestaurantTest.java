package entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

    private Restaurant restaurant;
    private Location location;
    private DishType dishType;

    @BeforeEach
    void setUp() {
        // Initialize any necessary objects before each test
        location = new Location(40.7128, -74.0060); // Example location
        dishType = DishType.ITALIAN; // Example dish type
    }

    @AfterEach
    void tearDown() {
        // Clean up after each test
        restaurant = null;
        location = null;
        dishType = null;
    }

    @Test
    void testConstructorValid() {
        restaurant = new Restaurant("1", "Test Restaurant", location, "123 Test St", dishType, 4.5, "http://photo.url", "Great place!");
        assertNotNull(restaurant);
        assertEquals("1", restaurant.getRestaurantId());
        assertEquals("Test Restaurant", restaurant.getName());
        assertEquals(location, restaurant.getLocation());
        assertEquals("123 Test St", restaurant.getAddress());
        assertEquals(dishType, restaurant.getDishType());
        assertEquals(4.5, restaurant.getAverageRating());
        assertEquals("http://photo.url", restaurant.getPhotoUrl());
        assertEquals("Great place!", restaurant.getSummarizedReview());
    }

    @Test
    void testConstructorInvalid() {
        // Test invalid name
        assertThrows(IllegalArgumentException.class, () -> {
            new Restaurant("1", "", location, "123 Test St", dishType, 4.5, "http://photo.url", "Great place!");
        });

        // Test invalid address
        assertThrows(IllegalArgumentException.class, () -> {
            new Restaurant("1", "Test Restaurant", location, "", dishType, 4.5, "http://photo.url", "Great place!");
        });

        // Test invalid average rating
        assertThrows(IllegalArgumentException.class, () -> {
            new Restaurant("1", "Test Restaurant", location, "123 Test St", dishType, 6.0, "http://photo.url", "Great place!");
        });

        // Test invalid restaurant ID
        assertThrows(IllegalArgumentException.class, () -> {
            new Restaurant("-1", "Test Restaurant", location, "123 Test St", dishType, 4.5, "http://photo.url", "Great place!");
        });
    }

    @Test
    void testSetters() {
        restaurant = new Restaurant("1", "Test Restaurant", location, "123 Test St", dishType, 4.5, "http://photo.url", "Great place!");

        restaurant.setRestaurantId("2");
        restaurant.setName("New Name");
        restaurant.setAddress("456 New St");
        restaurant.setAverageRating(3.8);
        restaurant.setPhotoUrl("http://newphoto.url");
        restaurant.setSummarizedReview("Good place!");

        assertEquals("2", restaurant.getRestaurantId());
        assertEquals("New Name", restaurant.getName());
        assertEquals("456 New St", restaurant.getAddress());
        assertEquals(3.8, restaurant.getAverageRating());
        assertEquals("http://newphoto.url", restaurant.getPhotoUrl());
        assertEquals("Good place!", restaurant.getSummarizedReview());
    }

    @Test
    void testInvalidSetters() {
        restaurant = new Restaurant("1", "Test Restaurant", location, "123 Test St", dishType, 4.5, "http://photo.url", "Great place!");

        assertThrows(IllegalArgumentException.class, () -> {
            restaurant.setName("");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            restaurant.setAddress("");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            restaurant.setAverageRating(6.0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            restaurant.setRestaurantId("-1");
        });
    }

    @Test
    void testToString() {
        restaurant = new Restaurant("1", "Test Restaurant", location, "123 Test St", dishType, 4.5, "http://photo.url", "Great place!");
        String expectedString = "Restaurant{" +
                "restaurantId='1'" +
                ", name='Test Restaurant'" +
                ", location=" + location +
                ", address='123 Test St'" +
                ", dishType=" + dishType +
                ", averageRating=4.5" +
                ", photoUrl='http://photo.url'" +
                ", summarizedReview='Great place!'" +
                '}';
        assertEquals(expectedString, restaurant.toString());
    }
}
