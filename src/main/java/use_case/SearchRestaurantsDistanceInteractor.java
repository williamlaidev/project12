package use_case;

import entity.Restaurant;
import entity.SearchInput;
import interface_adapter.SearchRestaurantGateways;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchRestaurantsDistanceInteractor implements SearchRestaurantsDistance {

    private final SearchRestaurantGateways restaurantGateways;

    /**
     * Constructs a SearchRestaurantsDistanceInteractor with the specified SearchRestaurantGateways.
     *
     * @param restaurantGateways The gateway used to interact with the restaurant search service.
     */
    public SearchRestaurantsDistanceInteractor(SearchRestaurantGateways restaurantGateways) {
        this.restaurantGateways = restaurantGateways;
    }

    @Override
    public Optional<List<Restaurant>> execute(SearchInput searchInput, int maxResults) {
        try {
            // Provide details about the search initiation
            System.out.println("Initiating search for restaurants within a radius of "
                    + searchInput.getDistance() + " meters from location ("
                    + searchInput.getLatitude() + ", " + searchInput.getLongitude() + ").");

            // Perform the search and handle the result
            List<Restaurant> restaurants = restaurantGateways.search(
                    searchInput, maxResults
            ).orElse(new ArrayList<>());

            // Provide details about the search completion
            System.out.println("Search completed. Found " + restaurants.size() + " restaurant(s) within a radius of "
                    + searchInput.getDistance() + " meters from location ("
                    + searchInput.getLatitude() + ", " + searchInput.getLongitude() + ").");

            return Optional.of(restaurants);
        } catch (Exception e) {
            // Provide details about the error
            System.err.println("Error during restaurant search: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
