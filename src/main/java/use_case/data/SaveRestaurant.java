package use_case.data;

import entity.Restaurant;
import domain.RestaurantRepository;

/**
 * Handles the use case for saving a new restaurant.
 * It uses the {@link RestaurantRepository} to persist the restaurant data.
 */
public class SaveRestaurant {
    private final RestaurantRepository repository;

    /**
     * Constructs an instance with the specified repository.
     *
     * @param repository the {@link RestaurantRepository} used for saving restaurants.
     */
    public SaveRestaurant(RestaurantRepository repository) {
        this.repository = repository;
    }

    /**
     * Saves the given restaurant to the repository.
     *
     * @param restaurant the {@link Restaurant} to be saved.
     */
    public void execute(Restaurant restaurant) {
        repository.save(restaurant);
    }
}
