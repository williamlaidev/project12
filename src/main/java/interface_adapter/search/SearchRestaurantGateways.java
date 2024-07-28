package interface_adapter.search;

import entity.*;
import domain.SearchInputBoundary;
import framework.search.GooglePlacesRestaurantSearchService;
import framework.config.EnvConfigService;
import framework.config.EnvConfigServiceImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.view.SearchInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchRestaurantGateways implements SearchInputBoundary {
    private final GooglePlacesRestaurantSearchService placesService;
    private final RestaurantMapper restaurantMapper;

    public SearchRestaurantGateways() {
        EnvConfigService envConfigService = new EnvConfigServiceImpl();
        this.placesService = new GooglePlacesRestaurantSearchService(envConfigService);
        this.restaurantMapper = new RestaurantMapper(placesService);
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

            return Optional.of(restaurants);
        } catch (Exception e) {
            System.err.println("Error during restaurant search: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private List<Restaurant> fetchNearbyRestaurants(Location location, DishType dishTypeFilter, int radius, int maxResults) throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();

        JSONObject response = placesService.fetchNearbyRestaurants(location.getLatitude(), location.getLongitude(), radius);

        parseRestaurantsFromResponse(restaurants, response, dishTypeFilter, maxResults);
        return restaurants;
    }

    private void parseRestaurantsFromResponse(List<Restaurant> restaurants, JSONObject response, DishType dishTypeFilter, int maxResults) throws Exception {
        JSONArray places = response.optJSONArray("results");
        if (places == null) {
            System.err.println("No results found in the response.");
            return;
        }

        for (int i = 0; i < Math.min(places.length(), maxResults); i++) {
            JSONObject place = places.getJSONObject(i);

            Restaurant restaurant = restaurantMapper.mapToRestaurant(place, dishTypeFilter);
            if (restaurant != null) {
                restaurants.add(restaurant);
                Double averageRating = restaurant.getAverageRating();
                String address = restaurant.getAddress();
                String photoUrl = restaurant.getPhotoUrl();
                String restaurantID =restaurant.getRestaurantId();


                    System.out.println("Restaurant Name: " + restaurant.getName() + "ID: " + restaurantID + "; Average Rating: " + averageRating + "; Address: " + address + "; PhotoUrl: " + photoUrl);
            }
        }
    }
}
