package main.java.entity;

import java.util.List;

public class RestaurantListFactory {

    /**
     * Creates a new instance of RestaurantList.
     *
     * @param restaurantListId the unique identifier for the restaurant list
     * @param userId           the ID of the user who created the restaurant list
     * @param name             the name of the restaurant list
     * @param description      the description of the restaurant list (optional)
     * @param restaurants      the list of restaurants in the restaurant list
     * @return a new RestaurantList object
     * @throws IllegalArgumentException if any parameter validation fails
     */
    public static RestaurantList createRestaurantList(int restaurantListId, int userId, String name, String description, List<Restaurant> restaurants) {
        // Validate parameters if needed
        if (restaurantListId <= 0) {
            throw new IllegalArgumentException("restaurantListId must be greater than zero");
        }
        if (userId <= 0) {
            throw new IllegalArgumentException("userId must be greater than zero");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name must not be empty");
        }

        // Create and return RestaurantList object
        return new RestaurantList(restaurantListId, userId, name, description, restaurants);
    }
}