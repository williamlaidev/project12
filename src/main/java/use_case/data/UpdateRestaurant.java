package use_case.data;

import domain.RestaurantRepository;
import entity.Restaurant;

/**
 * Use case for updating an existing restaurant in the repository.
 * This class encapsulates the logic required to update a restaurant.
 */
public class UpdateRestaurant {
    private final RestaurantRepository repository;

    /**
     * Constructs an {@link UpdateRestaurant} instance with the specified repository.
     *
     * @param repository the {@link RestaurantRepository} used to update the restaurant.
     */
    public UpdateRestaurant(RestaurantRepository repository) {
        this.repository = repository;
    }

    /**
     * Executes the update of a restaurant in the repository.
     *
     * @param restaurant the {@link Restaurant} with updated information.
     * @return {@code true} if the restaurant was successfully updated, {@code false} if the restaurant with the given ID does not exist.
     */
    public boolean execute(Restaurant restaurant) {
        return repository.update(restaurant);
    }
}
