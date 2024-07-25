package use_case.Data;

import domain.RestaurantRepository;

/**
 * Use case for deleting a restaurant by its name.
 * This class encapsulates the logic required to delete a restaurant by name.
 */
public class DeleteRestaurantByName {
    private final RestaurantRepository repository;

    /**
     * Constructs a {@link DeleteRestaurantByName} instance with the specified repository.
     *
     * @param repository the {@link RestaurantRepository} used to delete the restaurant.
     */
    public DeleteRestaurantByName(RestaurantRepository repository) {
        this.repository = repository;
    }

    /**
     * Executes the deletion of a restaurant from the repository using its name.
     *
     * @param name the name of the restaurant to be deleted.
     * @return {@code true} if the restaurant was successfully deleted, {@code false} if no restaurant with the given name was found.
     */
    public boolean execute(String name) {
        return repository.deleteByName(name);
    }
}
