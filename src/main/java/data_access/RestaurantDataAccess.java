package data_access;

import entity.Restaurant;

import java.util.List;

public interface RestaurantDataAccess {
    List<Restaurant> loadRestaurants();
    void saveRestaurants(List<Restaurant> restaurants);
}