package entity.review;

/**
 * Abstract class representing a validator in a chain of responsibility for reviewing.
 */
public abstract class ReviewValidator {
    private ReviewValidator nextValidator;

    /**
     * Links the current validator to the next validator in the chain.
     *
     * @param nextValidator The next validator to be linked.
     * @return The next validator, allowing for method chaining.
     */
    public ReviewValidator linkWith(ReviewValidator nextValidator) {
        this.nextValidator = nextValidator;
        return nextValidator;
    }

    /**
     * Abstract method for checking a review. Subclasses must implement this method to define
     * the specific validation logic.
     *
     * @param review The review to be validated.
     * @return true if the review passes validation; false otherwise.
     */
    public abstract boolean check(Review review);

    /**
     * Checks the review using the next validator in the chain, if one exists.
     *
     * @param review The review to be validated.
     * @return true if the review passes validation, either by this validator or by the next one;
     *         false otherwise.
     */
    protected boolean checkNext(Review review) {
        if (nextValidator == null) {
            return true;
        }
        return nextValidator.check(review);
    }
}
