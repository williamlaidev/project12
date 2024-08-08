package use_case.data.read;

import domain.RestaurantRepository;
import entity.restaurant.Restaurant;
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
