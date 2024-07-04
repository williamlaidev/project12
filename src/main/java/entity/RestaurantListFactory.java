package main.java.entity;
import java.util.List;

/**
 * Factory class for creating RestaurantList instances.
 */
public class RestaurantListFactory {

    /**
     * @param restaurantListId the unique identifier for the restaurant list
     * @param userId           the ID of the user who created the restaurant list
     * @param name             the name of the restaurant list
     * @param description      the description of the restaurant list (optional)
     * @param restaurantItems  the list of restaurant list items in the restaurant list
     * @return a new RestaurantList object
     */
    public static RestaurantList createRestaurantList(int restaurantListId, int userId, String name, String description, List<RestaurantListItem> restaurantItems) {
        return new RestaurantList(restaurantListId, userId, name, description, restaurantItems);
    }
}