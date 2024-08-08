package entity.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validator for checking the name of a restaurant.
 */
public class RestaurantNameValidator extends RestaurantValidator {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantNameValidator.class);

    /**
     * Checks if the restaurant name is valid.
     * Valid names are non-null and not empty or just whitespace.
     *
     * @param restaurant the restaurant to validate
     * @return true if the name is valid; false otherwise
     */
    @Override
    public boolean check(Restaurant restaurant) {
        String name = restaurant.getName();
        if (name == null || name.trim().isEmpty()) {
            logger.warn("Restaurant name validation failed for restaurant: {}", restaurant);
            return false;
        }
        return checkNext(restaurant);
    }
}
