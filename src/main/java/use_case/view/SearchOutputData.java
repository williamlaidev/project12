package use_case.view;
import entity.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



public class SearchOutputData {
    private final List<Restaurant> restaurantsList;

    public SearchOutputData(List<Restaurant> restaurantsList) {
        this.restaurantsList = restaurantsList;
    }

    public List<String> getRestaurantsId() {
        List<String> result = new ArrayList<>();
        for (Restaurant restaurant : restaurantsList) {
            if (restaurant != null) {
                result.add(restaurant.getRestaurantId());

            }
        }
        return result;
    }
}
