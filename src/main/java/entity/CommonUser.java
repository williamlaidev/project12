package main.java.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents a common user of the application.
 */
public class CommonUser implements User {
    private int id;
    private String name;
    private Location location;
    private LocalDateTime creationTime;
    private ArrayList<RestaurantList> savedRestaurantList;

    /**
     * Constructs a new CommonUser object.
     *
     * @param name         The name of the user.
     * @param location     The location of the user.
     * @param creationTime The creation time of the user.
     * @throws IllegalArgumentException if any parameter constraints are violated
     */
    public CommonUser(int id, String name, Location location, LocalDateTime creationTime) {
        validateName(name);

        this.id = id;
        this.name = name;
        this.location = location;
        this.creationTime = creationTime;
        this.savedRestaurantList = new ArrayList<>();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    @Override
    public ArrayList<RestaurantList> getSavedRestaurantList() {
        return savedRestaurantList;
    }

    @Override
    public void resetLocation(Location location) {
        this.location = location;
    }

    public void addSavedRestaurantList(RestaurantList restaurantList) {
        savedRestaurantList.add(restaurantList);
    }

    public boolean removeSavedRestaurantList(RestaurantList restaurantList) {
        return savedRestaurantList.remove(restaurantList);
    }

    @Override
    public String toString() {
        return "CommonUser{" +
                "name='" + name + '\'' +
                ", location=" + location +
                ", creationTime=" + creationTime +
                ", savedRestaurantList=" + savedRestaurantList +
                '}';
    }

    // Private helper methods for validation
    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("User name cannot be null or empty.");
        }
    }
}