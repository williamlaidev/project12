package use_case.search;

import entity.Restaurant;

import java.util.List;
import java.util.Optional;

/**
 * Interface for searching restaurants based on distance and dish type criteria.
 * This interface defines the contract for the use case that performs the search operation.
 */
public interface SearchRestaurantsByDistance {
    /**
     * Executes the search for restaurants based on the provided search input parameters and maximum number of results.
     * The search is performed based on the latitude, longitude, distance, and dish type provided in the
     * {@code RestaurantSearchInput} object. The number of results returned is limited by the {@code maximumResults} parameter.
     *
     * @param restaurantSearchInput the search input parameters including location (latitude and longitude), distance, and dish type
     * @param maximumResults the maximum number of search results to return
     * @return an Optional containing a list of restaurants matching the search criteria, or an empty Optional if no matches are found
     */
    Optional<List<Restaurant>> execute(RestaurantSearchInput restaurantSearchInput, int maximumResults);
}
