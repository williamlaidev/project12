package entity.review;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReviewRestaurantIdValidatorTest {

    private final ReviewValidator validator = new ReviewRestaurantIdValidator();

    @Test
    public void testValidRestaurantId() {
        Review review = new Review("JPQIDf9_tDeuEmsRUsoyG83frY4", "Kera Kim", "Great restaurant!", false);
        assertTrue(validator.check(review));
    }

    @Test
    public void testInvalidRestaurantIdNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Review(null, "Kera Kim", "Great restaurant!", false);
        });
    }

    @Test
    public void testInvalidRestaurantIdEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Review("", "Kera Kim", "Great restaurant!", false);
        });
    }

    @Test
    public void testInvalidRestaurantIdWhitespace() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Review("   ", "Kera Kim", "Great restaurant!", false);
        });
    }
}
