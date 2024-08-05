package interface_adapter.view;

import java.util.List;

public class RestaurantState {
    private List<String> restaurantInfoList;
    public RestaurantState(RestaurantState copy) {
        restaurantInfoList = copy.restaurantInfoList;
    }

    public RestaurantState() {}

    public List<String>getRestaurantLabel() {
        return restaurantInfoList;
    }

    public void setRestaurantsInfo(List<String> restaurantId) {
        this.restaurantInfoList = restaurantId;
    }

    public List<String> getRestaurantsInfo() {
        return restaurantInfoList;
    }
}
