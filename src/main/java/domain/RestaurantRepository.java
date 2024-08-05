package domain;

import entity.Location;
import entity.Restaurant;

import java.util.List;
import java.util.Optional;

/**
 * Defines the operations for managing {@link Restaurant} entities in a repository.
 * This interface provides methods for adding, finding, saving, updating, and deleting restaurants.
 */
public interface RestaurantRepository {

    /**
     * Adds a new restaurant to the repository.
     *
     * @param restaurant the {@link Restaurant} to be added.
     * @return {@code true} if the restaurant was added successfully; {@code false} otherwise.
     */
    boolean add(Restaurant restaurant);

    /**
     * Finds a restaurant by its unique identifier.
     *
     * @param id the ID of the restaurant to find.
     * @return an {@link Optional} containing the {@link Restaurant} if found, or an empty {@link Optional} if not found.
     */
    Optional<Restaurant> findById(String id);

    /**
     * Finds a restaurant by its name.
     *
     * @param name the name of the restaurant to find.
     * @return an {@link Optional} containing the {@link Restaurant} if found, or an empty {@link Optional} if not found.
     */
    Optional<Restaurant> findByName(String name);

    /**
     * Finds a restaurant by its location.
     *
     * @param location the {@link Location} of the restaurant to find.
     * @return an {@link Optional} containing the {@link Restaurant} if found, or an empty {@link Optional} if not found.
     */
    Optional<Restaurant> findByLocation(Location location);

    /**
     * Retrieves all restaurants from the repository.
     *
     * @return an {@link Optional} containing a list of all {@link Restaurant}s, or an empty {@link Optional} if no restaurants are found.
     */
    Optional<List<Restaurant>> findAll();

    /**
     * Saves a new or updated restaurant to the repository.
     * This method may be used for both inserting a new record or updating an existing one.
     *
     * @param restaurant the {@link Restaurant} to be saved.
     * @return {@code true} if the restaurant was saved successfully; {@code false} otherwise.
     */
    boolean save(Restaurant restaurant);

    /**
     * Updates an existing restaurant in the repository.
     *
     * @param restaurant the {@link Restaurant} to be updated.
     * @return {@code true} if the restaurant was updated successfully; {@code false} otherwise.
     */
    boolean update(Restaurant restaurant);

    /**
     * Deletes a restaurant from the repository by its unique identifier.
     *
     * @param id the ID of the restaurant to be deleted.
     * @return {@code true} if the restaurant was deleted successfully; {@code false} otherwise.
     */
    boolean delete(String id);
}
