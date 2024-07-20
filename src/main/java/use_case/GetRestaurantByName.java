package use_case;

import domain.RestaurantRepository;
import entity.Restaurant;
import java.util.Optional;

/**
 * Use case for retrieving a restaurant by its name.
 * This class encapsulates the logic required to get a restaurant by name.
 */
public class GetRestaurantByName {
    private final RestaurantRepository repository;

    /**
     * Constructs a {@link GetRestaurantByName} instance with the specified repository.
     *
     * @param repository the {@link RestaurantRepository} used to retrieve the restaurant.
     */
    public GetRestaurantByName(RestaurantRepository repository) {
        this.repository = repository;
    }

    /**
     * Executes the retrieval of a restaurant from the repository using its name.
     *
     * @param name the name of the restaurant to be retrieved.
     * @return an {@link Optional} containing the {@link Restaurant} if found, otherwise an empty {@link Optional}.
     */
    public Optional<Restaurant> execute(String name) {
        return repository.getByName(name);
    }
}
