package interface_adapter.search;

import entity.DishType;
import use_case.search.RestaurantSearchInput;
import use_case.search.RestaurantSearchInputPort;

/**
 * Adapter class that implements RestaurantSearchInputPort to provide search input parameters
 * for searching restaurants by distance and dish type (optional).
 * This adapter converts the input parameters into a RestaurantSearchInput object
 * that can be used by the use case layer to perform the search.
 */
public class RestaurantSearchInputAdapter implements RestaurantSearchInputPort {
    /**
     * Converts the given parameters into a RestaurantSearchInput object.
     *
     * @param latitude  the latitude of the search center, expressed in degrees
     * @param longitude the longitude of the search center, expressed in degrees
     * @param distance  the search radius around the location, expressed in meters
     * @param dishType  the type of dish to filter the search results by
     * @return a RestaurantSearchInput object containing the specified search parameters
     */
    @Override
    public RestaurantSearchInput getRestaurantSearchInput(double latitude, double longitude, String distance, DishType dishType) {
        // NOTE: This method can be replaced with additional ways of capturing user search input.
        return new RestaurantSearchInput(latitude, longitude, distance, dishType);
    }
}
