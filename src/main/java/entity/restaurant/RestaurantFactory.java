package entity.restaurant;

import entity.DishType;
import entity.location.Location;

public interface RestaurantFactory {
    Restaurant createRestaurant(String restaurantId, String name, Location location, String address, DishType dishType, double averageRating, String photoUrl);
}
