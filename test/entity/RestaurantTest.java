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
        reviews = new ArrayList<>();
        reviews.add(new Review("1", "Alice", "Great place!", false));
        reviews.add(new Review("1", "Bob", "Not bad", false));
        summarizedReview = new Review("1", "Reviewer", "Overall good experience", true);
        location = new Location(40.7128, -74.0060); // Example coordinates (New York City)
        restaurant = new Restaurant("1", "The Great Restaurant", location, "123 Food Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg", reviews, summarizedReview);
    }

    @AfterEach
    public void tearDown() {
        restaurant = null;
        reviews = null;
        summarizedReview = null;
        location = null;
    }

    @Test
    public void testValidRestaurantCreation() {
        assertNotNull(restaurant);
        assertEquals("1", restaurant.getRestaurantId());
        assertEquals("The Great Restaurant", restaurant.getName());
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

    @Test
    public void testSetName() {
        restaurant.setName("New Restaurant");
        assertEquals("New Restaurant", restaurant.getName());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> restaurant.setName(""));
        assertEquals("Restaurant name cannot be null or empty.", thrown.getMessage());
    }

    @Test
    public void testSetLocation() {
        Location newLocation = new Location(37.7749, -122.4194);
        restaurant.setLocation(newLocation);
        assertEquals(newLocation, restaurant.getLocation());
    }

    @Test
    public void testSetAddress() {
        restaurant.setAddress("456 New Address");
        assertEquals("456 New Address", restaurant.getAddress());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> restaurant.setAddress(""));
        assertEquals("Restaurant address cannot be null or empty.", thrown.getMessage());
    }

    @Test
    public void testSetDishType() {
        restaurant.setDishType(DishType.CHINESE);
        assertEquals(DishType.CHINESE, restaurant.getDishType());
    }

    @Test
    public void testSetAverageRating() {
        restaurant.setAverageRating(4.8);
        assertEquals(4.8, restaurant.getAverageRating());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> restaurant.setAverageRating(6));
        assertEquals("Average rating must be between 0 and 5.", thrown.getMessage());
    }

    @Test
    public void testSetPhotoUrl() {
        restaurant.setPhotoUrl("http://newphoto.com/photo.jpg");
        assertEquals("http://newphoto.com/photo.jpg", restaurant.getPhotoUrl());

        restaurant.setPhotoUrl(null);
        assertEquals("", restaurant.getPhotoUrl());
    }

    @Test
    public void testSetUserReviews() {
        List<Review> newReviews = new ArrayList<>();
        newReviews.add(new Review("1", "Kera", "Good", false));
        restaurant.setUserReviews(newReviews);
        assertEquals(newReviews, restaurant.getUserReviews());

        newReviews.add(new Review("2", "Kaira", "Excellent", false));
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> restaurant.setUserReviews(newReviews));
        assertEquals("User review restaurant ID must match restaurant ID.", thrown.getMessage());
    }

    @Test
    public void testSetSummarizedReview() {
        Review newSummarizedReview = new Review("1", "New Reviewer", "Great overall", true);
        restaurant.setSummarizedReview(newSummarizedReview);
        assertEquals(newSummarizedReview, restaurant.getSummarizedReview());

        newSummarizedReview.setSummarized(false);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> restaurant.setSummarizedReview(newSummarizedReview));
        assertEquals("Summarized review must be marked as summarized.", thrown.getMessage());
    }

    @Test
    public void testEqualsAndHashCode() {
        Restaurant restaurant1 = new Restaurant("1", "The Great Restaurant", location, "123 Food Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg", reviews, summarizedReview);
        Restaurant restaurant2 = new Restaurant("1", "The Great Restaurant", location, "123 Food Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg", reviews, summarizedReview);
        Restaurant restaurant3 = new Restaurant("2", "Another Restaurant", new Location(34.0522, -118.2437), "456 Another Street", DishType.CHINESE, 4.2, "http://example.com/photo2.jpg", new ArrayList<>(), null);

        assertEquals(restaurant1, restaurant2);
        assertNotEquals(restaurant1, restaurant3);
        assertEquals(restaurant1.hashCode(), restaurant2.hashCode());
        assertNotEquals(restaurant1.hashCode(), restaurant3.hashCode());
    }

    @Test
    public void testToString() {
        String expectedString = "Restaurant{restaurantId='1', name='The Great Restaurant', location=Location{latitude=40.7128, longitude=-74.006}, address='123 Food Street', dishType=ITALIAN, averageRating=4.5, photoUrl='http://example.com/photo.jpg', userReviews=" + reviews.toString() + ", summarizedReview=" + summarizedReview.toString() + "}";
        assertEquals(expectedString, restaurant.toString());
    }
}
