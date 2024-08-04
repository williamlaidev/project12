package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeminiReviewFactoryTest {

    @Test
    void testCreateReview() {
        ReviewFactory factory = new GeminiReviewFactory();
        Review review = factory.createReview("123", "Alice", "Great food!");

        assertEquals("123", review.getRestaurantId());
        assertEquals("Gemini", review.getAuthor());
        assertEquals("Great food!", review.getContent());
        assertTrue(review.isSummarized());
    }
}
