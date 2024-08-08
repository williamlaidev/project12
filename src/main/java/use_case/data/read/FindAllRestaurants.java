package use_case.data.read;

import domain.RestaurantRepository;
import entity.restaurant.Restaurant;
import java.util.List;

/**
 * Use case for retrieving all restaurants.
 * It uses the {@link RestaurantRepository} to perform the retrieval.
 */
public class FindAllRestaurants {

    private final RestaurantRepository restaurantRepository;

    /**
     * Creates an instance with the given repository.
     *
     * @param restaurantRepository the {@link RestaurantRepository} for retrieving all restaurants.
     */
    public FindAllRestaurants(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * Retrieves all restaurants.
     *
     * @return a {@link List} of {@link Restaurant}.
     */
    public List<Restaurant> execute() {
        return restaurantRepository.findAll();
    }
}
