package use_case.search;

import domain.RestaurantSearchService;
import entity.restaurant.Restaurant;
import entity.DishType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SearchRestaurantsByDistanceTest {

    @Mock
    private RestaurantSearchService restaurantSearchService;

    @InjectMocks
    private SearchRestaurantsByDistance searchRestaurantsByDistance;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecuteWithMoreRestaurantsThanMaxResults() {
        SearchRestaurantInput searchInput = new SearchRestaurantInput(40.7128, -74.0060, "500", DishType.AMERICAN);
        int maxRestaurantsToSearch = 10;
        int maxResults = 5;
        List<Restaurant> restaurants = Arrays.asList(
                new Restaurant("1", "Restaurant1", null, "Address1", DishType.AMERICAN, 4.5, null),
                new Restaurant("2", "Restaurant2", null, "Address2", DishType.AMERICAN, 4.0, null),
                new Restaurant("3", "Restaurant3", null, "Address3", DishType.AMERICAN, 3.5, null),
                new Restaurant("4", "Restaurant4", null, "Address4", DishType.AMERICAN, 3.0, null),
                new Restaurant("5", "Restaurant5", null, "Address5", DishType.AMERICAN, 2.5, null),
                new Restaurant("6", "Restaurant6", null, "Address6", DishType.AMERICAN, 4.2, null)
        );

        when(restaurantSearchService.fetchNearbyRestaurants(searchInput, maxRestaurantsToSearch, maxResults)).thenReturn(restaurants);

        List<Restaurant> actualRestaurants = searchRestaurantsByDistance.execute(searchInput, maxRestaurantsToSearch, maxResults);

        assertEquals(5, actualRestaurants.size());
    }

    @Test
    public void testExecuteWithNoRestaurants() {
        SearchRestaurantInput searchInput = new SearchRestaurantInput(40.7128, -74.0060, "500", DishType.AMERICAN);
        int maxRestaurantsToSearch = 10;
        int maxResults = 5;

        when(restaurantSearchService.fetchNearbyRestaurants(searchInput, maxRestaurantsToSearch, maxResults)).thenReturn(Collections.emptyList());

        List<Restaurant> actualRestaurants = searchRestaurantsByDistance.execute(searchInput, maxRestaurantsToSearch, maxResults);

        assertEquals(0, actualRestaurants.size());
    }

    @Test
    public void testExecuteHandlesException() {
        SearchRestaurantInput searchInput = new SearchRestaurantInput(40.7128, -74.0060, "500", DishType.AMERICAN);
        int maxRestaurantsToSearch = 10;
        int maxResults = 5;

        when(restaurantSearchService.fetchNearbyRestaurants(searchInput, maxRestaurantsToSearch, maxResults))
                .thenThrow(new RuntimeException("Database error"));

        List<Restaurant> actualRestaurants = searchRestaurantsByDistance.execute(searchInput, maxRestaurantsToSearch, maxResults);

        assertEquals(0, actualRestaurants.size());
    }
}
