package test.entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.entity.SearchResult;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SearchResultTest {

    private SearchResult searchResult;

    @BeforeEach
    public void setUp() {
        // Initialize a SearchResult instance before each test
        int searchResultId = 1;
        int searchQueryId = 123;
        List<Integer> restaurantIds = Arrays.asList(101, 102, 103);

        searchResult = new SearchResult(searchResultId, searchQueryId, restaurantIds);
    }

    @Test
    public void testGetters() {
        assertEquals(1, searchResult.getSearchResultId());
        assertEquals(123, searchResult.getSearchQueryId());
        assertEquals(Arrays.asList(101, 102, 103), searchResult.getRestaurantIds());
    }

    @Test
    public void testSetters() {
        searchResult.setSearchResultId(2);
        searchResult.setSearchQueryId(456);
        List<Integer> newRestaurantIds = Arrays.asList(201, 202);

        searchResult.setRestaurantIds(newRestaurantIds);

        assertEquals(2, searchResult.getSearchResultId());
        assertEquals(456, searchResult.getSearchQueryId());
        assertEquals(Arrays.asList(201, 202), searchResult.getRestaurantIds());
    }

    @Test
    public void testToString() {
        String expected = "SearchResults{searchResultId=1, searchQueryId=123, restaurantIds=[101, 102, 103]}";
        assertEquals(expected, searchResult.toString());
    }
}