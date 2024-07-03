package entity;

public class SearchQuery {
    // Attribute to store the input keywords for restaurant search
    private String keyword;

    // Constructor
    public SearchQuery(String keyword) {
        this.keyword = keyword;
    }

    // Getter for keyword
    public String getKeyword() {
        return keyword;
    }

    // Setter for keyword
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return "SearchQuery{" +
                "keyword='" + keyword + '\'' +
                '}';
    }

