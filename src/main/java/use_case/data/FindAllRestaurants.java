package use_case.data;

import entity.Restaurant;
import domain.RestaurantRepository;

import java.util.List;
import java.util.Optional;

/**
 * Handles the use case for retrieving all restaurants.
 * It uses the {@link RestaurantRepository} to fetch the list of all restaurants.
 */
public class FindAllRestaurants {
    private final RestaurantRepository repository;

    /**
     * Constructs an instance with the specified repository.
     *
     * @param repository the {@link RestaurantRepository} used for finding all restaurants.
     */
    public FindAllRestaurants(RestaurantRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all restaurants from the repository.
     *
     * @return an {@link Optional} containing a list of all {@link Restaurant}s, or an empty {@link Optional} if no restaurants are found.
     */
    public Optional<List<Restaurant>> execute() {
        return repository.findAll();
    }
}
