package use_case.data.read;
import domain.RestaurantRepository;
import entity.restaurant.Restaurant;
import java.util.Optional;

public class FindRestaurantById {

    private final RestaurantRepository restaurantRepository;

    public FindRestaurantById(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Optional<Restaurant> execute(String id) {
        return restaurantRepository.findById(id);
    }
}
