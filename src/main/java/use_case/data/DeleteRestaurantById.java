package use_case.data;

import domain.RestaurantRepository;

/**
 * Use case for deleting a restaurant by its unique identifier.
 * This class encapsulates the logic required to delete a restaurant by ID.
 */
public class DeleteRestaurantById {
    private final RestaurantRepository repository;

    /**
     * Constructs a {@link DeleteRestaurantById} instance with the specified repository.
     *
     * @param repository the {@link RestaurantRepository} used to delete the restaurant.
     */
    public DeleteRestaurantById(RestaurantRepository repository) {
        this.repository = repository;
    }

    /**
     * Executes the deletion of a restaurant from the repository using its ID.
     *
     * @param id the unique identifier of the restaurant to be deleted.
     * @return {@code true} if the restaurant was successfully deleted, {@code false} if no restaurant with the given ID was found.
     */
    public boolean execute(String id) {
        return repository.deleteById(id);
    }
}
