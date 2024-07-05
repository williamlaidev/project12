package main.java.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchQueryTest {

    private SearchQuery searchQuery;
    private List<String> filters;

    @BeforeEach
    void setUp() {
        filters = Arrays.asList("filter1", "filter2");
        searchQuery = new SearchQuery(1, 100, "sushi", filters);
    }

    @AfterEach
    void tearDown() {
        searchQuery = null;
        filters = null;
    }

    @Test
    void getSearchQueryId() {
        assertEquals(1, searchQuery.getSearchQueryId());
    }

    @Test
    void getUserId() {
        assertEquals(100, searchQuery.getUserId());
    }

    @Test
    void getKeywords() {
        assertEquals("sushi", searchQuery.getKeywords());
    }

    @Test
    void getFilters() {
        assertEquals(filters, searchQuery.getFilters());
    }

    @Test
    void setSearchQueryId() {
        searchQuery.setSearchQueryId(2);
        assertEquals(2, searchQuery.getSearchQueryId());
    }

    @Test
    void setUserId() {
        searchQuery.setUserId(200);
        assertEquals(200, searchQuery.getUserId());
    }

    @Test
    void setKeywords() {
        searchQuery.setKeywords("ramen");
        assertEquals("ramen", searchQuery.getKeywords());
    }

    @Test
    void setFilters() {
        List<String> newFilters = Arrays.asList("filter3", "filter4");
        searchQuery.setFilters(newFilters);
        assertEquals(newFilters, searchQuery.getFilters());
    }

    @Test
    void testToString() {
        String expected = "SearchQuery{searchQueryId=1, userId=100, keywords='sushi', filters=[filter1, filter2]}";
        assertEquals(expected, searchQuery.toString());
    }
}
