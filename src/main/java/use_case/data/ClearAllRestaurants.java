package use_case.data;

import domain.RestaurantRepository;

public class ClearAllRestaurants {

    private final RestaurantRepository restaurantRepository;

    public ClearAllRestaurants(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public boolean execute() {
        return restaurantRepository.clearAll();
    }
}
