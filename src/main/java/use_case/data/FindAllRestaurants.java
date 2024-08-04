package use_case.data;

import domain.RestaurantRepository;
import entity.Restaurant;
import java.util.List;

public class FindAllRestaurants {

    private final RestaurantRepository restaurantRepository;

    public FindAllRestaurants(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> execute() {
        return restaurantRepository.findAll();
    }
}
