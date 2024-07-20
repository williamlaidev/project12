package use_case;

import domain.RestaurantRepository;
import entity.Restaurant;

import java.util.List;

public class GetAllRestaurants {
    private final RestaurantRepository repository;

    public GetAllRestaurants(RestaurantRepository repository) {
        this.repository = repository;
    }

    public List<Restaurant> execute() {
        return repository.getAll();
    }
}
