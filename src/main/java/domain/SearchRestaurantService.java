package domain;

import entity.Restaurant;
import use_case.search.RestaurantSearchInput;

import java.util.List;
import java.util.Optional;

/**
 * The SearchRestaurantService interface defines the contract for a service that
 * performs searches for restaurants based on specific criteria.
 */
public interface SearchRestaurantService {

    /**
     * Executes a search for restaurants based on the given input criteria.
     *
     * @param restaurantSearchInput The input criteria for searching restaurants.
     * @param maxResults The maximum number of results to return.
     * @return An Optional containing a List of Restaurants that match the search criteria,
     *         or an empty Optional if no matches are found.
     * @throws Exception if an error occurs during the search process.
     */
    Optional<List<Restaurant>> execute(RestaurantSearchInput restaurantSearchInput, int maxResults) throws Exception;
}
