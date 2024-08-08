package use_case.data.update;

import domain.RestaurantRepository;
import entity.operation_result.OperationResult;
import entity.restaurant.Restaurant;

/**
 * Use case for updating an existing restaurant.
 * It uses the {@link RestaurantRepository} to update the restaurant.
 */
public class UpdateRestaurant {

    private final RestaurantRepository restaurantRepository;

    /**
     * Creates an instance with the given repository.
     *
     * @param restaurantRepository the {@link RestaurantRepository} for updating restaurants.
     */
    public UpdateRestaurant(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * Updates the specified restaurant.
     *
     * @param restaurant the {@link Restaurant} to be updated.
     * @return an {@link OperationResult} indicating the outcome of the operation.
     */
    public OperationResult execute(Restaurant restaurant) {
        return restaurantRepository.update(restaurant);
    }
}
