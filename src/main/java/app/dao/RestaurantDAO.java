package app.dao;

import entity.Restaurant;
import java.util.List;

public interface RestaurantDao {
    void save(Restaurant restaurant);
    Restaurant findById(String restaurantId);
    List<Restaurant> findAll();
    void update(Restaurant restaurant);
    void delete(String restaurantId);
}
