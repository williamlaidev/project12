package main.entity;

/**
 * Represents a relationship between a restaurant and a restaurant list.
 */
public class RestaurantListItem {
    private int restaurantListItemId;
    private int restaurantListId;
    private int restaurantId;

    /**
     * @param restaurantListItemId the unique identifier for the restaurant list item
     * @param restaurantListId     the ID of the restaurant list to which this item belongs
     * @param restaurantId         the ID of the restaurant referenced by this item
     */
    public RestaurantListItem(int restaurantListItemId, int restaurantListId, int restaurantId) {
        this.restaurantListItemId = restaurantListItemId;
        this.restaurantListId = restaurantListId;
        this.restaurantId = restaurantId;
    }

    public int getRestaurantListItemId() {
        return restaurantListItemId;
    }

    public int getRestaurantListId() {
        return restaurantListId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantListItemId(int restaurantListItemId) {
        this.restaurantListItemId = restaurantListItemId;
    }

    public void setRestaurantListId(int restaurantListId) {
        this.restaurantListId = restaurantListId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "RestaurantListItem{" +
                "restaurantListItemId=" + restaurantListItemId +
                ", restaurantListId=" + restaurantListId +
                ", restaurantId=" + restaurantId +
                '}';
    }
}
