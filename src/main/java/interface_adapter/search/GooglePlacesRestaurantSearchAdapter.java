package interface_adapter.search;

import entity.DishType;
import entity.location.Location;
import entity.restaurant.RestaurantFactory;
import org.json.JSONArray;
import entity.restaurant.Restaurant;
import org.json.JSONObject;

public class GooglePlacesRestaurantSearchAdapter implements RestaurantSearchAdapter {

    private final RestaurantFactory restaurantFactory;

    public GooglePlacesRestaurantSearchAdapter(RestaurantFactory restaurantFactory) {
        this.restaurantFactory = restaurantFactory;
    }

    @Override
    public Restaurant adaptToRestaurant(JSONObject placeJson) {
        String placeId = placeJson.optString("place_id", "");
        String restaurantName = placeJson.optString("name", "");
        JSONArray placeTypes = placeJson.optJSONArray("types");

        double latitude = placeJson.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
        double longitude = placeJson.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
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
