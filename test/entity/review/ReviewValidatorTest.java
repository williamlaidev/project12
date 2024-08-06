package entity.review;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReviewValidatorTest {

    private static class TestValidator extends ReviewValidator {
        private final boolean shouldPass;

        public TestValidator(boolean shouldPass) {
            this.shouldPass = shouldPass;
        }

        @Override
        public boolean check(Review review) {
            return shouldPass && checkNext(review);
        }
    }

    @Test
    public void testSingleValidatorPass() {
        ReviewValidator validator = new TestValidator(true);
        Review review = new Review("JPQIDf9_tDeuEmsRUsoyG83frY4", "Kera Kim", "Great restaurant!", false);
        assertTrue(validator.check(review));
    }

    @Test
    public void testSingleValidatorFail() {
        ReviewValidator validator = new TestValidator(false);
        Review review = new Review("JPQIDf9_tDeuEmsRUsoyG83frY4", "Kera Kim", "Great restaurant!", false);
        assertFalse(validator.check(review));
    }

    @Test
    public void testChainValidatorPass() {
        ReviewValidator validator = new TestValidator(true).linkWith(new TestValidator(true));
        Review review = new Review("JPQIDf9_tDeuEmsRUsoyG83frY4", "Kera Kim", "Great restaurant!", false);
        assertTrue(validator.check(review));
    }

    @Test
    public void testChainValidatorFailSecond() {
        ReviewValidator validator = new TestValidator(true).linkWith(new TestValidator(false));
        Review review = new Review("JPQIDf9_tDeuEmsRUsoyG83frY4", "Kera Kim", "Great restaurant!", false);
        assertFalse(validator.check(review));
    }

    @Test
    public void testChainValidatorFailBoth() {
        ReviewValidator validator = new TestValidator(false).linkWith(new TestValidator(false));
        Review review = new Review("JPQIDf9_tDeuEmsRUsoyG83frY4", "Kera Kim", "Great restaurant!", false);
        assertFalse(validator.check(review));
    }
}
