package domain;

import entity.restaurant.Restaurant;
import use_case.search.SearchRestaurantInput;

import java.util.List;

public interface RestaurantSearchService {
    /**
     * Searches for restaurants based on the provided search input parameters and limits.
     *
     * @param searchInput the search input parameters including location (latitude and longitude), distance, and dish type
     * @param maxRestaurantsToSearch the maximum number of restaurants to be searched and compared with the criteria
     * @param maxResults the maximum number of search results to return
     * @return a list of restaurants matching the search criteria
     */
    List<Restaurant> fetchNearbyRestaurants(SearchRestaurantInput searchInput, int maxRestaurantsToSearch, int maxResults);
}
