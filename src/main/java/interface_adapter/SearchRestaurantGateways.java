package interface_adapter;

import domain.RestaurantRepository;
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

public class SearchRestaurantGateways implements SearchInputBoundary {
    private final GooglePlacesRestaurantSearchService placesService;
    private final RestaurantMapper restaurantMapper;
    private final RestaurantRepository restaurantRepository;

    public SearchRestaurantGateways(RestaurantRepository restaurantRepository) {
        EnvConfigService envConfigService = new EnvConfigServiceImpl();
        this.placesService = new GooglePlacesRestaurantSearchService(envConfigService);
        this.restaurantMapper = new RestaurantMapper(placesService);
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Optional<List<Restaurant>> execute(SearchInput searchInput, int maxResults) {
        try {
            DishType dishTypeFilter = searchInput.getDishType();
            Location location = new Location(searchInput.getLatitude(), searchInput.getLongitude());

            System.out.println("Starting search for restaurants within "
                    + searchInput.getDistance() + " meters from location ("
                    + searchInput.getLatitude() + ", " + searchInput.getLongitude() + ").");

            List<Restaurant> restaurants = fetchNearbyRestaurants(location, dishTypeFilter, Integer.parseInt(searchInput.getDistance()), maxResults);

            System.out.println("Search completed. Found " + restaurants.size() + " restaurant(s).");

            // Save the found restaurants to the repository
            for (Restaurant restaurant : restaurants) {
                restaurantRepository.add(restaurant);
            }

            return Optional.of(restaurants);
        } catch (Exception e) {
            System.err.println("Error during restaurant search: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private List<Restaurant> fetchNearbyRestaurants(Location location, DishType dishTypeFilter, int radius, int maxResults) throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        System.out.println("Calling placesService to fetch nearby restaurants...");

        JSONObject response = placesService.fetchNearbyRestaurants(location.getLatitude(), location.getLongitude(), radius);
        System.out.println("Successfully fetched nearby restaurants from placesService.");

        parseRestaurantsFromResponse(restaurants, response, dishTypeFilter, maxResults);
        return restaurants;
    }

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
