package main.java.entity;
import java.util.List;

/**
 * Factory class for creating SearchQuery instances.
 */
public class SearchQueryFactory {

    /**
     * @param searchQueryId the unique search query identifier
     * @param userId        the ID of the user who made the search query
     * @param keywords      the keywords used in the search query
     * @param filters       the filters applied in the search query (serialized string or JSON)
     * @return a new SearchQuery object
     */
    public static SearchQuery createSearchQuery(int searchQueryId, int userId, String keywords, List<String> filters) {
        return new SearchQuery(searchQueryId, userId, keywords, filters);
    }
}
