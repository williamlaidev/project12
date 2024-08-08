package domain;

import entity.operation_result.OperationResult;
import entity.restaurant.Restaurant;
import java.util.List;
import java.util.Optional;

public interface RestaurantRepository {

    OperationResult add(Restaurant restaurant);

    Optional<Restaurant> findById(String id);

    List<Restaurant> findAll();

    OperationResult save(Restaurant restaurant);

    OperationResult update(Restaurant restaurant);

    boolean deleteById(String id);

    boolean clearAll();
}
