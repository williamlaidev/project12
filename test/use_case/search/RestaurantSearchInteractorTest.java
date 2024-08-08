package use_case.search;

import entity.DishType;
import entity.location.Location;
import entity.restaurant.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RestaurantSearchInteractorTest {

    private SearchRestaurants searchStrategy;
    private RestaurantSearchInteractor interactor;

    @BeforeEach
    public void setUp() {
        searchStrategy = mock(SearchRestaurants.class);
        interactor = new RestaurantSearchInteractor(searchStrategy);
    }

    @Test
    public void testFetchNearbyRestaurantsInvalidDistance() {
        SearchRestaurantInput input = new SearchRestaurantInput(37.7749, -122.4194, "invalid", DishType.PIZZA);

        List<Restaurant> result = interactor.fetchNearbyRestaurants(input, 10, 5);
        assertTrue(result.isEmpty());
    }
}
