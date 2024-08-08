package use_case.data.read;

import domain.RestaurantRepository;
import entity.restaurant.Restaurant;
import java.util.Optional;

/**
 * Use case for finding a restaurant by ID.
 * It uses the {@link RestaurantRepository} to perform the search.
 */
public class FindRestaurantById {

    private final RestaurantRepository restaurantRepository;

    /**
     * Creates an instance with the given repository.
     *
     * @param restaurantRepository the {@link RestaurantRepository} for finding a restaurant by ID.
     */
    public FindRestaurantById(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * Finds the restaurant with the specified ID.
     *
     * @param id the ID of the restaurant to find.
     * @return an {@link Optional} containing the {@link Restaurant} if found,
     *         or {@link Optional#empty()} if not found.
     */
    public Optional<Restaurant> execute(String id) {
        return restaurantRepository.findById(id);
    }
}
