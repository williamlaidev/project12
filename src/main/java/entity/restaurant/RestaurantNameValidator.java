package entity.restaurant;

public class RestaurantNameValidator extends RestaurantValidator {

    @Override
    public boolean check(Restaurant restaurant) {
        String name = restaurant.getName();
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Restaurant name validation failed.");
            return false;
        }
        return checkNext(restaurant);
    }
}
