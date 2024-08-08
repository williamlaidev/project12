package entity.restaurant;

/**
 * Validator implementation for restaurant ID.
 */
public class RestaurantIdValidator extends RestaurantValidator {

    @Override
    public boolean check(Restaurant restaurant) {
        String restaurantId = restaurant.getRestaurantId();
        if (restaurantId == null || restaurantId.trim().isEmpty()) {
            System.out.println("Restaurant ID validation failed.");
            return false;
        }
        return checkNext(restaurant);
    }
}
