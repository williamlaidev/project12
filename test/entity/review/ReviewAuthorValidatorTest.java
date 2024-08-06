package entity.review;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReviewAuthorValidatorTest {

    private final ReviewValidator validator = new ReviewAuthorValidator();

    @Test
    public void testValidAuthor() {
        Review review = new Review("JPQIDf9_tDeuEmsRUsoyG83frY4", "Kera Kim", "Great restaurant!", false);
        assertTrue(validator.check(review));
    }

    @Test
    public void testInvalidAuthorNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Review("UIIJN1t_tDe829SDOIPE903ndfd", null, "Great restaurant!", false);
        });
    }

    @Test
    public void testInvalidAuthorEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Review("ChIJN1t_tDeuEmsR090G83frY4", "", "Great restaurant!", false);
        });
    }

    @Test
    public void testInvalidAuthorWhitespace() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Review("KOIJN1t_tDe829SDLFK123PLdr4", "   ", "Great restaurant!", false);
        });
    }
}
