package use_case.data;

import domain.RestaurantRepository;

/**
 * Handles the use case for deleting a restaurant by its ID.
 * It uses the {@link RestaurantRepository} to remove the restaurant.
 */
public class DeleteRestaurant {
    private final RestaurantRepository repository;

    /**
     * Constructs an instance with the specified repository.
     *
     * @param repository the {@link RestaurantRepository} used for deleting restaurants.
     */
    public DeleteRestaurant(RestaurantRepository repository) {
        this.repository = repository;
    }

    /**
     * Deletes a restaurant with the given ID from the repository.
     *
     * @param id the ID of the restaurant to be deleted.
     */
    public void execute(String id) {
        repository.delete(id);
    }
}
