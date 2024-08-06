package entity.review;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReviewGeminiFactoryTest {

    private final ReviewWithoutAuthorFactory factory = new ReviewGeminiFactory();

    @Test
    public void testCreateValidReview() {
        Review review = factory.createReview("JPQIDf9_tDeuEmsRUsoyG83frY4", "Great restaurant!");
        assertNotNull(review);
        assertEquals("JPQIDf9_tDeuEmsRUsoyG83frY4", review.getRestaurantId());
        assertEquals("Gemini", review.getAuthor());
        assertEquals("Great restaurant!", review.getContent());
        assertTrue(review.isSummarized());
    }

    @Test
    public void testCreateReviewWithNullRestaurantId() {
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createReview(null, "Amazing experience");
        });
    }

    @Test
    public void testCreateReviewWithEmptyRestaurantId() {
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createReview("", "Lovely place");
        });
    }

    @Test
    public void testCreateReviewWithWhitespaceRestaurantId() {
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createReview("   ", "Fantastic service");
        });
    }

    @Test
    public void testCreateReviewWithNullContent() {
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createReview("KJSD839_tDe829SDLEJR2093nr8", null);
        });
    }

    @Test
    public void testCreateReviewWithEmptyContent() {
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createReview("LKJD129_tDe829SDLFK3209nr23", "");
        });
    }

    @Test
    public void testCreateReviewWithWhitespaceContent() {
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createReview("POIU123_tDe829SDLFK2039nropw", "   ");
        });
    }
}
