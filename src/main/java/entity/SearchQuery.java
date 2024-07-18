package entity;

import java.util.List;

/**
 * Represents a search query made by a user.
 */
public class SearchQuery {
    private int searchQueryId;
    private int userId;
    private String keywords;
    private List<String> filters; // Serialized string or JSON representation

    /**
     * @param searchQueryId the unique search query identifier
     * @param userId        the ID of the user who made the search query
     * @param keywords      the keywords used in the search query
     * @param filters       the filters applied in the search query (serialized string or JSON)
     */
    public SearchQuery(int searchQueryId, int userId, String keywords, List<String> filters) {
        this.searchQueryId = searchQueryId;
        this.userId = userId;
        this.keywords = keywords;
        this.filters = filters;
    }

    public int getSearchQueryId() {
        return searchQueryId;
    }

    public int getUserId() {
        return userId;
    }

    public String getKeywords() {
        return keywords;
    }

    public List<String> getFilters() {
        return filters;
    }

    public void setSearchQueryId(int searchQueryId) {
        this.searchQueryId = searchQueryId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setFilters(List<String> filters) {
        this.filters = filters;
    }

    @Override
    public String toString() {
        return "SearchQuery{" +
                "searchQueryId=" + searchQueryId +
                ", userId=" + userId +
                ", keywords='" + keywords + '\'' +
                ", filters=" + filters +
                '}';
    }
}
