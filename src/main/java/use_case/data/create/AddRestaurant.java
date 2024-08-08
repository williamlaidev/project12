package use_case.data.create;

import domain.RestaurantRepository;
import entity.operation_result.OperationResult;
import entity.restaurant.Restaurant;

public class AddRestaurant {

    private final RestaurantRepository restaurantRepository;

    public AddRestaurant(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public OperationResult execute(Restaurant restaurant) {
        return restaurantRepository.add(restaurant);
    }
}
