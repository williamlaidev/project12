package use_case.data.read;

import domain.RestaurantRepository;
import entity.restaurant.Restaurant;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FindAllRestaurantsTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private FindAllRestaurants findAllRestaurants;

    public FindAllRestaurantsTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        Restaurant restaurant1 = new Restaurant("1", "Restaurant 1", null, "Address 1", null, 4.5, "url1");
        Restaurant restaurant2 = new Restaurant("2", "Restaurant 2", null, "Address 2", null, 3.5, "url2");

        when(restaurantRepository.findAll()).thenReturn(Arrays.asList(restaurant1, restaurant2));

        List<Restaurant> result = findAllRestaurants.execute();
        assertEquals(2, result.size());
        assertEquals("1", result.get(0).getRestaurantId());
        assertEquals("2", result.get(1).getRestaurantId());
    }
}
