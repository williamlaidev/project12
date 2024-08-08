package use_case.data.create;

import domain.RestaurantRepository;
import entity.operation_result.OperationResult;
import entity.restaurant.Restaurant;

/**
 * Use case for adding a restaurant.
 * It uses the {@link RestaurantRepository} to perform the addition.
 */
public class AddRestaurant {

    private final RestaurantRepository restaurantRepository;

    /**
     * Creates an instance with the given repository.
     *
     * @param restaurantRepository the {@link RestaurantRepository} for adding restaurants.
     */
    public AddRestaurant(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * Adds the specified restaurant.
     *
     * @param restaurant the restaurant to be added.
     * @return an {@link OperationResult} indicating the outcome of the operation.
     */
    public OperationResult execute(Restaurant restaurant) {
        return restaurantRepository.add(restaurant);
    }
}
