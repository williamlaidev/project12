package interface_adapter.search;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Interface for fetching restaurant data from external sources.
 */
public interface RestaurantSearchGateways {
    /**
     * Fetches nearby restaurants based on the provided location and radius.
     *
     * @param latitude Latitude of the search location
     * @param longitude Longitude of the search location
     * @param radius Search radius in meters
     * @return JSON object containing the search results
     * @throws IOException If an I/O error occurs
     * @throws JSONException If a JSON parsing error occurs
     */
    JSONObject fetchNearbyRestaurants(double latitude, double longitude, int radius) throws IOException, JSONException;
}
