package interface_adapter;

import entity.Restaurant;
import java.util.List;

public interface RestaurantRepository {
    Restaurant getRestaurantById(String restaurantId);
    Restaurant getRestaurantByName(String Name);
    List<Restaurant> getAllRestaurant();
    boolean saveRestaurant(Restaurant restaurant);
    boolean updateRestaurant(Restaurant restaurant);
    boolean deleteRestaurantById(String restaurantId);
    boolean deleteRestaurantByName(String Name);
}
