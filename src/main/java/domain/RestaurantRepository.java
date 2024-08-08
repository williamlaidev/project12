package domain;

import entity.operation_result.OperationResult;
import entity.restaurant.Restaurant;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing {@code Restaurant} entities.
 */
public interface RestaurantRepository {

    /**
     * Adds a new restaurant to the repository.
     *
     * @param restaurant the restaurant to be added
     * @return the result of the operation
     */
    OperationResult add(Restaurant restaurant);

    /**
     * Finds a restaurant by its unique identifier.
     *
     * @param id the unique identifier of the restaurant
     * @return an {@code Optional} containing the restaurant if found, or empty if not
     */
    Optional<Restaurant> findById(String id);

    /**
     * Retrieves all restaurants from the repository.
     *
     * @return a list of all restaurants
     */
    List<Restaurant> findAll();

    /**
     * Saves or updates a restaurant in the repository.
     *
     * @param restaurant the restaurant to be saved or updated
     * @return the result of the operation
     */
    OperationResult save(Restaurant restaurant);

    /**
     * Updates an existing restaurant in the repository.
     *
     * @param restaurant the restaurant to be updated
     * @return the result of the operation
     */
    OperationResult update(Restaurant restaurant);

    /**
     * Deletes a restaurant by its unique identifier.
     *
     * @param id the unique identifier of the restaurant to be deleted
     * @return {@code true} if the restaurant was deleted successfully, {@code false} otherwise
     */
    boolean deleteById(String id);

    /**
     * Clears all restaurants from the repository.
     *
     * @return {@code true} if all restaurants were cleared successfully, {@code false} otherwise
     */
    boolean clearAll();
}
