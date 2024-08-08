package use_case.search;

import entity.restaurant.Restaurant;
import java.util.List;

/**
 * Defines the interface for searching restaurants.
 */
public interface SearchRestaurants {

    /**
     * Executes a search for restaurants based on the input criteria.
     *
     * @param restaurantSearchInput the input parameters for the search.
     * @param maxRestaurantsToSearch the maximum number of restaurants to search.
     * @param maximumResults the maximum number of results to return.
     * @return a list of {@link Restaurant} that match the search criteria.
     */
    List<Restaurant> execute(SearchRestaurantInput restaurantSearchInput, int maxRestaurantsToSearch, int maximumResults);
}
