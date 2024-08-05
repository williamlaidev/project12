package interface_adapter.search;

import entity.restaurant.Restaurant;
import org.json.JSONObject;

public interface RestaurantSearchAdapter {
    Restaurant adaptToRestaurant(JSONObject placeJson);
}