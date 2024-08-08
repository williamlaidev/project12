package use_case.data.delete;

import domain.RestaurantRepository;

/**
 * Use case for clearing all restaurants.
 * It uses the {@link RestaurantRepository} to perform the clearance.
 */
public class ClearAllRestaurants {

    private final RestaurantRepository restaurantRepository;

    /**
     * Creates an instance with the given repository.
     *
     * @param restaurantRepository the {@link RestaurantRepository} for clearing restaurants.
     */
    public ClearAllRestaurants(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * Clears all restaurants.
     *
     * @return {@code true} if the clearance was successful, {@code false} otherwise.
     */
    public boolean execute() {
        return restaurantRepository.clearAll();
    }
}
