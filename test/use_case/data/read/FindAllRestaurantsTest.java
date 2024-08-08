package use_case.data.read;

import domain.RestaurantRepository;
import entity.restaurant.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindAllRestaurantsTest {

    private RestaurantRepository restaurantRepository;
    private FindAllRestaurants findAllRestaurants;

    @BeforeEach
    public void setUp() {
        restaurantRepository = mock(RestaurantRepository.class);
        findAllRestaurants = new FindAllRestaurants(restaurantRepository);
    }

    @Test
    public void testExecute() {
        List<Restaurant> expectedRestaurants = List.of(
                new Restaurant("id1", "Test Restaurant 1", null, "123 Main St", null, 4.5, ""),
                new Restaurant("id2", "Test Restaurant 2", null, "456 Main St", null, 4.0, "")
        );

        when(restaurantRepository.findAll()).thenReturn(expectedRestaurants);

        List<Restaurant> result = findAllRestaurants.execute();

        assertEquals(expectedRestaurants, result);
    }
}
