package entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantFactoryTest {

    private Location location;
    private List<Review> userReviews;
    private Review summarizedReview;

    @BeforeEach
    public void setUp() {
        location = new Location(43.6532, -79.3832);
        userReviews = new ArrayList<>();
        userReviews.add(new Review("ChIJqwg8BAA1K4gRGVOVcxx71Kw", "Sophia", "The food is great, but the line is long :(", false));
        userReviews.add(new Review("ChIJqwg8BAA1K4gRGVOVcxx71Kw", "James", "Everything is good but the drinks were particularly excellent :)", false));
        summarizedReview = new Review("ChIJqwg8BAA1K4gRGVOVcxx71Kw", "Summarizer", "Overall satisfying experience", true);
    }

    @AfterEach
    public void tearDown() {
        location = null;
        userReviews = null;
        summarizedReview = null;
    }

    @Test
    public void testCreateRestaurantWithoutReviews() {
        Restaurant restaurant = RestaurantFactory.createRestaurantWithoutReviews(
                "ChIJqwg8BAA1K4gRGVOVcxx71Kw",
                "Hello Food",
                location,
                "123 Food Street, Toronto, ON",
                DishType.ITALIAN,
                4.5,
                "http://example.com/photo.jpg"
        );

        assertNotNull(restaurant);
        assertEquals("ChIJqwg8BAA1K4gRGVOVcxx71Kw", restaurant.getRestaurantId());
        assertEquals("Hello Food", restaurant.getName());
        assertEquals(location, restaurant.getLocation());
        assertEquals("123 Food Street, Toronto, ON", restaurant.getAddress());
        assertEquals(DishType.ITALIAN, restaurant.getDishType());
        assertEquals(4.5, restaurant.getAverageRating());
        assertEquals("http://example.com/photo.jpg", restaurant.getPhotoUrl());
        assertTrue(restaurant.getUserReviews().isEmpty());
        assertNull(restaurant.getSummarizedReview());
    }

    @Test
    public void testCreateRestaurantWithUserReviews() {
        Restaurant restaurant = RestaurantFactory.createRestaurantWithUserReviews(
                "ChIJqwg8BAA1K4gRGVOVcxx71Kw",
                "Hello Food",
                location,
                "123 Food Street, Toronto, ON",
                DishType.ITALIAN,
                4.5,
                "http://example.com/photo.jpg",
                userReviews
        );

        assertNotNull(restaurant);
        assertEquals("ChIJqwg8BAA1K4gRGVOVcxx71Kw", restaurant.getRestaurantId());
        assertEquals("Hello Food", restaurant.getName());
        assertEquals(location, restaurant.getLocation());
        assertEquals("123 Food Street, Toronto, ON", restaurant.getAddress());
        assertEquals(DishType.ITALIAN, restaurant.getDishType());
        assertEquals(4.5, restaurant.getAverageRating());
        assertEquals("http://example.com/photo.jpg", restaurant.getPhotoUrl());
        assertEquals(userReviews, restaurant.getUserReviews());
        assertNull(restaurant.getSummarizedReview());
    }

    @Test
    public void testCreateRestaurantWithUserReviewsAndSummarizedReview() {
        Restaurant restaurant = RestaurantFactory.createRestaurantWithUserReviewsAndSummarizedReview(
                "ChIJqwg8BAA1K4gRGVOVcxx71Kw",
                "Hello Food",
                location,
                "123 Food Street, Toronto, ON",
                DishType.ITALIAN,
                4.5,
                "http://example.com/photo.jpg",
                userReviews,
                summarizedReview
        );

        assertNotNull(restaurant);
        assertEquals("ChIJqwg8BAA1K4gRGVOVcxx71Kw", restaurant.getRestaurantId());
        assertEquals("Hello Food", restaurant.getName());
        assertEquals(location, restaurant.getLocation());
        assertEquals("123 Food Street, Toronto, ON", restaurant.getAddress());
        assertEquals(DishType.ITALIAN, restaurant.getDishType());
        assertEquals(4.5, restaurant.getAverageRating());
        assertEquals("http://example.com/photo.jpg", restaurant.getPhotoUrl());
        assertEquals(userReviews, restaurant.getUserReviews());
        assertEquals(summarizedReview, restaurant.getSummarizedReview());
    }

    @Test
    public void testCreateRestaurantWithoutReviewsNullPhotoUrl() {
        Restaurant restaurant = RestaurantFactory.createRestaurantWithoutReviews(
                "ChIJqwg8BAA1K4gRGVOVcxx71Kw",
                "Hello Food",
                location,
                "123 Food Street, Toronto, ON",
                DishType.ITALIAN,
                4.5,
                null
        );

        assertNotNull(restaurant);
        assertEquals("ChIJqwg8BAA1K4gRGVOVcxx71Kw", restaurant.getRestaurantId());
        assertEquals("Hello Food", restaurant.getName());
        assertEquals(location, restaurant.getLocation());
        assertEquals("123 Food Street, Toronto, ON", restaurant.getAddress());
        assertEquals(DishType.ITALIAN, restaurant.getDishType());
        assertEquals(4.5, restaurant.getAverageRating());
        assertTrue(restaurant.getUserReviews().isEmpty());
        assertNull(restaurant.getSummarizedReview());
        assertEquals("", restaurant.getPhotoUrl());
    }
}
