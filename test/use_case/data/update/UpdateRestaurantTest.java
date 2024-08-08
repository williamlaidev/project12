package use_case.data.update;

import domain.RestaurantRepository;
import entity.operation_result.OperationResult;
import entity.operation_result.OperationResultSuccessFactory;
import entity.restaurant.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UpdateRestaurantTest {

    private RestaurantRepository restaurantRepository;
    private UpdateRestaurant updateRestaurant;
    private OperationResultSuccessFactory successFactory;

    @BeforeEach
    public void setUp() {
        restaurantRepository = mock(RestaurantRepository.class);
        updateRestaurant = new UpdateRestaurant(restaurantRepository);
        successFactory = new OperationResultSuccessFactory();
    }

    @Test
    public void testExecute() {
        Restaurant restaurant = new Restaurant("id1", "Test Restaurant", null, "123 Main St", null, 4.5, "");
        OperationResult expected = successFactory.create("Restaurant updated successfully");

        when(restaurantRepository.update(restaurant)).thenReturn(expected);

        OperationResult result = updateRestaurant.execute(restaurant);

        assertEquals(expected, result);
    }
}
