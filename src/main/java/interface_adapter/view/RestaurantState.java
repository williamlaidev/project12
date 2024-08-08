package interface_adapter.view;

import java.util.List;

/**
 * Represents the state of a restaurant, including the list of restaurant information.
 */
public class RestaurantState {

    private List<String> restaurantInfoList;

    /**
     * Constructs a new RestaurantState by copying the state from another RestaurantState instance.
     *
     * @param copy The RestaurantState instance to copy from.
     */
    public RestaurantState(RestaurantState copy) {
        this.restaurantInfoList = copy.restaurantInfoList;
    }

    /**
     * Constructs a new, empty RestaurantState.
     */
    public RestaurantState() {}

    /**
     * Returns the list of restaurant information.
     *
     * @return A list of strings representing restaurant information.
     */
    public List<String> getRestaurantLabel() {
        return restaurantInfoList;
    }

    /**
     * Sets the list of restaurant information.
     *
     * @param restaurantInfo A list of strings representing new restaurant information.
     */
    public void setRestaurantsInfo(List<String> restaurantInfo) {
        this.restaurantInfoList = restaurantInfo;
    }

    /**
     * Returns the list of restaurant information.
     *
     * @return A list of strings representing restaurant information.
     */
    public List<String> getRestaurants() {
        return restaurantInfoList;
    }
}
