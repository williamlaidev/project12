package use_case.search;

import domain.RestaurantPhotoService;
import org.json.JSONObject;

public class FetchRestaurantPhotoUrl {
    private final RestaurantPhotoService restaurantPhotoService;

    public FetchRestaurantPhotoUrl(RestaurantPhotoService restaurantPhotoService) {
        this.restaurantPhotoService = restaurantPhotoService;
    }

    public String execute(JSONObject placeJson) {
        return restaurantPhotoService.fetchPhotoUrl(placeJson);
    }
}
