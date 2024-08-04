package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserReviewFactoryTest {

    @Test
    void testCreateReview() {
        ReviewFactory factory = new UserReviewFactory();
        Review review = factory.createReview("123", "Alice", "Great food!");

        assertEquals("123", review.getRestaurantId());
        assertEquals("Alice", review.getAuthor());
        assertEquals("Great food!", review.getContent());
        assertFalse(review.isSummarized());
    }
}
