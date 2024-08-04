package use_case.data;

import domain.RestaurantRepository;
import entity.OperationResult;
import entity.Restaurant;

public class SaveRestaurant {

    private final RestaurantRepository restaurantRepository;

    public SaveRestaurant(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public OperationResult execute(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
}
