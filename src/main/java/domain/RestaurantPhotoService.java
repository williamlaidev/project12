package domain;

import org.json.JSONObject;

/**
 * Service for retrieving photo URLs of restaurants.
 */
public interface RestaurantPhotoService {

    /**
     * Fetches the URL of a photo for a given place represented by a JSON object.
     *
     * @param placeJson JSON object containing place information
     * @return the URL of the photo associated with the place
     */
    String fetchPhotoUrl(JSONObject placeJson);
}
