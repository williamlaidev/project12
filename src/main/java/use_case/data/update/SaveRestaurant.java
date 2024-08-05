package use_case.data.update;

import domain.RestaurantRepository;
import entity.operation_result.OperationResult;
import entity.restaurant.Restaurant;

public class SaveRestaurant {

    private final RestaurantRepository restaurantRepository;

    public SaveRestaurant(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public OperationResult execute(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
}
