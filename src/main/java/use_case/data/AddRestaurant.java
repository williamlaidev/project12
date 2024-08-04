package use_case.data;

import domain.RestaurantRepository;
import entity.OperationResult;
import entity.Restaurant;

public class AddRestaurant {

    private final RestaurantRepository restaurantRepository;

    public AddRestaurant(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public OperationResult execute(Restaurant restaurant) {
        return restaurantRepository.add(restaurant);
    }
}
