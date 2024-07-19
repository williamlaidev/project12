package local_data_access;

import org.json.JSONObject;

public interface RestaurantDAO {
    void addRestaurant(JSONObject restaurant);
    void updateRestaurant(JSONObject restaurant);
    void deleteRestaurant(JSONObject restaurant);
}
