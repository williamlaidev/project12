package use_case.data;

import domain.RestaurantRepository;
import entity.Restaurant;
import java.util.Optional;

/**
 * Use case for retrieving a restaurant by its unique identifier.
 * This class encapsulates the logic required to get a restaurant by ID.
 */
public class GetRestaurantById {
    private final RestaurantRepository repository;

    /**
     * Constructs a {@link GetRestaurantById} instance with the specified repository.
     *
     * @param repository the {@link RestaurantRepository} used to retrieve the restaurant.
     */
    public GetRestaurantById(RestaurantRepository repository) {
        this.repository = repository;
    }

    /**
     * Executes the retrieval of a restaurant from the repository using its ID.
     *
     * @param id the unique identifier of the restaurant to be retrieved.
     * @return an {@link Optional} containing the {@link Restaurant} if found, otherwise an empty {@link Optional}.
     */
    public Optional<Restaurant> execute(String id) {
        return repository.getById(id);
    }
}
