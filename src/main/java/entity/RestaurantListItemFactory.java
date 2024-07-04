package main.java.entity;

/**
 * Factory class for creating RestaurantListItem instances.
 */
public class RestaurantListItemFactory {

    /**
     * @param restaurantListItemId the unique identifier for the restaurant list item
     * @param restaurantListId     the ID of the restaurant list to which this item belongs
     * @param restaurantId         the ID of the restaurant referenced by this item
     * @return a new RestaurantListItem object
     */
    public static RestaurantListItem createRestaurantListItem(int restaurantListItemId, int restaurantListId, int restaurantId) {
        return new RestaurantListItem(restaurantListItemId, restaurantListId, restaurantId);
    }
}
