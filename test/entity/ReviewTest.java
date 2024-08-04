package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReviewTest {

    @Test
    void testReviewCreation() {
        Review review = new Review("123", "Alice", "Great food!", false);

        assertEquals("123", review.getRestaurantId());
        assertEquals("Alice", review.getAuthor());
        assertEquals("Great food!", review.getContent());
        assertFalse(review.isSummarized());
    }

    @Test
    void testReviewToString() {
        Review review = new Review("123", "Alice", "Great food!", false);

        String expected = "Review{restaurantId='123', author='Alice', content='Great food!', isSummarized=false}";
        assertEquals(expected, review.toString());
    }

    @Test
    void testReviewEquals() {
        Review review1 = new Review("123", "Alice", "Great food!", false);
        Review review2 = new Review("123", "Alice", "Great food!", false);
        Review review3 = new Review("124", "Bob", "Not so great", true);

        assertEquals(review1, review2);
        assertNotEquals(review1, review3);
    }

    @Test
    void testInvalidRestaurantId() {
        assertThrows(IllegalArgumentException.class, () -> new Review(null, "Alice", "Great food!", false));
        assertThrows(IllegalArgumentException.class, () -> new Review("", "Alice", "Great food!", false));
    }

    @Test
    void testInvalidAuthor() {
        assertThrows(IllegalArgumentException.class, () -> new Review("123", null, "Great food!", false));
        assertThrows(IllegalArgumentException.class, () -> new Review("123", "", "Great food!", false));
    }

    @Test
    void testInvalidContent() {
        assertThrows(IllegalArgumentException.class, () -> new Review("123", "Alice", null, false));
        assertThrows(IllegalArgumentException.class, () -> new Review("123", "Alice", "", false));
    }
}
