package entity.review;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReviewTest {

    @Test
    public void testValidReviewCreation() {
        Review review = new Review("ChIJN1t_tDeuEmsRUsoyG83frY4", "Kera Kim", "Great restaurant!", false);
        assertNotNull(review);
        assertEquals("ChIJN1t_tDeuEmsRUsoyG83frY4", review.getRestaurantId());
        assertEquals("Kera Kim", review.getAuthor());
        assertEquals("Great restaurant!", review.getContent());
        assertFalse(review.isSummarized());
    }

    @Test
    public void testInvalidReviewRestaurantId() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Review("", "Kera Kim", "Great restaurant!", false);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Review(null, "Kera Kim", "Great restaurant!", false);
        });
    }

    @Test
    public void testInvalidReviewAuthor() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Review("ChIJN1t_tDeuEmsRUsoyG83frY4", "", "Great restaurant!", false);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Review("ChIJN1t_tDeuEmsRUsoyG83frY4", null, "Great restaurant!", false);
        });
    }

    @Test
    public void testInvalidReviewContent() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Review("ChIJN1t_tDeuEmsRUsoyG83frY4", "Kera Kim", "", false);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Review("ChIJN1t_tDeuEmsRUsoyG83frY4", "Kera Kim", null, false);
        });
    }

    @Test
    public void testToString() {
        Review review = new Review("ChIJN1t_tDeuEmsRUsoyG83frY4", "Kera Kim", "Great restaurant!", false);
        String expectedString = "Review{restaurantId='ChIJN1t_tDeuEmsRUsoyG83frY4', author='Kera Kim', content='Great restaurant!', isSummarized=false}";
        assertEquals(expectedString, review.toString());
    }

    @Test
    public void testEquals() {
        Review review1 = new Review("ChIJN1t_tDeuEmsRUsoyG83frY4", "Kera Kim", "Great restaurant!", false);
        Review review2 = new Review("ChIJN1t_tDeuEmsRUsoyG83frY4", "Kera Kim", "Great restaurant!", false);
        Review review3 = new Review("ChIJN1t_tDeuEmsRUsoyG83frY4", "Jane Lee", "Great restaurant!", false);

        assertEquals(review1, review2);
        assertNotEquals(review1, review3);
        assertNotEquals(review1, null);
        assertNotEquals(review1, new Object());
    }
}
