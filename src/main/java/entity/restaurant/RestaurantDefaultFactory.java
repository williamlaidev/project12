package entity.restaurant;

import entity.DishType;
import entity.location.Location;

public class RestaurantDefaultFactory implements RestaurantFactory {

    @Override
    public Restaurant createRestaurant(String restaurantId, String name, Location location, String address, DishType dishType, double averageRating, String photoUrl) {
        return new Restaurant(restaurantId, name, location, address, dishType, averageRating, photoUrl);
    }
}
