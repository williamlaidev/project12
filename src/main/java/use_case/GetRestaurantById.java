package use_case;

import domain.RestaurantRepository;
import entity.Restaurant;

import java.util.Optional;

public class GetRestaurantById {
    private final RestaurantRepository repository;

    public GetRestaurantById(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Optional<Restaurant> execute(String id) {
        return repository.getById(id);
    }
}
