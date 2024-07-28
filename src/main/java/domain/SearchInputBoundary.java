package domain;

import entity.Restaurant;
import use_case.view.SearchInput;

import java.util.List;
import java.util.Optional;

/**
 * Defines the boundary interface for searching restaurants based on user input.
 */
public interface SearchInputBoundary {
    /**
     * Searches for restaurants based on the provided search input parameters and limits the number of results.
     *
     * @param searchInput the input parameters for the search, including location, search radius, and dish type
     * @param maxResults  the maximum number of restaurant results to return
     * @return an {@code Optional} containing a list of restaurants that match the search criteria, or an empty {@code Optional} if no results are found or if an error occurs
     * @throws Exception if an error occurs during the search process
     */
    Optional<List<Restaurant>> execute(SearchInput searchInput, int maxResults) throws Exception;
}
