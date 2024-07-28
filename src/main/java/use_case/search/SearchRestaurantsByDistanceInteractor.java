package use_case.search;

import entity.Restaurant;
import domain.SearchRestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Interactor implementation for searching restaurants based on distance and dish type criteria.
 * This class performs the search operation by interacting with the SearchRestaurantService.
 */
public class SearchRestaurantsByDistanceInteractor implements SearchRestaurantsByDistance {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchRestaurantsByDistanceInteractor.class);
    private final SearchRestaurantService searchRestaurantService;

    /**
     * Constructs a new SearchRestaurantsByDistanceInteractor with the specified SearchRestaurantService.
     *
     * @param searchRestaurantService the service used to perform the restaurant search
     */
    public SearchRestaurantsByDistanceInteractor(SearchRestaurantService searchRestaurantService) {
        this.searchRestaurantService = searchRestaurantService;
    }

    /**
     * Executes the search for restaurants based on the provided search input parameters and maximum number of results.
     * The search is performed based on the latitude, longitude, distance, and dish type provided in the
     * {@code RestaurantSearchInput} object. The number of results returned is limited by the {@code maxResults} parameter.
     *
     * @param restaurantSearchInput the search input parameters including location (latitude and longitude), distance, and dish type
     * @param maxResults            the maximum number of search results to return
     * @return an Optional containing a list of restaurants matching the search criteria, or an empty Optional if no matches are found
     */
    @Override
    public Optional<List<Restaurant>> execute(RestaurantSearchInput restaurantSearchInput, int maxResults) {
        try {
            // Perform the search for restaurants using the provided search service
            Optional<List<Restaurant>> result = searchRestaurantService.execute(restaurantSearchInput, maxResults);
            List<Restaurant> restaurants = result.orElse(new ArrayList<>());

            // Output the search result details
            System.out.println("Search completed. Found " + restaurants.size() + " restaurant(s) within a radius of "
                    + restaurantSearchInput.getDistance() + " meters from location ("
                    + restaurantSearchInput.getLatitude() + ", " + restaurantSearchInput.getLongitude() + ").");

            return Optional.of(restaurants);
        } catch (Exception e) {
            System.err.println("An error occurred during the restaurant search: " + e.getMessage());
            LOGGER.error("An error occurred during the restaurant search: {}", e.getMessage(), e);
            return Optional.empty();
        }
    }
}
