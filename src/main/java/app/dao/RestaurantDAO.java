package main.java.app.dao;

import main.java.entity.Restaurant;
import java.util.List;

public interface RestaurantDao {
    void save(Restaurant restaurant);
    Restaurant findById(String restaurantId);
    List<Restaurant> findAll();
    void update(Restaurant restaurant);
    void delete(String restaurantId);
}
