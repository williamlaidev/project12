package main.entity;
import java.util.List;

/**
 * Represents the search results containing a list of restaurants matching the search criteria.
 */
public class SearchResult {
    private int searchResultId;
    private int searchQueryId;
    private List<Integer> restaurantIds; // List of restaurant IDs matching the search criteria

    /**
     * Constructs a new SearchResults with the specified details.
     *
     * @param searchResultId the unique search result identifier
     * @param searchQueryId  the ID of the search query that generated these results
     * @param restaurantIds  the list of restaurant IDs matching the search criteria
     */
    public SearchResult(int searchResultId, int searchQueryId, List<Integer> restaurantIds) {
        this.searchResultId = searchResultId;
        this.searchQueryId = searchQueryId;
        this.restaurantIds = restaurantIds;
    }

    public int getSearchResultId() {
        return searchResultId;
    }

    public int getSearchQueryId() {
        return searchQueryId;
    }

    public List<Integer> getRestaurantIds() {
        return restaurantIds;
    }

    public void setSearchResultId(int searchResultId) {
        this.searchResultId = searchResultId;
    }

    public void setSearchQueryId(int searchQueryId) {
        this.searchQueryId = searchQueryId;
    }

    public void setRestaurantIds(List<Integer> restaurantIds) {
        this.restaurantIds = restaurantIds;
    }


    @Override
    public String toString() {
        return "SearchResults{" +
                "searchResultId=" + searchResultId +
                ", searchQueryId=" + searchQueryId +
                ", restaurantIds=" + restaurantIds +
                '}';
    }
}