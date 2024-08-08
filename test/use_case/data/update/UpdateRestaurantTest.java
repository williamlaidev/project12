package use_case.data.update;

import domain.RestaurantRepository;
import entity.operation_result.OperationResult;
import entity.restaurant.Restaurant;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UpdateRestaurantTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private UpdateRestaurant updateRestaurant;

    public UpdateRestaurantTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        Restaurant restaurant = new Restaurant("1", "Updated Restaurant", null, "Updated Address", null, 5.0, "updated_url");
        OperationResult result = new OperationResult(true, "Update successful");
        when(restaurantRepository.update(restaurant)).thenReturn(result);

        OperationResult resultFromUseCase = updateRestaurant.execute(restaurant);
        assertEquals(result.isSuccess(), resultFromUseCase.isSuccess());
        assertEquals(result.getMessage(), resultFromUseCase.getMessage());
    }
}
