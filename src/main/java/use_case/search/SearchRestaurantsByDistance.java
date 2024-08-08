package use_case.search;

import domain.RestaurantSearchService;
import entity.restaurant.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements {@link SearchRestaurants} to find restaurants based on distance.
 * Uses a {@link RestaurantSearchService} to perform the search.
 */
public class SearchRestaurantsByDistance implements SearchRestaurants {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchRestaurantsByDistance.class);
    private final RestaurantSearchService restaurantSearchService;

    /**
     * Constructs an instance with the given search service.
     *
     * @param restaurantSearchService the service used to search for restaurants.
     */
    public SearchRestaurantsByDistance(RestaurantSearchService restaurantSearchService) {
        this.restaurantSearchService = restaurantSearchService;
    }

    @Override
    public List<Restaurant> execute(SearchRestaurantInput searchInput, int maxRestaurantsToSearch, int maxResults) {
        try {
            List<Restaurant> restaurants = restaurantSearchService.fetchNearbyRestaurants(searchInput, maxRestaurantsToSearch, maxResults);

            if (restaurants.size() > maxResults) {
                restaurants = restaurants.subList(0, maxResults);
            }

            LOGGER.info("Search completed. Found {} restaurant(s) within a radius of {} meters from location ({}, {}).",
                    restaurants.size(), searchInput.getDistance(), searchInput.getLatitude(), searchInput.getLongitude());

            return restaurants;
        } catch (Exception e) {
            LOGGER.error("An error occurred during the restaurant search: {}", e.getMessage(), e);
            return new ArrayList<>();
        }
    }
}
