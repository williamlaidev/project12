package use_case.search;

import entity.Restaurant;
import domain.SearchRestaurantService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchRestaurantsByDistanceInteractor implements SearchRestaurantsByDistance {

    private final SearchRestaurantService searchRestaurantService;

    public SearchRestaurantsByDistanceInteractor(SearchRestaurantService searchRestaurantService) {
        this.searchRestaurantService = searchRestaurantService;
    }

    @Override
    public Optional<List<Restaurant>> execute(RestaurantSearchInput restaurantSearchInput, int maxResults) {
        try {


            // Perform the search
            Optional<List<Restaurant>> result = searchRestaurantService.execute(restaurantSearchInput, maxResults);
            List<Restaurant> restaurants = result.orElse(new ArrayList<>());

            System.out.println("Search completed. Found " + restaurants.size() + " restaurant(s) within a radius of "
                    + restaurantSearchInput.getDistance() + " meters from location ("
                    + restaurantSearchInput.getLatitude() + ", " + restaurantSearchInput.getLongitude() + ").");

            return Optional.of(restaurants);
        } catch (Exception e) {
            System.err.println("An error occurred during the restaurant search: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
