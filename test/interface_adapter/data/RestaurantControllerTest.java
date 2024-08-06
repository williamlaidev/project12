package interface_adapter.data;

import domain.RestaurantRepository;
import entity.operation_result.OperationResult;
import entity.operation_result.OperationResultFailureFactory;
import entity.operation_result.OperationResultSuccessFactory;
import entity.restaurant.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.data.create.AddRestaurant;
import use_case.data.delete.ClearAllRestaurants;
import use_case.data.delete.DeleteRestaurantById;
import use_case.data.read.FindAllRestaurants;
import use_case.data.read.FindRestaurantById;
import use_case.data.update.UpdateRestaurant;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RestaurantControllerTest {

    private RestaurantController controller;
    private RestaurantRepository repository;
    private AddRestaurant addRestaurantUseCase;
    private UpdateRestaurant updateRestaurantUseCase;
    private DeleteRestaurantById deleteRestaurantByIdUseCase;
    private ClearAllRestaurants clearAllRestaurantsUseCase;
    private FindAllRestaurants findAllRestaurantsUseCase;
    private FindRestaurantById findRestaurantByIdUseCase;
    private OperationResultSuccessFactory successFactory;
    private OperationResultFailureFactory failureFactory;

    @BeforeEach
    public void setUp() {
        repository = mock(RestaurantRepository.class);
        addRestaurantUseCase = mock(AddRestaurant.class);
        updateRestaurantUseCase = mock(UpdateRestaurant.class);
        deleteRestaurantByIdUseCase = mock(DeleteRestaurantById.class);
        clearAllRestaurantsUseCase = mock(ClearAllRestaurants.class);
        findAllRestaurantsUseCase = mock(FindAllRestaurants.class);
        findRestaurantByIdUseCase = mock(FindRestaurantById.class);
        successFactory = new OperationResultSuccessFactory();
        failureFactory = new OperationResultFailureFactory();

        controller = new RestaurantController(repository) {
            @Override
            public OperationResult addRestaurant(Restaurant restaurant) {
                return addRestaurantUseCase.execute(restaurant);
            }

            @Override
            public OperationResult updateRestaurant(Restaurant restaurant) {
                return updateRestaurantUseCase.execute(restaurant);
            }

            @Override
            public OperationResult deleteRestaurantById(String id) {
                return deleteRestaurantByIdUseCase.execute(id) ? successFactory.create("Restaurant deleted successfully")
                        : failureFactory.create("Failed to delete restaurant");
            }

            @Override
            public OperationResult clearAllRestaurants() {
                return clearAllRestaurantsUseCase.execute() ? successFactory.create("All restaurants cleared successfully")
                        : failureFactory.create("Failed to clear all restaurants");
            }

            @Override
            public Optional<Restaurant> getRestaurantById(String id) {
                return findRestaurantByIdUseCase.execute(id);
            }

            @Override
            public List<Restaurant> getAllRestaurants() {
                return findAllRestaurantsUseCase.execute();
            }
        };
    }

    @Test
    public void testAddRestaurant() {
        Restaurant restaurant = new Restaurant("id1", "Test Restaurant", null, "123 Main St", null, 4.5, "");
        OperationResult expected = successFactory.create("Restaurant added successfully");
        when(addRestaurantUseCase.execute(restaurant)).thenReturn(expected);

        OperationResult result = controller.addRestaurant(restaurant);
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    public void testUpdateRestaurant() {
        Restaurant restaurant = new Restaurant("id1", "Test Restaurant", null, "123 Main St", null, 4.5, "");
        OperationResult expected = successFactory.create("Restaurant updated successfully");
        when(updateRestaurantUseCase.execute(restaurant)).thenReturn(expected);

        OperationResult result = controller.updateRestaurant(restaurant);
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    public void testDeleteRestaurantById() {
        String restaurantId = "id1";
        when(deleteRestaurantByIdUseCase.execute(restaurantId)).thenReturn(true);

        OperationResult expected = successFactory.create("Restaurant deleted successfully");
        OperationResult result = controller.deleteRestaurantById(restaurantId);
        assertEquals(expected.getMessage(), result.getMessage());

        when(deleteRestaurantByIdUseCase.execute(restaurantId)).thenReturn(false);
        expected = failureFactory.create("Failed to delete restaurant");
        result = controller.deleteRestaurantById(restaurantId);
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    public void testClearAllRestaurants() {
        when(clearAllRestaurantsUseCase.execute()).thenReturn(true);

        OperationResult expected = successFactory.create("All restaurants cleared successfully");
        OperationResult result = controller.clearAllRestaurants();
        assertEquals(expected.getMessage(), result.getMessage());

        when(clearAllRestaurantsUseCase.execute()).thenReturn(false);
        expected = failureFactory.create("Failed to clear all restaurants");
        result = controller.clearAllRestaurants();
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    public void testGetRestaurantById() {
        String restaurantId = "id1";
        Restaurant restaurant = new Restaurant(restaurantId, "Test Restaurant", null, "123 Main St", null, 4.5, "");
        when(findRestaurantByIdUseCase.execute(restaurantId)).thenReturn(Optional.of(restaurant));

        Optional<Restaurant> result = controller.getRestaurantById(restaurantId);
        assertTrue(result.isPresent());
        assertEquals(restaurantId, result.get().getRestaurantId());

        when(findRestaurantByIdUseCase.execute(restaurantId)).thenReturn(Optional.empty());
        result = controller.getRestaurantById(restaurantId);
        assertFalse(result.isPresent());
    }

    @Test
    public void testGetAllRestaurants() {
        List<Restaurant> restaurants = List.of(new Restaurant("id1", "Test Restaurant", null, "123 Main St", null, 4.5, ""));
        when(findAllRestaurantsUseCase.execute()).thenReturn(restaurants);

        List<Restaurant> result = controller.getAllRestaurants();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("id1", result.get(0).getRestaurantId());
    }
}
