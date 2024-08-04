package use_case.data;

import domain.RestaurantRepository;
import entity.OperationResult;
import entity.Restaurant;

public class UpdateRestaurant {

    private final RestaurantRepository restaurantRepository;

    public UpdateRestaurant(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public OperationResult execute(Restaurant restaurant) {
        return restaurantRepository.update(restaurant);
    }
}
