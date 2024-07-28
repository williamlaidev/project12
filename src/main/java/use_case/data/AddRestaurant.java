package use_case.data;

import entity.Restaurant;
import domain.RestaurantRepository;

/**
 * Handles the use case for adding a restaurant.
 * It uses the {@link RestaurantRepository} to save the restaurant.
 */
public class AddRestaurant {

    private final RestaurantRepository repository;

    /**
     * Creates an instance with the given repository.
     *
     * @param repository the {@link RestaurantRepository} for adding restaurants.
     */
    public AddRestaurant(RestaurantRepository repository) {
        this.repository = repository;
    }

    /**
     * Adds the specified restaurant to the repository.
     *
     * @param restaurant the {@link Restaurant} to add.
     */
    public void execute(Restaurant restaurant) {
        repository.add(restaurant);
    }
}
