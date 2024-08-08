package framework.search;

import domain.RestaurantPhotoService;
import framework.EnvConfigService;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Client for fetching restaurant photo URLs from Google Places API.
 * Implements the RestaurantPhotoService interface.
 */
public class GooglePlacesPhotoClient implements RestaurantPhotoService {
    private final EnvConfigService envConfigService;

    /**
     * Constructs a new GooglePlacesPhotoClient.
     *
     * @param envConfigService The service for accessing environment configuration.
     */
    public GooglePlacesPhotoClient(EnvConfigService envConfigService) {
        this.envConfigService = envConfigService;
    }

    /**
     * Fetches the photo URL for a given place JSON object.
     * The photo URL is constructed using the photo reference and API key from the environment configuration.
     *
     * @param placeJson The JSON object containing place details, including photos.
     * @return The URL of the first photo in the array, or null if no photos are available.
     * @throws IllegalArgumentException If the API key is missing or empty.
     */
    @Override
    public String fetchPhotoUrl(JSONObject placeJson) {
        String apiKey = envConfigService.getGoogleMapsApiKey();
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("API key is missing. Please check your environment configuration.");
        }

        if (placeJson.has("photos")) {
            JSONArray photosArray = placeJson.getJSONArray("photos");
            if (!photosArray.isEmpty()) {
                JSONObject firstPhoto = photosArray.getJSONObject(0);
                return "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=" + firstPhoto.getString("photo_reference") + "&key=" + apiKey;
            }
        }
        return null;
    }
}
