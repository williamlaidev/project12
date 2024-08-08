package entity.review;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validator for checking the validity of a restaurant ID in a review.
 */
public class ReviewRestaurantIdValidator extends ReviewValidator {

    private static final Logger logger = LoggerFactory.getLogger(ReviewRestaurantIdValidator.class);

    /**
     * Checks if the restaurant ID of the review is valid.
     * A valid restaurant ID is one that is not null and not empty or just whitespace.
     *
     * @param review The review to be validated.
     * @return true if the restaurant ID is valid; false otherwise.
     */
    @Override
    public boolean check(Review review) {
        String restaurantId = review.getRestaurantId();
        if (restaurantId == null || restaurantId.trim().isEmpty()) {
            logger.warn("Restaurant ID validation failed for review: {}", review);
            return false;
        }
        return checkNext(review);
    }
}
