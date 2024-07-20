package use_case;

import domain.RestaurantRepository;
import entity.Restaurant;

import java.util.Optional;

public class GetRestaurantByName {
    private final RestaurantRepository repository;

    public GetRestaurantByName(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Optional<Restaurant> execute(String name) {
        return repository.getByName(name);
    }
}
