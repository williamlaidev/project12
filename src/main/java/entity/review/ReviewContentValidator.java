package entity.review;

public class ReviewContentValidator extends ReviewValidator {

    @Override
    public boolean check(Review review) {
        String content = review.getContent();
        if (content == null || content.trim().isEmpty()) {
            System.out.println("Content validation failed.");
            return false;
        }
        return checkNext(review);
    }
}
