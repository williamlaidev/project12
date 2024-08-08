package use_case.search;

import domain.RestaurantSearchService;
import entity.DishType;
import entity.location.Location;
import entity.restaurant.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SearchRestaurantsByDistanceTest {

    private RestaurantSearchService restaurantSearchService;
    private SearchRestaurantsByDistance searchRestaurantsByDistance;

    @BeforeEach
    public void setUp() {
        restaurantSearchService = mock(RestaurantSearchService.class);
        searchRestaurantsByDistance = new SearchRestaurantsByDistance(restaurantSearchService);
    }

    @Test
    public void testExecuteSuccess() {
        SearchRestaurantInput input = new SearchRestaurantInput(37.7749, -122.4194, "1000", DishType.PIZZA);
        int maxRestaurantsToSearch = 10;
        int maxResults = 5;
        List<Restaurant> expectedRestaurants = List.of(
                new Restaurant("id1", "Restaurant 1", new Location(37.7749, -122.4194), "123 Main St", DishType.PIZZA, 4.5, ""),
                new Restaurant("id2", "Restaurant 2", new Location(37.7750, -122.4195), "456 Main St", DishType.PIZZA, 4.0, "")
        );

        when(restaurantSearchService.fetchNearbyRestaurants(input, maxRestaurantsToSearch, maxResults)).thenReturn(expectedRestaurants);

        List<Restaurant> result = searchRestaurantsByDistance.execute(input, maxRestaurantsToSearch, maxResults);
        assertEquals(expectedRestaurants, result);
    }

    @Test
    public void testExecuteFailure() {
        SearchRestaurantInput input = new SearchRestaurantInput(37.7749, -122.4194, "1000", DishType.PIZZA);

        when(restaurantSearchService.fetchNearbyRestaurants(input, 10, 5)).thenThrow(new RuntimeException("Search failed"));

        List<Restaurant> result = searchRestaurantsByDistance.execute(input, 10, 5);
        assertTrue(result.isEmpty());
    }
}
