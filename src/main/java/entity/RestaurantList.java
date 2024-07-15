package main.java.entity;

import java.util.List;

/**
 * Represents a collection of restaurants saved by a user.
 */
public class RestaurantList {
    private int restaurantListId;
    private User user; // Changed to User type
    private String name;
    private String description;
    private List<Restaurant> restaurants; // List of restaurants in the restaurant list

    /**
     * Constructs a RestaurantList object.
     *
     * @param restaurantListId the unique identifier for the restaurant list
     * @param user             the User object who created the restaurant list
     * @param name             the name of the restaurant list
     * @param description      the description of the restaurant list (optional)
     * @param restaurants      the list of restaurants in the restaurant list
     * @throws IllegalArgumentException if any parameter constraints are violated
     */
    public RestaurantList(int restaurantListId, User user, String name, String description, List<Restaurant> restaurants) {
        validateRestaurantListId(restaurantListId);
        validateUser(user);
        validateName(name);

        this.restaurantListId = restaurantListId;
        this.user = user;
        this.name = name;
        this.description = (description == null) ? "" : description;
        this.restaurants = restaurants;
    }

    public int getRestaurantListId() {
        return restaurantListId;
    }

    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurantListId(int restaurantListId) {
        validateRestaurantListId(restaurantListId);
        this.restaurantListId = restaurantListId;
    }

    public void setUser(User user) {
        validateUser(user);
        this.user = user;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = (description == null) ? "" : description;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public String toString() {
        return "RestaurantList{" +
                "restaurantListId=" + restaurantListId +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", restaurants=" + restaurants +
                '}';
    }

    // Private helper methods for validation
    private void validateRestaurantListId(int restaurantListId) {
        if (restaurantListId < 0) {
            throw new IllegalArgumentException("Restaurant list ID cannot be negative.");
        }
    }

    private void validateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Restaurant list name cannot be empty or null.");
        }
    }
}
