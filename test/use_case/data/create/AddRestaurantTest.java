package use_case.data.create;

import domain.RestaurantRepository;
import entity.DishType;
import entity.location.Location;
import entity.operation_result.OperationResult;
import entity.restaurant.Restaurant;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AddRestaurantTest {

    @Test
    void testExecuteSuccess() {
        // Arrange
        RestaurantRepository mockRepository = mock(RestaurantRepository.class);
        OperationResult expectedResult = new OperationResult(true, "Success");
        Restaurant restaurant = new Restaurant("1", "Restaurant Name", new Location(40.7128, -74.0060), "123 Main St", DishType.ITALIAN, 4.5, "photoUrl");

        when(mockRepository.add(restaurant)).thenReturn(expectedResult);

        AddRestaurant addRestaurant = new AddRestaurant(mockRepository);

        // Act
        OperationResult result = addRestaurant.execute(restaurant);

        // Assert
        assertEquals(expectedResult, result);
        verify(mockRepository, times(1)).add(restaurant);
    }

    @Test
    void testExecuteFailure() {
        // Arrange
        RestaurantRepository mockRepository = mock(RestaurantRepository.class);
        OperationResult expectedResult = new OperationResult(false, "Failure");
        Restaurant restaurant = new Restaurant("1", "Restaurant Name", new Location(40.7128, -74.0060), "123 Main St", DishType.ITALIAN, 4.5, "photoUrl");

        when(mockRepository.add(restaurant)).thenReturn(expectedResult);

        AddRestaurant addRestaurant = new AddRestaurant(mockRepository);

        // Act
        OperationResult result = addRestaurant.execute(restaurant);

        // Assert
        assertEquals(expectedResult, result);
        verify(mockRepository, times(1)).add(restaurant);
    }
}
