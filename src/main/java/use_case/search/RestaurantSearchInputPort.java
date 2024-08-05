package use_case.search;

import entity.DishType;

/**
 * Input port for obtaining search input parameters to find restaurants.
 * This interface defines a method for converting input parameters into a
 * RestaurantSearchInput object that can be used by the use case layer to perform the search.
 */
public interface RestaurantSearchInputPort {
    /**
     * Converts the given parameters into a RestaurantSearchInput object.
     *
     * @param latitude  the latitude of the search center, expressed in degrees
     * @param longitude the longitude of the search center, expressed in degrees
     * @param distance  the search radius around the location, expressed in meters
     * @param dishType  the type of dish to filter the search results by
     * @return a RestaurantSearchInput object containing the specified search parameters
     */
    RestaurantSearchInput getRestaurantSearchInput(double latitude, double longitude, String distance, DishType dishType);
}
