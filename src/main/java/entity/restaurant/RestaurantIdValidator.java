package entity.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validator for checking the ID of a restaurant.
 */
public class RestaurantIdValidator extends RestaurantValidator {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantIdValidator.class);

    /**
     * Checks if the restaurant ID is valid.
     * Valid IDs are non-null and not empty or just whitespace.
     *
     * @param restaurant the restaurant to validate
     * @return true if the ID is valid; false otherwise
     */
    @Override
    public boolean check(Restaurant restaurant) {
        String restaurantId = restaurant.getRestaurantId();
        if (restaurantId == null || restaurantId.trim().isEmpty()) {
            logger.warn("Restaurant ID validation failed for restaurant: {}", restaurant);
            return false;
        }
        return checkNext(restaurant);
    }
}
