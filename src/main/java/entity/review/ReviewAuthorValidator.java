package entity.review;

public class ReviewAuthorValidator extends ReviewValidator {

    @Override
    public boolean check(Review review) {
        String author = review.getAuthor();
        if (author == null || author.trim().isEmpty()) {
            System.out.println("Author validation failed.");
            return false;
        }
        return checkNext(review);
    }
}
