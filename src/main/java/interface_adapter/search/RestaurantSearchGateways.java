package interface_adapter.search;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public interface RestaurantSearchGateways {
    JSONObject fetchNearbyRestaurants(double latitude, double longitude, int radius) throws IOException, JSONException;
}