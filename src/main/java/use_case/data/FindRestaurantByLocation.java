package use_case.data;

import entity.Location;
import entity.Restaurant;
import domain.RestaurantRepository;

import java.util.Optional;

/**
 * Handles the use case for retrieving a restaurant by its location.
 * It uses the {@link RestaurantRepository} to find the restaurant.
 */
public class FindRestaurantByLocation {
    private final RestaurantRepository repository;

    /**
     * Constructs an instance with the specified repository.
     *
     * @param repository the {@link RestaurantRepository} used for finding a restaurant by location.
     */
    public FindRestaurantByLocation(RestaurantRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves a restaurant with the given location from the repository.
     *
     * @param location the location of the restaurant to be retrieved.
     * @return an {@link Optional} containing the {@link Restaurant} if found, or an empty {@link Optional} if not found.
     */
    public Optional<Restaurant> execute(Location location) {
        return repository.findByLocation(location);
    }
}
