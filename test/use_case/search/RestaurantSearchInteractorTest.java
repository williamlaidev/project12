package use_case.search;

import domain.RestaurantSearchService;
import entity.DishType;
import entity.restaurant.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RestaurantSearchInteractorTest {

    @Mock
    private SearchRestaurants searchStrategy;

    @InjectMocks
    private RestaurantSearchInteractor restaurantSearchInteractor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFetchNearbyRestaurants_ValidInput() {
        SearchRestaurantInput searchInput = new SearchRestaurantInput(40.7128, -74.0060, "500", DishType.AMERICAN);
        int maxRestaurantsToSearch = 10;
        int maxResults = 5;
        Restaurant restaurant1 = new Restaurant("1", "Restaurant1", null, "Address1", DishType.AMERICAN, 4.5, null);
        Restaurant restaurant2 = new Restaurant("2", "Restaurant2", null, "Address2", DishType.AMERICAN, 4.0, null);
        List<Restaurant> expectedRestaurants = new ArrayList<>();

        when(searchStrategy.execute(searchInput, maxRestaurantsToSearch, maxResults)).thenReturn(expectedRestaurants);

        List<Restaurant> actualRestaurants = restaurantSearchInteractor.fetchNearbyRestaurants(searchInput, maxRestaurantsToSearch, maxResults);

        assertEquals(expectedRestaurants, actualRestaurants);
    }

    @Test
    public void testFetchNearbyRestaurants_InvalidDistanceFormat() {
        SearchRestaurantInput searchInput = new SearchRestaurantInput(40.7128, -74.0060, "invalid", DishType.AMERICAN);
        int maxRestaurantsToSearch = 10;
        int maxResults = 5;

        List<Restaurant> actualRestaurants = restaurantSearchInteractor.fetchNearbyRestaurants(searchInput, maxRestaurantsToSearch, maxResults);

        assertEquals(new ArrayList<>(), actualRestaurants);
    }

    @Test
    public void testFetchNearbyRestaurants_ExceptionInSearchStrategy() {
        SearchRestaurantInput searchInput = new SearchRestaurantInput(40.7128, -74.0060, "500", DishType.AMERICAN);
        int maxRestaurantsToSearch = 10;
        int maxResults = 5;

        when(searchStrategy.execute(searchInput, maxRestaurantsToSearch, maxResults)).thenThrow(new RuntimeException("Search strategy error"));

        List<Restaurant> actualRestaurants = restaurantSearchInteractor.fetchNearbyRestaurants(searchInput, maxRestaurantsToSearch, maxResults);

        assertEquals(new ArrayList<>(), actualRestaurants);
    }

    @Test
    public void testFetchNearbyRestaurants_EmptyResults() {
        SearchRestaurantInput searchInput = new SearchRestaurantInput(40.7128, -74.0060, "500", DishType.AMERICAN);
        int maxRestaurantsToSearch = 10;
        int maxResults = 5;

        when(searchStrategy.execute(searchInput, maxRestaurantsToSearch, maxResults)).thenReturn(new ArrayList<>());

        List<Restaurant> actualRestaurants = restaurantSearchInteractor.fetchNearbyRestaurants(searchInput, maxRestaurantsToSearch, maxResults);

        assertEquals(new ArrayList<>(), actualRestaurants);
    }
}
