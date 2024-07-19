package interface_adapter;

import entity.Location;
import entity.Restaurant;
import use_case.SearchRestaurantsByDistance;
import use_case.SearchRestaurantsByDishType;
import java.util.List;

public class NearbyRestaurantController {

    private final SearchRestaurantsByDistance searchRestaurantsByDistance;
    private final SearchRestaurantsByDishType searchRestaurantsByDishType;

    public NearbyRestaurantController(SearchRestaurantsByDistance searchRestaurantsByDistance, SearchRestaurantsByDishType searchRestaurantsByDishType) {
        this.searchRestaurantsByDistance = searchRestaurantsByDistance;
        this.searchRestaurantsByDishType = searchRestaurantsByDishType;
    }

    public List<Restaurant> getNearbyRestaurantsByDistance(Location location, int radius, int maxResults) {
        return searchRestaurantsByDistance.search(location, radius, maxResults);
    }

    public List<Restaurant> getNearbyRestaurantsByDishType(Location location, String dishType, int radius, int maxResults) {
        return searchRestaurantsByDishType.search(location, dishType, radius, maxResults);
    }
}
