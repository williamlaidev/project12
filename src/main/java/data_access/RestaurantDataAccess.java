package data_access;

import entity.Restaurant;
import java.util.List;

/**
 * This interface defines the methods for accessing restaurant data.
 */
public interface RestaurantDataAccess {

    /**
     * Loads a list of restaurants from the data source.
     *
     * @return a list of {@link Restaurant} objects loaded from the data source.
     *         If an error occurs or no data is available, an empty list is returned.
     */
    List<Restaurant> loadRestaurants();

    /**
     * Saves a list of restaurants to the data source.
     *
     * @param restaurants the list of {@link Restaurant} objects to be saved.
     *                    If the list is null or empty, no changes will be made.
     */
    void saveRestaurants(List<Restaurant> restaurants);
}
