package main.java.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchResultTest {

    private SearchResult searchResult;
    private List<Integer> restaurantIds;

    @BeforeEach
    void setUp() {
        restaurantIds = Arrays.asList(101, 102, 103);
        searchResult = new SearchResult(1, 10, restaurantIds);
    }

    @AfterEach
    void tearDown() {
        searchResult = null;
        restaurantIds = null;
    }

    @Test
    void getSearchResultId() {
        assertEquals(1, searchResult.getSearchResultId());
    }

    @Test
    void getSearchQueryId() {
        assertEquals(10, searchResult.getSearchQueryId());
    }

    @Test
    void getRestaurantIds() {
        assertEquals(restaurantIds, searchResult.getRestaurantIds());
    }

    @Test
    void setSearchResultId() {
        searchResult.setSearchResultId(2);
        assertEquals(2, searchResult.getSearchResultId());
    }

    @Test
    void setSearchQueryId() {
        searchResult.setSearchQueryId(20);
        assertEquals(20, searchResult.getSearchQueryId());
    }

    @Test
    void setRestaurantIds() {
        List<Integer> newRestaurantIds = Arrays.asList(201, 202, 203);
        searchResult.setRestaurantIds(newRestaurantIds);
        assertEquals(newRestaurantIds, searchResult.getRestaurantIds());
    }

    @Test
    void testToString() {
        String expected = "SearchResults{searchResultId=1, searchQueryId=10, restaurantIds=[101, 102, 103]}";
        assertEquals(expected, searchResult.toString());
    }
}
