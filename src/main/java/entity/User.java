package main.java.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface User {

    int getId();

    String getName();

    Location getLocation();

    LocalDateTime getCreationTime();

    ArrayList<RestaurantList> getSavedRestaurantList();

    void resetLocation(Location location);

    void addSavedRestaurantList(RestaurantList restaurantList);

    boolean removeSavedRestaurantList(RestaurantList restaurantList);
}
