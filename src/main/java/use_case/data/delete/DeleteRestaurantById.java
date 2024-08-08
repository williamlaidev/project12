package use_case.data.delete;

import domain.RestaurantRepository;

/**
 * Use case for deleting a restaurant by ID.
 * It uses the {@link RestaurantRepository} to perform the deletion.
 */
public class DeleteRestaurantById {

    private final RestaurantRepository repository;

    /**
     * Creates an instance with the given repository.
     *
     * @param repository the {@link RestaurantRepository} for deleting restaurants.
     */
    public DeleteRestaurantById(RestaurantRepository repository) {
        this.repository = repository;
    }

    /**
     * Deletes the restaurant with the specified ID.
     *
     * @param id the ID of the restaurant to be deleted.
     * @return {@code true} if the deletion was successful, {@code false} otherwise.
     */
    public boolean execute(String id) {
        return repository.deleteById(id);
    }
}
