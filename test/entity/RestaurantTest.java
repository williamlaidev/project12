package entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantTest {
    private Restaurant restaurant;
    private List<Review> reviews;
    private Review summarizedReview;
    private Location location;

    @BeforeEach
    public void setUp() {
        // Initialize common test data
        reviews = new ArrayList<>();
        reviews.add(new Review("1", "Alice", "Great place!", false));
        reviews.add(new Review("1", "Bob", "Not bad", false));
        summarizedReview = new Review("1", "Reviewer", "Overall good experience", true);
        location = new Location(40.7128, -74.0060); // Example coordinates (New York City)
    }

    @AfterEach
    public void tearDown() {
        // Clean up resources if needed
        restaurant = null;
        reviews = null;
        summarizedReview = null;
        location = null;
    }

    @Test
    public void testValidRestaurantCreation() {
        restaurant = new Restaurant("1", "The Great Eatery", location, "123 Food Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg", reviews, summarizedReview);
        assertNotNull(restaurant);
        assertEquals("1", restaurant.getRestaurantId());
        assertEquals("The Great Eatery", restaurant.getName());
        assertEquals(location, restaurant.getLocation());
        assertEquals("123 Food Street", restaurant.getAddress());
        assertEquals(DishType.ITALIAN, restaurant.getDishType());
        assertEquals(4.5, restaurant.getAverageRating());
        assertEquals("http://example.com/photo.jpg", restaurant.getPhotoUrl());
        assertEquals(reviews, restaurant.getUserReviews());
        assertEquals(summarizedReview, restaurant.getSummarizedReview());
    }

    @Test
    public void testInvalidRestaurantId() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Restaurant("", "The Great Eatery", location, "123 Food Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg", reviews, summarizedReview));
        assertEquals("Restaurant ID cannot be null or empty.", thrown.getMessage());
    }

    @Test
    public void testInvalidName() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Restaurant("1", "", location, "123 Food Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg", reviews, summarizedReview));
        assertEquals("Restaurant name cannot be null or empty.", thrown.getMessage());
    }

    @Test
    public void testInvalidAddress() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Restaurant("1", "The Great Eatery", location, "", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg", reviews, summarizedReview));
        assertEquals("Restaurant address cannot be null or empty.", thrown.getMessage());
    }

    @Test
    public void testInvalidAverageRating() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Restaurant("1", "The Great Eatery", location, "123 Food Street", DishType.ITALIAN, -1, "http://example.com/photo.jpg", reviews, summarizedReview));
        assertEquals("Average rating must be between 0 and 5.", thrown.getMessage());
    }

    @Test
    public void testInvalidReviewRestaurantId() {
        reviews.add(new Review("2", "Charlie", "Loved it!", false));
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Restaurant("1", "The Great Eatery", location, "123 Food Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg", reviews, summarizedReview));
        assertEquals("User review restaurant ID must match restaurant ID.", thrown.getMessage());
    }

    @Test
    public void testInvalidSummarizedReview() {
        summarizedReview.setSummarized(false);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Restaurant("1", "The Great Eatery", location, "123 Food Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg", reviews, summarizedReview));
        assertEquals("Summarized review must be marked as summarized.", thrown.getMessage());
    }

    @Test
    public void testInvalidLocationLatitude() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Restaurant("1", "The Great Eatery", new Location(100.0, -74.0060), "123 Food Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg", reviews, summarizedReview));
        assertEquals("Latitude must be between -90 and 90 degrees.", thrown.getMessage());
    }

    @Test
    public void testInvalidLocationLongitude() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Restaurant("1", "The Great Eatery", new Location(40.7128, 200.0), "123 Food Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg", reviews, summarizedReview));
        assertEquals("Longitude must be between -180 and 180 degrees.", thrown.getMessage());
    }

}
