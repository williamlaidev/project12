package interface_adapter.search;

import entity.DishType;
import entity.location.Location;
import entity.restaurant.Restaurant;
import entity.restaurant.RestaurantFactory;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Adapter for converting Google Places JSON data into Restaurant entities.
 */
public class GooglePlacesRestaurantSearchAdapter implements RestaurantSearchAdapter {

    private final RestaurantFactory restaurantFactory;

    /**
     * Constructs a GooglePlacesRestaurantSearchAdapter with the given RestaurantFactory.
     *
     * @param restaurantFactory Factory to create Restaurant entities
     */
    public GooglePlacesRestaurantSearchAdapter(RestaurantFactory restaurantFactory) {
        this.restaurantFactory = restaurantFactory;
    }

    /**
     * Converts a JSONObject representing a place into a Restaurant entity.
     *
     * @param placeJson JSON object representing the place
     * @return A Restaurant entity created from the JSON object
     */
    @Override
    public Restaurant adaptToRestaurant(JSONObject placeJson) {
        String placeId = placeJson.optString("place_id", "");
        String restaurantName = placeJson.optString("name", "");
        JSONArray placeTypes = placeJson.optJSONArray("types");

        double latitude = placeJson.optJSONObject("geometry").optJSONObject("location").optDouble("lat", 0.0);
        double longitude = placeJson.optJSONObject("geometry").optJSONObject("location").optDouble("lng", 0.0);
        Location location = new Location(latitude, longitude);
        String address = placeJson.optString("vicinity", "");
        double averageRating = placeJson.optDouble("rating", 0.0);
        String photoUrl = "";

        DishType matchedDishType = null;
        if (placeTypes != null) {
            for (int i = 0; i < placeTypes.length(); i++) {
                String type = placeTypes.getString(i);
                for (DishType dishType : DishType.values()) {
                    for (String apiType : dishType.getApiTypes()) {
                        if (type.equals(apiType)) {
                            matchedDishType = dishType;
                            break;
                        }
                    }
                    if (matchedDishType != null) {
                        break;
                    }
                }
                if (matchedDishType != null) {
                    break;
                }
            }
        }
        return restaurantFactory.createRestaurant(placeId, restaurantName, location, address, matchedDishType, averageRating, photoUrl);
    }
}
