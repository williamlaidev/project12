package domain;

import org.json.JSONObject;

public interface RestaurantPhotoService {
    String fetchPhotoUrl(JSONObject placeJson);
}