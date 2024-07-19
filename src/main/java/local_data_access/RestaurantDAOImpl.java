package local_data_access;

import org.json.JSONArray;
import org.json.JSONObject;

public class RestaurantDAOImpl implements RestaurantDAO {

    private static final String FILE_PATH = "restaurants.json";

    public void addRestaurant(JSONObject restaurant) {
        JSONArray data = RestaurantManager.loadJson(FILE_PATH);
        data.put(restaurant);
        RestaurantManager.saveJson(FILE_PATH, data);
    }

    public void updateRestaurant(JSONObject updatedRestaurant) {
        JSONArray data = RestaurantManager.loadJson(FILE_PATH);
        JSONArray updatedData = new JSONArray();
        String updatedRestaurantName = updatedRestaurant.getString("name");

        for (int i = 0; i < data.length(); i++) {
            JSONObject restaurant = data.getJSONObject(i);
            if (restaurant.getString("name").equals(updatedRestaurantName)) {
                updatedData.put(updatedRestaurant);
            } else {
                updatedData.put(restaurant);
            }
        }
        RestaurantManager.saveJson(FILE_PATH, updatedData);
    }

    public void deleteRestaurant(JSONObject restaurant) {
        String restaurantName = restaurant.getString("name");
        JSONArray data = RestaurantManager.loadJson(FILE_PATH);
        JSONArray updatedData = new JSONArray();

        for (int i = 0; i < data.length(); i++) {
            JSONObject currentRestaurant = data.getJSONObject(i);
            if (!currentRestaurant.getString("name").equals(restaurantName)) {
                updatedData.put(currentRestaurant);
            }
        }
        RestaurantManager.saveJson(FILE_PATH, updatedData);
    }
}