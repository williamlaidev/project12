package entity.review;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validator for checking the author of a review.
 */
public class ReviewAuthorValidator extends ReviewValidator {

    private static final Logger logger = LoggerFactory.getLogger(ReviewAuthorValidator.class);

    /**
     * Checks if the author of the review is valid.
     * A valid author is one that is not null and not empty or just whitespace.
     *
     * @param review The review to be validated.
     * @return true if the author is valid; false otherwise.
     */
    @Override
    public boolean check(Review review) {
        String author = review.getAuthor();
        if (author == null || author.trim().isEmpty()) {
            logger.warn("Author validation failed for review: {}", review);
            return false;
        }
        return checkNext(review);
    }
}
