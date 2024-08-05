package interface_adapter.search;

import use_case.search.RestaurantSearchInteractor;
import use_case.search.SearchRestaurantInput;
import entity.restaurant.Restaurant;
import entity.DishType;

import java.util.List;

public class RestaurantSearchController {

    private final RestaurantSearchInteractor restaurantSearchInteractor;

    public RestaurantSearchController(RestaurantSearchInteractor restaurantSearchInteractor) {
        this.restaurantSearchInteractor = restaurantSearchInteractor;
    }

    public List<Restaurant> searchRestaurants(double latitude, double longitude, String distance, DishType dishType, int maxRestaurantsToSearch, int maxResults) {
        SearchRestaurantInput searchInput = new SearchRestaurantInput(latitude, longitude, distance, dishType);
        return restaurantSearchInteractor.fetchNearbyRestaurants(searchInput, maxRestaurantsToSearch, maxResults);
    }
}