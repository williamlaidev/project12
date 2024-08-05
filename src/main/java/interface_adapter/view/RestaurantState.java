package interface_adapter.view;

import java.util.List;

public class RestaurantState {
    private List<String> restaurantIdList;
    public RestaurantState(RestaurantState copy) {
        restaurantIdList = copy.restaurantIdList;
    }

    public RestaurantState() {}

    public List<String>getRestaurantId() {
        return restaurantIdList;
    }

    public void setRestaurantId(List<String> restaurantId) {
        this.restaurantIdList = restaurantId;
    }
}
