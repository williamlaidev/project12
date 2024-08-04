package use_case.data;
import domain.RestaurantRepository;
import entity.Restaurant;
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
