package interface_adapter.search;

import entity.restaurant.Restaurant;
import org.json.JSONObject;

/**
 * Interface for adapting JSON data into Restaurant entities.
 */
public interface RestaurantSearchAdapter {
    /**
     * Converts a JSONObject representing a place into a Restaurant entity.
     *
     * @param placeJson JSON object representing the place
     * @return A Restaurant entity created from the JSON object
     */
    Restaurant adaptToRestaurant(JSONObject placeJson);
}
