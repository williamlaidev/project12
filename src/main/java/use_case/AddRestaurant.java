package use_case;

import domain.RestaurantRepository;
import entity.Restaurant;

/**
 * Use case for adding a new restaurant to the repository.
 * This class encapsulates the logic required to add a restaurant.
 */
public class AddRestaurant {
    private final RestaurantRepository repository;

    /**
     * Constructs an {@link AddRestaurant} instance with the specified repository.
     *
     * @param repository the {@link RestaurantRepository} used to add the restaurant.
     */
    public AddRestaurant(RestaurantRepository repository) {
        this.repository = repository;
    }

    /**
     * Executes the addition of a restaurant to the repository.
     *
     * @param restaurant the {@link Restaurant} to be added.
     * @return {@code true} if the restaurant was successfully added, {@code false} if a restaurant with the same ID already exists.
     */
    public boolean execute(Restaurant restaurant) {
        return repository.add(restaurant);
    }
}
