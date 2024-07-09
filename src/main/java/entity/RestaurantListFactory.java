package main.java.entity;

import java.util.List;

/**
 * Factory class for creating instances of RestaurantList.
 */
public class RestaurantListFactory {

    /**
     * Creates a new instance of RestaurantList.
     *
     * @param restaurantListId the unique identifier for the restaurant list
     * @param user             the user who created the restaurant list
     * @param name             the name of the restaurant list
     * @param description      the description of the restaurant list (optional)
     * @param restaurants      the list of restaurants in the restaurant list
     * @return a new RestaurantList object
     */
    public RestaurantList createRestaurantList(int restaurantListId, User user, String name, String description, List<Restaurant> restaurants) {
        return new RestaurantList(restaurantListId, user, name, description, restaurants);
    }
}