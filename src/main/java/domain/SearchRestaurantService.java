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
     * @param maxResults            The maximum number of results to return.
     * @throws Exception if an error occurs during the search process.
     */
    void execute(RestaurantSearchInput restaurantSearchInput, int maxResults) throws Exception;

    Optional<List<Restaurant>> getRestaurants() throws Exception;
}
