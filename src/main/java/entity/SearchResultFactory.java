package entity;

import java.util.List;

/**
 * Factory class for creating SearchResult instances.
 */
public class SearchResultFactory {

    /**
     * @param searchResultId the unique search result identifier
     * @param searchQueryId  the ID of the search query that generated these results
     * @param restaurantIds  the list of restaurant IDs matching the search criteria
     * @return a new SearchResult object
     */
    public static SearchResult createSearchResult(int searchResultId, int searchQueryId, List<Integer> restaurantIds) {
        return new SearchResult(searchResultId, searchQueryId, restaurantIds);
    }
}
