package use_case.search;

import domain.RestaurantPhotoService;
import org.json.JSONObject;

/**
 * Use case for fetching the URL of a restaurant's photo.
 * It uses the {@link RestaurantPhotoService} to obtain the photo URL.
 */
public class FetchRestaurantPhotoUrl {

    private final RestaurantPhotoService restaurantPhotoService;

    /**
     * Constructs an instance with the given service.
     *
     * @param restaurantPhotoService the {@link RestaurantPhotoService} for fetching photo URLs.
     */
    public FetchRestaurantPhotoUrl(RestaurantPhotoService restaurantPhotoService) {
        this.restaurantPhotoService = restaurantPhotoService;
    }

    /**
     * Retrieves the photo URL for the given restaurant JSON object.
     *
     * @param placeJson the JSON object representing the restaurant.
     * @return the URL of the restaurant's photo.
     */
    public String execute(JSONObject placeJson) {
        return restaurantPhotoService.fetchPhotoUrl(placeJson);
    }
}
