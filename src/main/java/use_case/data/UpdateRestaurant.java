package use_case.data;

import entity.Restaurant;
import domain.RestaurantRepository;

/**
 * Handles the use case for updating an existing restaurant.
 * It uses the {@link RestaurantRepository} to update the restaurant data.
 */
public class UpdateRestaurant {
    private final RestaurantRepository repository;

    /**
     * Constructs an instance with the specified repository.
     *
     * @param repository the {@link RestaurantRepository} used for updating restaurants.
     */
    public UpdateRestaurant(RestaurantRepository repository) {
        this.repository = repository;
    }

    /**
     * Updates the given restaurant in the repository.
     *
     * @param restaurant the {@link Restaurant} to be updated.
     */
    public void execute(Restaurant restaurant) {
        repository.update(restaurant);
    }
}
