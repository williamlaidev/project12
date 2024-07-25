package framework.SearchRestaurant;

import org.json.JSONObject;

/**
 * Interface for interacting with a Geolocation API to get the current location.
 */
public interface GeolocationService {

    /**
     * Gets the current geographical location.
     *
     * @return JSONObject containing the latitude and longitude of the current location
     * @throws Exception if there is a network issue or the API call fails
     */
    JSONObject getCurrentLocation() throws Exception;
}
