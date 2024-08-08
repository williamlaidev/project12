package entity.restaurant;

public class RestaurantAddressValidator extends RestaurantValidator {

    @Override
    public boolean check(Restaurant restaurant) {
        String address = restaurant.getAddress();
        if (address == null || address.trim().isEmpty()) {
            System.out.println("Restaurant address validation failed.");
            return false;
        }
        return checkNext(restaurant);
    }
}
