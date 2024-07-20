package use_case;

import domain.RestaurantRepository;
import entity.Location;
import entity.Restaurant;

import java.util.Optional;

public class GetRestaurantByLocation {
    private final RestaurantRepository repository;

    public GetRestaurantByLocation(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Optional<Restaurant> execute(Location location) {
        return repository.getByLocation(location);
    }
}
