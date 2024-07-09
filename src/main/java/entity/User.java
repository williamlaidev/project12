package main.java.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface User {

    String getName();

    Location getLocation();

    LocalDateTime getCreationTime();

    ArrayList<RestaurantList> getSavedRestaurantList();

    void setLocation(Location location);

    void addSavedRestaurantList(RestaurantList restaurantList);

    boolean removeSavedRestaurantList(RestaurantList restaurantList);
}
