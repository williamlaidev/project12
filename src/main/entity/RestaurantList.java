package main.entity;
import java.util.List;

/**
 * Represents a collection of restaurants saved by a user.
 */
public class RestaurantList {
    private int restaurantListId;
    private int userId;
    private String name;
    private String description;
    private List<RestaurantListItem> restaurantItems; // List of restaurant list items

    /**
     * @param restaurantListId the unique identifier for the restaurant list
     * @param userId           the ID of the user who created the restaurant list
     * @param name             the name of the restaurant list
     * @param description      the description of the restaurant list (optional)
     * @param restaurantItems  the list of restaurant list items in the restaurant list
     */
    public RestaurantList(int restaurantListId, int userId, String name, String description, List<RestaurantListItem> restaurantItems) {
        this.restaurantListId = restaurantListId;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.restaurantItems = restaurantItems;
    }

    public int getRestaurantListId() {
        return restaurantListId;
    }

    public int getUserId() {
        return userId;
    }
    
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
    public List<RestaurantListItem> getRestaurantItems() {
        return restaurantItems;
    }

    public void setRestaurantListId(int restaurantListId) {
        this.restaurantListId = restaurantListId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public void setRestaurantItems(List<RestaurantListItem> restaurantItems) {
        this.restaurantItems = restaurantItems;
    }
    
    @Override
    public String toString() {
        return "RestaurantList{" +
                "restaurantListId=" + restaurantListId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", restaurantItems=" + restaurantItems +
                '}';
    }
}
