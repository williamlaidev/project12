package use_case;

import entity.DishType;
import entity.Restaurant;
import entity.SearchInput;
import interface_adapter.SearchRestaurantGateways;

import java.util.List;
import java.util.Optional;

public class Runner {
    public static void main(String[] args) {
        // Initialize SearchRestaurantGateways with real implementations of restaurant search services
        SearchRestaurantGateways gateways = new SearchRestaurantGateways();

        // Create an instance of SearchRestaurantsDistanceInteractor, injecting the real gateways for querying
        SearchRestaurantsDistanceInteractor searchInteractor = new SearchRestaurantsDistanceInteractor(gateways);

        // Set the parameters for the search query
        double latitude = 43.6494; // Latitude coordinate for search location
        double longitude = -79.3870; // Longitude coordinate for search location
        String distance = "500"; // Distance from the location in meters to search within
        DishType dishType = null; // The type of cuisine to filter results by

        // Construct the SearchInput object using the search parameters
        SearchInput searchInput = new SearchInput(latitude, longitude, distance, dishType);

        // Perform the search using the interactor, requesting up to 5 results
        Optional<List<Restaurant>> result = searchInteractor.execute(searchInput, 10);

        // Output the search results or notify if no results were found
        if (result.isPresent()) {
            System.out.println("Search Results:");
            for (Restaurant restaurant : result.get()) {
                System.out.println(restaurant); // Print details of each restaurant found
            }
        } else {
            System.out.println("No restaurants found within the specified criteria or an error occurred.");
        }
    }
}
