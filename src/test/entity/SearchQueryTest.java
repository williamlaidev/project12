package test.entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.java.entity.SearchQuery;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SearchQueryTest {

    private SearchQuery searchQuery;

    @BeforeEach
    public void setUp() {
        // Initialize a SearchQuery instance before each test
        int searchQueryId = 1;
        int userId = 123;
        String keywords = "pizza";
        List<String> filters = new ArrayList<>();
        filters.add("open");
        filters.add("delivery");

        searchQuery = new SearchQuery(searchQueryId, userId, keywords, filters);
    }

    @Test
    public void testGetters() {
        assertEquals(1, searchQuery.getSearchQueryId());
        assertEquals(123, searchQuery.getUserId());
        assertEquals("pizza", searchQuery.getKeywords());
        assertEquals(List.of("open", "delivery"), searchQuery.getFilters());
    }

    @Test
    public void testSetters() {
        searchQuery.setSearchQueryId(2);
        searchQuery.setUserId(456);
        searchQuery.setKeywords("sushi");
        List<String> newFilters = new ArrayList<>();
        newFilters.add("vegetarian");
        newFilters.add("pickup");
        searchQuery.setFilters(newFilters);

        assertEquals(2, searchQuery.getSearchQueryId());
        assertEquals(456, searchQuery.getUserId());
        assertEquals("sushi", searchQuery.getKeywords());
        assertEquals(List.of("vegetarian", "pickup"), searchQuery.getFilters());
    }

    @Test
    public void testToString() {
        String expected = "SearchQuery{searchQueryId=1, userId=123, keywords='pizza', filters=[open, delivery]}";
        assertEquals(expected, searchQuery.toString());
    }
}