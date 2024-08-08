package entity.restaurant;

public abstract class RestaurantValidator {
    private RestaurantValidator nextValidator;

    public RestaurantValidator linkWith(RestaurantValidator nextValidator) {
        this.nextValidator = nextValidator;
        return nextValidator;
    }

    public abstract boolean check(Restaurant restaurant);

    protected boolean checkNext(Restaurant restaurant) {
        if (nextValidator == null) {
            return true;
        }
        return nextValidator.check(restaurant);
    }
}
