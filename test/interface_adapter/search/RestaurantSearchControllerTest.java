package interface_adapter.search;

import entity.DishType;
import entity.location.Location;
import entity.restaurant.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.search.RestaurantSearchInteractor;
import use_case.search.SearchRestaurantInput;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RestaurantSearchControllerTest {

    private RestaurantSearchController restaurantSearchController;
    private RestaurantSearchInteractor mockRestaurantSearchInteractor;

    @BeforeEach
    public void setUp() {
        mockRestaurantSearchInteractor = mock(RestaurantSearchInteractor.class);
        restaurantSearchController = new RestaurantSearchController(mockRestaurantSearchInteractor);
    }

    @Test
    public void testSearchRestaurants() {
        double latitude = 37.7749;
        double longitude = -122.4194;
        String distance = "500";
        DishType dishType = DishType.CHINESE;
        int maxRestaurantsToSearch = 10;
        int maxResults = 5;

        Location location = new Location(latitude, longitude);
        Restaurant mockRestaurant = new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Test Restaurant", location, "123 Test St", dishType, 1.2, "http://example.com/photo.jpg");
        List<Restaurant> mockRestaurants = List.of(mockRestaurant);

        when(mockRestaurantSearchInteractor.fetchNearbyRestaurants(any(SearchRestaurantInput.class), any(Integer.class), any(Integer.class)))
                .thenReturn(mockRestaurants);

        List<Restaurant> result = restaurantSearchController.searchRestaurants(latitude, longitude, distance, dishType, maxRestaurantsToSearch, maxResults);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mockRestaurant, result.get(0));
    }

    @Test
    public void testSearchRestaurantsNoResults() {
        double latitude = 37.7749;
        double longitude = -122.4194;
        String distance = "500";
        DishType dishType = DishType.CHINESE;
        int maxRestaurantsToSearch = 10;
        int maxResults = 5;

        when(mockRestaurantSearchInteractor.fetchNearbyRestaurants(any(SearchRestaurantInput.class), any(Integer.class), any(Integer.class)))
                .thenReturn(List.of());

        List<Restaurant> result = restaurantSearchController.searchRestaurants(latitude, longitude, distance, dishType, maxRestaurantsToSearch, maxResults);

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testSearchRestaurantsNullDishType() {
        double latitude = 37.7749;
        double longitude = -122.4194;
        String distance = "500";
        DishType dishType = null;
        int maxRestaurantsToSearch = 10;
        int maxResults = 5;

        Location location = new Location(latitude, longitude);
        Restaurant mockRestaurant = new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Test Restaurant", location, "123 Test St", DishType.AMERICAN, 1.2, "http://example.com/photo.jpg");
        List<Restaurant> mockRestaurants = List.of(mockRestaurant);

        when(mockRestaurantSearchInteractor.fetchNearbyRestaurants(any(SearchRestaurantInput.class), any(Integer.class), any(Integer.class)))
                .thenReturn(mockRestaurants);

        List<Restaurant> result = restaurantSearchController.searchRestaurants(latitude, longitude, distance, dishType, maxRestaurantsToSearch, maxResults);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mockRestaurant, result.get(0));
    }
}
