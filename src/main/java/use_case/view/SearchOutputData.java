package use_case.view;

import entity.restaurant.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds and processes search output data.
 */
public class SearchOutputData {
    private final List<Restaurant> restaurantsList;

    public SearchOutputData(List<Restaurant> restaurantsList) {
        this.restaurantsList = restaurantsList;
    }

    /**
     * Gets formatted information about the restaurants.
     *
     * @return list of restaurant info strings
     */
    public List<String> getRestaurantsInfo() {
        List<String> result = new ArrayList<>();
        for (Restaurant restaurant : restaurantsList) {
            if (restaurant != null) {
                String info = String.format("%s - %s - %s - %s - %s - Rating: %.2f - %s",
                        restaurant.getRestaurantId(),
                        restaurant.getName(),
                        restaurant.getAddress(),
                        restaurant.getLocation(),
                        restaurant.getDishType().name(),
                        restaurant.getAverageRating(),
                        restaurant.getPhotoUrl());
                result.add(info);
            }
        }
        return result;
    }
}
