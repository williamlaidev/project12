package use_case.data.read;

import domain.RestaurantRepository;
import entity.restaurant.Restaurant;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class FindRestaurantByIdTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private FindRestaurantById findRestaurantById;

    public FindRestaurantByIdTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecuteFound() {
        Restaurant restaurant = new Restaurant("1", "Restaurant", null, "Address", null, 4.5, "url");
        when(restaurantRepository.findById("1")).thenReturn(Optional.of(restaurant));

        Optional<Restaurant> result = findRestaurantById.execute("1");
        assertTrue(result.isPresent());
        assertEquals("1", result.get().getRestaurantId());
    }

    @Test
    public void testExecuteNotFound() {
        when(restaurantRepository.findById("999")).thenReturn(Optional.empty());

        Optional<Restaurant> result = findRestaurantById.execute("999");
        assertTrue(result.isEmpty());
    }
}
