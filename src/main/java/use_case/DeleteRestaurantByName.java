package use_case;

import domain.RestaurantRepository;
import entity.Restaurant;

public class DeleteRestaurantByName {
    private final RestaurantRepository repository;

    public DeleteRestaurantByName(RestaurantRepository repository) {
        this.repository = repository;
    }

    public boolean execute(Restaurant restaurant) {
        return repository.deleteByName(restaurant);
    }
}
