package entity.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validator for checking the address of a restaurant.
 */
public class RestaurantAddressValidator extends RestaurantValidator {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantAddressValidator.class);

    /**
     * Checks if the restaurant address is valid.
     * Valid addresses are non-null and not empty or just whitespace.
     *
     * @param restaurant the restaurant to validate
     * @return true if the address is valid; false otherwise
     */
    @Override
    public boolean check(Restaurant restaurant) {
        String address = restaurant.getAddress();
        if (address == null || address.trim().isEmpty()) {
            logger.warn("Restaurant address validation failed for restaurant: {}", restaurant);
            return false;
        }
        return checkNext(restaurant);
    }
}
