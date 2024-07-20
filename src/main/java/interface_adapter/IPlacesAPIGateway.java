package interface_adapter;

import entity.Location;
import entity.Restaurant;
import java.util.List;

public interface IPlacesAPIGateway {
    Location getCurrentLocation() throws Exception;
    List<Restaurant> getNearbyRestaurants(Location location, int radius, int maxResults) throws Exception;
    List<Restaurant> getNearbyRestaurantsByDishType(Location location, String dishType, int radius, int maxResults) throws Exception;
}
