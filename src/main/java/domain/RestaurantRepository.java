package domain;

import entity.Location;
import entity.Restaurant;
import java.util.List;
import java.util.Optional;

/**
 * The RestaurantRepository interface defines the contract for a repository
 * that provides CRUD (Create, Read, Update, Delete) operations for Restaurant entities.
 */
public interface RestaurantRepository {

    /**
     * Adds a new restaurant to the repository.
     *
     * @param restaurant the restaurant to be added
     * @return true if the restaurant was added successfully, false otherwise
     */
    boolean add(Restaurant restaurant);

    /**
     * Retrieves a restaurant by its unique ID.
     *
     * @param id the unique identifier of the restaurant
     * @return an Optional containing the found restaurant, or empty if not found
     */
    Optional<Restaurant> getById(String id);

    /**
     * Retrieves a restaurant by its name.
     *
     * @param name the name of the restaurant
     * @return an Optional containing the found restaurant, or empty if not found
     */
    Optional<Restaurant> getByName(String name);

    /**
     * Retrieves a restaurant by its location.
     *
     * @param location the location of the restaurant
     * @return an Optional containing the found restaurant, or empty if not found
     */
    Optional<Restaurant> getByLocation(Location location);

    /**
     * Retrieves all restaurants in the repository.
     *
     * @return a list of all restaurants
     */
    List<Restaurant> getAll();

    /**
     * Updates the details of an existing restaurant.
     *
     * @param restaurant the restaurant with updated details
     * @return true if the update was successful, false otherwise
     */
    boolean update(Restaurant restaurant);

    /**
     * Deletes a restaurant by its unique ID.
     *
     * @param id the unique identifier of the restaurant to be deleted
     * @return true if the deletion was successful, false otherwise
     */
    boolean deleteById(String id);

    /**
     * Deletes a restaurant by its name.
     *
     * @param name the name of the restaurant to be deleted
     * @return true if the deletion was successful, false otherwise
     */
    boolean deleteByName(String name);
}
