package entity.review;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewUserFactoryTest {

    private final ReviewWithAuthorFactory factory = new ReviewUserFactory();

    @Test
    public void testCreateValidReview() {
        Review review = factory.createReview("JPQIDf9_tDeuEmsRUsoyG83frY4", "Kera Kim", "Great restaurant!");
        assertNotNull(review);
        assertEquals("JPQIDf9_tDeuEmsRUsoyG83frY4", review.getRestaurantId());
        assertEquals("Kera Kim", review.getAuthor());
        assertEquals("Great restaurant!", review.getContent());
        assertFalse(review.isSummarized());
    }

    @Test
    public void testCreateReviewWithNullRestaurantId() {
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createReview(null, "John Lee", "Amazing experience!");
        });
    }

    @Test
    public void testCreateReviewWithEmptyRestaurantId() {
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createReview("", "Alice Sung", "Lovely place!");
        });
    }

    @Test
    public void testCreateReviewWithWhitespaceRestaurantId() {
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createReview("   ", "David Liu", "Fantastic service!");
        });
    }

    @Test
    public void testCreateReviewWithNullAuthor() {
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createReview("UIIJN1t_tDe829SDOIPE903ndfd", null, "Wonderful ambiance!");
        });
    }

    @Test
    public void testCreateReviewWithEmptyAuthor() {
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createReview("ChIJN1t_tDeuEmsR090G83frY4", "", "Tasty food!");
        });
    }

    @Test
    public void testCreateReviewWithWhitespaceAuthor() {
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createReview("KOIJN1t_tDe829SDLFK123PLdr4", "   ", "Friendly staff!");
        });
    }

    @Test
    public void testCreateReviewWithNullContent() {
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createReview("KJSD839_tDe829SDLEJR2093nr8", "William", null);
        });
    }

    @Test
    public void testCreateReviewWithEmptyContent() {
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createReview("LKJD129_tDe829SDLFK3209nr23", "Chris", "");
        });
    }

    @Test
    public void testCreateReviewWithWhitespaceContent() {
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createReview("POIU123_tDe829SDLFK2039nropw", "Emma", "   ");
        });
    }
}
