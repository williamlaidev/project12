package use_case.data.read;

import domain.RestaurantRepository;
import entity.restaurant.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindRestaurantByIdTest {

    private RestaurantRepository restaurantRepository;
    private FindRestaurantById findRestaurantById;

    @BeforeEach
    public void setUp() {
        restaurantRepository = mock(RestaurantRepository.class);
        findRestaurantById = new FindRestaurantById(restaurantRepository);
    }

    @Test
    public void testExecute() {
        String restaurantId = "id1";
        Restaurant expectedRestaurant = new Restaurant(restaurantId, "Test Restaurant", null, "123 Main St", null, 4.5, "");

        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(expectedRestaurant));

        Optional<Restaurant> result = findRestaurantById.execute(restaurantId);

        assertTrue(result.isPresent());
        assertEquals(expectedRestaurant, result.get());
    }
}
