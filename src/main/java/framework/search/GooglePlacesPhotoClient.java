package framework.search;

import domain.RestaurantPhotoService;
import framework.config.EnvConfigService;
import org.json.JSONArray;
import org.json.JSONObject;

public class GooglePlacesPhotoClient implements RestaurantPhotoService {
    private final EnvConfigService envConfigService;

    public GooglePlacesPhotoClient(EnvConfigService envConfigService) {
        this.envConfigService = envConfigService;
    }

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
