package use_case;

import domain.RestaurantRepository;
import entity.Restaurant;

public class UpdateRestaurant {
    private final RestaurantRepository repository;

    public UpdateRestaurant(RestaurantRepository repository) {
        this.repository = repository;
    }

    public boolean execute(Restaurant restaurant) {
        return repository.update(restaurant);
    }
}
