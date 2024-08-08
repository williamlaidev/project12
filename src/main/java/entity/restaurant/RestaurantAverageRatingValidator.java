package entity.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validator for checking the average rating of a restaurant.
 */
public class RestaurantAverageRatingValidator extends RestaurantValidator {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantAverageRatingValidator.class);

    /**
     * Checks if the restaurant's average rating is within the valid range.
     * Valid ratings are between 0 and 5, inclusive.
     *
     * @param restaurant the restaurant to validate
     * @return true if the rating is valid; false otherwise
     */
    @Override
    public boolean check(Restaurant restaurant) {
        double averageRating = restaurant.getAverageRating();
        if (averageRating < 0 || averageRating > 5) {
            logger.warn("Average rating validation failed for restaurant: {}", restaurant);
            return false;
        }
        return checkNext(restaurant);
    }
}
