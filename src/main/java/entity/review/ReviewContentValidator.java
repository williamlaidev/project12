package entity.review;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validator for checking the content of a review.
 */
public class ReviewContentValidator extends ReviewValidator {

    private static final Logger logger = LoggerFactory.getLogger(ReviewContentValidator.class);

    /**
     * Checks if the content of the review is valid.
     * Valid content is defined as non-null and not empty or just whitespace.
     *
     * @param review The review to be validated.
     * @return true if the content is valid; false otherwise.
     */
    @Override
    public boolean check(Review review) {
        String content = review.getContent();
        if (content == null || content.trim().isEmpty()) {
            logger.warn("Content validation failed for review: {}", review);
            return false;
        }
        return checkNext(review);
    }
}
