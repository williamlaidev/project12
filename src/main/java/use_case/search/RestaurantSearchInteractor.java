package use_case.search;

import domain.RestaurantSearchService;
import entity.DishType;
import entity.restaurant.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantSearchInteractor implements RestaurantSearchService {

    private final SearchRestaurants searchStrategy;

    public RestaurantSearchInteractor(SearchRestaurants searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    @Override
    public List<Restaurant> fetchNearbyRestaurants(SearchRestaurantInput searchInput, int maxRestaurantsToSearch, int maxResults) {
        double latitude = searchInput.getLatitude();
        double longitude = searchInput.getLongitude();
        String distance = searchInput.getDistance();
        DishType dishType = searchInput.getDishType();

        try {
            double distanceValue = Double.parseDouble(distance);
            String roundedDistance = Integer.toString((int) Math.round(distanceValue));

            SearchRestaurantInput refinedInput = new SearchRestaurantInput(latitude, longitude, roundedDistance, dishType);
            List<Restaurant> results = searchStrategy.execute(refinedInput, maxRestaurantsToSearch, maxResults);

            return results != null ? results : new ArrayList<>();
        } catch (NumberFormatException e) {
            System.out.println("Invalid distance input. Please enter a valid number.");
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("An error occurred during the search: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
