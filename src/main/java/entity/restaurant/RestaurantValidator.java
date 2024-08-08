package entity.restaurant;

/**
 * Abstract class for validating restaurant details.
 * Provides a mechanism to chain validators.
 */
public abstract class RestaurantValidator {
    private RestaurantValidator nextValidator;

    /**
     * Links this validator to the next validator in the chain.
     *
     * @param nextValidator the next validator to link
     * @return the next validator
     */
    public RestaurantValidator linkWith(RestaurantValidator nextValidator) {
        this.nextValidator = nextValidator;
        return nextValidator;
    }

    /**
     * Checks if the restaurant is valid.
     *
     * @param restaurant the restaurant to validate
     * @return true if the restaurant is valid; false otherwise
     */
    public abstract boolean check(Restaurant restaurant);

    /**
     * Passes the validation to the next validator in the chain.
     *
     * @param restaurant the restaurant to validate
     * @return true if all validators passed; false otherwise
     */
    protected boolean checkNext(Restaurant restaurant) {
        if (nextValidator == null) {
            return true;
        }
        return nextValidator.check(restaurant);
    }
}
