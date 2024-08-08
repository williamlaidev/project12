package use_case.data.create;

import domain.RestaurantRepository;
import entity.operation_result.OperationResult;
import entity.operation_result.OperationResultSuccessFactory;
import entity.location.Location;
import entity.restaurant.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddRestaurantTest {

    private AddRestaurant addRestaurant;
    private RestaurantRepository restaurantRepository;
    private OperationResultSuccessFactory successFactory;

    @BeforeEach
    public void setUp() {
        restaurantRepository = mock(RestaurantRepository.class);
        addRestaurant = new AddRestaurant(restaurantRepository);
        successFactory = new OperationResultSuccessFactory();
    }

    @Test
    public void testExecuteSuccess() {
        // Create a sample restaurant
        Location location = new Location(37.7749, -122.4194);
        Restaurant restaurant = new Restaurant("id1", "Test Restaurant", location, "123 Main St", null, 4.5, "");

        // Mock the repository to return a success operation result
        OperationResult expected = successFactory.create("Restaurant added successfully");
        when(restaurantRepository.add(restaurant)).thenReturn(expected);

        // Execute the use case
        OperationResult result = addRestaurant.execute(restaurant);

        // Verify the interactions and assert results
        verify(restaurantRepository, times(1)).add(restaurant);
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    public void testExecuteFailure() {
        // Create a sample restaurant
        Location location = new Location(37.7749, -122.4194);
        Restaurant restaurant = new Restaurant("id1", "Test Restaurant", location, "123 Main St", null, 4.5, "");

        // Mock the repository to return a failure operation result
        OperationResult expected = new OperationResult(false, "Failed to add restaurant");
        when(restaurantRepository.add(restaurant)).thenReturn(expected);

        // Execute the use case
        OperationResult result = addRestaurant.execute(restaurant);

        // Verify the interactions and assert results
        verify(restaurantRepository, times(1)).add(restaurant);
        assertEquals(expected.getMessage(), result.getMessage());
    }
}
