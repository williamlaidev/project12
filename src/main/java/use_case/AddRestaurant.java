package use_case;

import domain.RestaurantRepository;
import entity.Restaurant;

public class AddRestaurant {
    private final RestaurantRepository repository;

    public AddRestaurant(RestaurantRepository repository) {
        this.repository = repository;
    }

    public boolean execute(Restaurant restaurant) {
        return repository.add(restaurant);
    }
}
