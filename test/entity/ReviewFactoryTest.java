package entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewFactoryTest {

    @BeforeEach
    public void setUp() {
        // Set up before each test if necessary
    }

    @AfterEach
    public void tearDown() {
        // Clean up after each test if necessary
    }

    @Test
    public void testCreateUserReviewValid() {
        Review review = ReviewFactory.createUserReview("ChIJqwg8BAA1K4gRGVOVcxx71Kw", "Kera", "Excellent service and food!");
        assertNotNull(review);
        assertEquals("ChIJqwg8BAA1K4gRGVOVcxx71Kw", review.getRestaurantId());
        assertEquals("Kera", review.getAuthor());
        assertEquals("Excellent service and food!", review.getContent());
        assertFalse(review.isSummarized());
    }

    @Test
    public void testCreateUserReviewInvalidRestaurantId() {
        assertThrows(IllegalArgumentException.class, () -> {
            ReviewFactory.createUserReview("", "Kera", "Excellent service and food!");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ReviewFactory.createUserReview(null, "Kera", "Excellent service and food!");
        });
    }

    @Test
    public void testCreateUserReviewInvalidAuthor() {
        assertThrows(IllegalArgumentException.class, () -> {
            ReviewFactory.createUserReview("ChIJqwg8BAA1K4gRGVOVcxx71Kw", "", "Excellent service and food!");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ReviewFactory.createUserReview("ChIJqwg8BAA1K4gRGVOVcxx71Kw", null, "Excellent service and food!");
        });
    }

    @Test
    public void testCreateUserReviewInvalidContent() {
        assertThrows(IllegalArgumentException.class, () -> {
            ReviewFactory.createUserReview("ChIJqwg8BAA1K4gRGVOVcxx71Kw", "Kera", "");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ReviewFactory.createUserReview("ChIJqwg8BAA1K4gRGVOVcxx71Kw", "Kera", null);
        });
    }

    @Test
    public void testCreateGeminiSummarizedReviewValid() {
        Review review = ReviewFactory.createGeminiSummarizedReview("ChIJqwg8BAA1K4gRGVOVcxx71Kw", "Summarized review content");
        assertNotNull(review);
        assertEquals("ChIJqwg8BAA1K4gRGVOVcxx71Kw", review.getRestaurantId());
        assertEquals("Gemini", review.getAuthor());
        assertEquals("Summarized review content", review.getContent());
        assertTrue(review.isSummarized());
    }

    @Test
    public void testCreateGeminiSummarizedReviewInvalidRestaurantId() {
        assertThrows(IllegalArgumentException.class, () -> {
            ReviewFactory.createGeminiSummarizedReview("", "Summarized review content");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ReviewFactory.createGeminiSummarizedReview(null, "Summarized review content");
        });
    }

    @Test
    public void testCreateGeminiSummarizedReviewInvalidContent() {
        assertThrows(IllegalArgumentException.class, () -> {
            ReviewFactory.createGeminiSummarizedReview("ChIJqwg8BAA1K4gRGVOVcxx71Kw", "");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ReviewFactory.createGeminiSummarizedReview("ChIJqwg8BAA1K4gRGVOVcxx71Kw", null);
        });
    }
}
