package use_case;

import domain.RestaurantRepository;
import entity.Location;
import entity.Restaurant;
import java.util.Optional;

/**
 * Use case for retrieving a restaurant by its location.
 * This class encapsulates the logic required to get a restaurant by location.
 */
public class GetRestaurantByLocation {
    private final RestaurantRepository repository;

    /**
     * Constructs a {@link GetRestaurantByLocation} instance with the specified repository.
     *
     * @param repository the {@link RestaurantRepository} used to retrieve the restaurant.
     */
    public GetRestaurantByLocation(RestaurantRepository repository) {
        this.repository = repository;
    }

    /**
     * Executes the retrieval of a restaurant from the repository using its location.
     *
     * @param location the location of the restaurant to be retrieved.
     * @return an {@link Optional} containing the {@link Restaurant} if found, otherwise an empty {@link Optional}.
     */
    public Optional<Restaurant> execute(Location location) {
        return repository.getByLocation(location);
    }
}
