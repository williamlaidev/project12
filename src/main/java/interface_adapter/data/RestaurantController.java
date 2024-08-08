package interface_adapter.data;

import domain.RestaurantRepository;
import entity.operation_result.OperationResult;
import entity.restaurant.Restaurant;
import entity.operation_result.OperationResultSuccessFactory;
import entity.operation_result.OperationResultFailureFactory;
import use_case.data.create.AddRestaurant;
import use_case.data.delete.ClearAllRestaurants;
import use_case.data.delete.DeleteRestaurantById;
import use_case.data.read.FindAllRestaurants;
import use_case.data.read.FindRestaurantById;
import use_case.data.update.UpdateRestaurant;

import java.util.List;
import java.util.Optional;

public class RestaurantController {

    private final AddRestaurant addRestaurantUseCase;
    private final UpdateRestaurant updateRestaurantUseCase;
    private final DeleteRestaurantById deleteRestaurantByIdUseCase;
    private final ClearAllRestaurants clearAllRestaurantsUseCase;
    private final FindAllRestaurants findAllRestaurantsUseCase;
    private final FindRestaurantById findRestaurantByIdUseCase;

    private final OperationResultSuccessFactory successFactory;
    private final OperationResultFailureFactory failureFactory;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.addRestaurantUseCase = new AddRestaurant(restaurantRepository);
        this.updateRestaurantUseCase = new UpdateRestaurant(restaurantRepository);
        this.deleteRestaurantByIdUseCase = new DeleteRestaurantById(restaurantRepository);
        this.clearAllRestaurantsUseCase = new ClearAllRestaurants(restaurantRepository);
        this.findAllRestaurantsUseCase = new FindAllRestaurants(restaurantRepository);
        this.findRestaurantByIdUseCase = new FindRestaurantById(restaurantRepository);
        this.successFactory = new OperationResultSuccessFactory();
        this.failureFactory = new OperationResultFailureFactory();
    }

    public OperationResult addRestaurant(Restaurant restaurant) {
        try {
            return addRestaurantUseCase.execute(restaurant);
        } catch (Exception e) {
            return failureFactory.create("Failed to add restaurant");
        }
    }

    public OperationResult updateRestaurant(Restaurant restaurant) {
        try {
            return updateRestaurantUseCase.execute(restaurant);
        } catch (Exception e) {
            return failureFactory.create("Failed to update restaurant");
        }
    }

    public OperationResult deleteRestaurantById(String id) {
        try {
            boolean result = deleteRestaurantByIdUseCase.execute(id);
            return result ? successFactory.create("Restaurant deleted successfully")
                    : failureFactory.create("Failed to delete restaurant");
        } catch (Exception e) {
            return failureFactory.create("Failed to delete restaurant");
        }
    }

    public OperationResult clearAllRestaurants() {
        try {
            boolean result = clearAllRestaurantsUseCase.execute();
            return result ? successFactory.create("All restaurants cleared successfully")
                    : failureFactory.create("Failed to clear all restaurants");
        } catch (Exception e) {
            return failureFactory.create("Failed to clear all restaurants");
        }
    }

    public Optional<Restaurant> getRestaurantById(String id) {
        try {
            return findRestaurantByIdUseCase.execute(id);
        } catch (Exception e) {
            return Optional.empty(); // Handle appropriately or log the error
        }
    }

    public List<Restaurant> getAllRestaurants() {
        try {
            return findAllRestaurantsUseCase.execute();
        } catch (Exception e) {
            return List.of(); // Handle appropriately or log the error
        }
    }
}
