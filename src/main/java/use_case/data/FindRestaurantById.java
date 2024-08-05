package use_case.data;

import entity.Restaurant;
import domain.RestaurantRepository;

import java.util.Optional;

/**
 * Handles the use case for retrieving a restaurant by its ID.
 * It uses the {@link RestaurantRepository} to find the restaurant.
 */
public class FindRestaurantById {
    private final RestaurantRepository repository;

    /**
     * Constructs an instance with the specified repository.
     *
     * @param repository the {@link RestaurantRepository} used for finding a restaurant by ID.
     */
    public FindRestaurantById(RestaurantRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves a restaurant with the given ID from the repository.
     *
     * @param id the ID of the restaurant to be retrieved.
     * @return an {@link Optional} containing the {@link Restaurant} if found, or an empty {@link Optional} if not found.
     */
    public Optional<Restaurant> execute(String id) {
        return repository.findById(id);
    }
}
