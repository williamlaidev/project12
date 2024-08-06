package entity.review;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReviewContentValidatorTest {

    private final ReviewValidator validator = new ReviewContentValidator();

    @Test
    public void testValidContent() {
        Review review = new Review("JPQIDf9_tDeuEmsRUsoyG83frY4", "Kera Kim", "Great restaurant!", false);
        assertTrue(validator.check(review));
    }

    @Test
    public void testInvalidContentNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Review("UIIJN1t_tDe829SDOIPE903ndfd", "Kera Kim", null, false);
        });
    }

    @Test
    public void testInvalidContentEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Review("ChIJN1t_tDeuEmsR090G83frY4", "Kera Kim", "", false);
        });
    }

    @Test
    public void testInvalidContentWhitespace() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Review("KOIJN1t_tDe829SDLFK123PLdr4", "Kera Kim", "   ", false);
        });
    }

    @Test
    public void testValidContentWithLeadingAndTrailingSpaces() {
        Review review = new Review("KOIJN1t_tDe829SDLFK123PLdr4", "Kera Kim", "  Delicious food!  ", false);
        assertTrue(validator.check(review));
    }
}
