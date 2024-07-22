package framework;

import org.json.JSONObject;

/**
 * Interface for searching restaurants and generating photo URLs.
 */
public interface RestaurantSearchService {

    /**
     * Fetches nearby restaurants from the API based on location and radius.
     *
     * @param latitude  the latitude of the location
     * @param longitude the longitude of the location
     * @param radius    the radius in meters within which to find restaurants
     * @return JSONObject containing the list of restaurants found
     * @throws Exception if there is an issue with the network or the API call fails
     */
    JSONObject fetchNearbyRestaurants(double latitude, double longitude, int radius) throws Exception;

    /**
     * Builds the URL for a photo based on its reference.
     *
     * @param photoReference the photo reference from the Places API
     * @return the complete URL to access the photo
     */
    String buildPhotoUrl(String photoReference);
}
