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

/**
 * Manages restaurant-related operations and communicates with use cases.
 */
public class RestaurantController {

    private final AddRestaurant addRestaurantUseCase;
    private final UpdateRestaurant updateRestaurantUseCase;
    private final DeleteRestaurantById deleteRestaurantByIdUseCase;
    private final ClearAllRestaurants clearAllRestaurantsUseCase;
    private final FindAllRestaurants findAllRestaurantsUseCase;
    private final FindRestaurantById findRestaurantByIdUseCase;

    private final OperationResultSuccessFactory successFactory;
    private final OperationResultFailureFactory failureFactory;

    /**
     * Constructs a RestaurantController with the specified RestaurantRepository.
     * @param restaurantRepository Repository to manage restaurant data.
     */
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

    /**
     * Adds a restaurant.
     * @param restaurant The restaurant to add.
     * @return OperationResult indicating success or failure.
     */
    public OperationResult addRestaurant(Restaurant restaurant) {
        try {
            return addRestaurantUseCase.execute(restaurant);
        } catch (Exception e) {
            return failureFactory.create("Failed to add restaurant");
        }
    }

    /**
     * Updates a restaurant.
     * @param restaurant The restaurant with updated details.
     * @return OperationResult indicating success or failure.
     */
    public OperationResult updateRestaurant(Restaurant restaurant) {
        try {
            return updateRestaurantUseCase.execute(restaurant);
        } catch (Exception e) {
            return failureFactory.create("Failed to update restaurant");
        }
    }

    /**
     * Deletes a restaurant by its ID.
     * @param id The ID of the restaurant to delete.
     * @return OperationResult indicating success or failure.
     */
    public OperationResult deleteRestaurantById(String id) {
        try {
            boolean result = deleteRestaurantByIdUseCase.execute(id);
            return result ? successFactory.create("Restaurant deleted successfully")
                    : failureFactory.create("Failed to delete restaurant");
        } catch (Exception e) {
            return failureFactory.create("Failed to delete restaurant");
        }
    }

    /**
     * Clears all restaurants.
     * @return OperationResult indicating success or failure.
     */
    public OperationResult clearAllRestaurants() {
        try {
            boolean result = clearAllRestaurantsUseCase.execute();
            return result ? successFactory.create("All restaurants cleared successfully")
                    : failureFactory.create("Failed to clear all restaurants");
        } catch (Exception e) {
            return failureFactory.create("Failed to clear all restaurants");
        }
    }

    /**
     * Retrieves a restaurant by its ID.
     * @param id The ID of the restaurant to retrieve.
     * @return An Optional containing the restaurant, if found.
     */
    public Optional<Restaurant> getRestaurantById(String id) {
        try {
            return findRestaurantByIdUseCase.execute(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * Retrieves all restaurants.
     * @return A list of all restaurants.
     */
    public List<Restaurant> getAllRestaurants() {
        try {
            return findAllRestaurantsUseCase.execute();
        } catch (Exception e) {
            return List.of();
        }
    }
}
