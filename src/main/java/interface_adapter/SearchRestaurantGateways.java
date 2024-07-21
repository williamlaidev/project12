package interface_adapter;

import entity.*;
import domain.SearchInputBoundary;
import framework.GooglePlacesRestaurantSearchService;
import framework.EnvConfigService;
import framework.EnvConfigServiceImpl;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link SearchInputBoundary} that interacts with various services to search for restaurants.
 */
public class SearchRestaurantGateways implements SearchInputBoundary {
    private final GooglePlacesRestaurantSearchService placesService;
    private final RestaurantMapper restaurantMapper;

    /**
     * Constructs a SearchRestaurantGateways instance with default services.
     */
    public SearchRestaurantGateways() {
        EnvConfigService envConfigService = new EnvConfigServiceImpl();
        this.placesService = new GooglePlacesRestaurantSearchService(envConfigService);
        this.restaurantMapper = new RestaurantMapper(placesService);
    }

    @Override
    public Optional<List<Restaurant>> search(SearchInput searchInput, int maxResults) {
        try {
            DishType dishTypeFilter = searchInput.getDishType();
            Location location = new Location(searchInput.getLatitude(), searchInput.getLongitude());
            System.out.println("Searching for nearby restaurants within "
                    + searchInput.getDistance() + " meters from location ("
                    + searchInput.getLatitude() + ", " + searchInput.getLongitude() + ").");

            List<Restaurant> restaurants = fetchNearbyRestaurants(location, dishTypeFilter, Integer.parseInt(searchInput.getDistance()), maxResults);

            return Optional.of(restaurants);
        } catch (Exception e) {
            System.err.println("Error during restaurant search: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Fetches nearby restaurants based on location, radius, and filters.
     *
     * @param location       the location for the search
     * @param dishTypeFilter the dish type to filter the results
     * @param radius         the search radius in meters
     * @param maxResults     the maximum number of results to return
     * @return a list of restaurants matching the criteria
     * @throws Exception if an error occurs during the fetch
     */
    private List<Restaurant> fetchNearbyRestaurants(Location location, DishType dishTypeFilter, int radius, int maxResults) throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        System.out.println("Calling placesService to fetch nearby restaurants...");

        JSONObject response = placesService.fetchNearbyRestaurants(location.getLatitude(), location.getLongitude(), radius);
        System.out.println("Successfully fetched nearby restaurants from placesService.");

        System.out.println("Parsing response from placesService...");
        parseRestaurantsFromResponse(restaurants, response, dishTypeFilter, maxResults);
        return restaurants;
    }

    /**
     * Parses the response from the places service and maps it to restaurant entities.
     *
     * @param restaurants    the list to store the parsed restaurants
     * @param response       the response from the places service
     * @param dishTypeFilter the dish type to filter the results
     * @param maxResults     the maximum number of results to parse
     * @throws Exception if an error occurs during parsing
     */
    private void parseRestaurantsFromResponse(List<Restaurant> restaurants, JSONObject response, DishType dishTypeFilter, int maxResults) throws Exception {
        JSONArray places = response.optJSONArray("results");
        if (places == null) {
            System.err.println("No results found in the response.");
            return;
        }

        System.out.println("Parsing " + places.length() + " places from response...");
        for (int i = 0; i < Math.min(places.length(), maxResults); i++) {
            JSONObject place = places.getJSONObject(i);
            System.out.println("Processing place " + (i + 1) + " of " + Math.min(places.length(), maxResults) + "...");

            Restaurant restaurant = restaurantMapper.mapToRestaurant(place, dishTypeFilter);
            if (restaurant != null) {
                restaurants.add(restaurant);
                System.out.println("Restaurant: " + restaurant.getName() + " has been added to the results.");
            }
        }
    }
}
