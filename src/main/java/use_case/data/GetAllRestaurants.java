package use_case.data;

import domain.RestaurantRepository;
import entity.Restaurant;
import java.util.List;

/**
 * Use case for retrieving all restaurants from the repository.
 * This class encapsulates the logic required to get all restaurants.
 */
public class GetAllRestaurants {
    private final RestaurantRepository repository;

    /**
     * Constructs a {@link GetAllRestaurants} instance with the specified repository.
     *
     * @param repository the {@link RestaurantRepository} used to retrieve all restaurants.
     */
    public GetAllRestaurants(RestaurantRepository repository) {
        this.repository = repository;
    }

    /**
     * Executes the retrieval of all restaurants from the repository.
     *
     * @return a {@link List} of all {@link Restaurant} objects in the repository.
     */
    public List<Restaurant> execute() {
        return repository.getAll();
    }
}
