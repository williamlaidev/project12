package interface_adapter.search;

import use_case.search.RestaurantSearchInteractor;
import use_case.search.SearchRestaurantInput;
import entity.restaurant.Restaurant;
import entity.DishType;

import java.util.List;

/**
 * Controller for handling restaurant search requests.
 */
public class RestaurantSearchController {

    private final RestaurantSearchInteractor restaurantSearchInteractor;

    /**
     * Constructs a RestaurantSearchController with the given RestaurantSearchInteractor.
     *
     * @param restaurantSearchInteractor Use case for searching restaurants
     */
    public RestaurantSearchController(RestaurantSearchInteractor restaurantSearchInteractor) {
        this.restaurantSearchInteractor = restaurantSearchInteractor;
    }

    /**
     * Searches for restaurants based on the given parameters.
     *
     * @param latitude                  Latitude of the search location
     * @param longitude                 Longitude of the search location
     * @param distance                  Search radius in meters
     * @param dishType                  Dish type filter
     * @param maxRestaurantsToSearch    Maximum number of restaurants to search
     * @param maxResults                Maximum number of results to return
     * @return List of restaurants found in the search
     */
    public List<Restaurant> searchRestaurants(double latitude, double longitude, String distance, DishType dishType, int maxRestaurantsToSearch, int maxResults) {
        SearchRestaurantInput searchInput = new SearchRestaurantInput(latitude, longitude, distance, dishType);
        return restaurantSearchInteractor.fetchNearbyRestaurants(searchInput, maxRestaurantsToSearch, maxResults);
    }
}
