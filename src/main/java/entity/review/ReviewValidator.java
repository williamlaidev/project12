package entity.review;

public abstract class ReviewValidator {
    private ReviewValidator nextValidator;

    public ReviewValidator linkWith(ReviewValidator nextValidator) {
        this.nextValidator = nextValidator;
        return nextValidator;
    }

    public abstract boolean check(Review review);

    protected boolean checkNext(Review review) {
        if (nextValidator == null) {
            return true;
        }
        return nextValidator.check(review);
    }
}
