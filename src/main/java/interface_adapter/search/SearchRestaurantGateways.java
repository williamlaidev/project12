package interface_adapter.search;

import entity.*;
import domain.SearchRestaurantService;
import interface_adapter.data.SQLiteRestaurantRepository;
import use_case.data.AddRestaurant;
import use_case.data.UpdateRestaurant;
import use_case.data.FindRestaurantById;
import framework.search.GooglePlacesRestaurantSearchService;
import framework.config.EnvConfigService;
import framework.config.EnvConfigServiceImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import use_case.search.RestaurantSearchInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchRestaurantGateways implements SearchRestaurantService {
    private final GooglePlacesRestaurantSearchService placesService;
    private final RestaurantMapper restaurantMapper;
    private static final Logger logger = LoggerFactory.getLogger(SearchRestaurantGateways.class);
    private final AddRestaurant addRestaurantUseCase;
    private final UpdateRestaurant updateRestaurantUseCase;
    private final FindRestaurantById findRestaurantByIdUseCase;

    public SearchRestaurantGateways() {
        EnvConfigService envConfigService = new EnvConfigServiceImpl();
        this.placesService = new GooglePlacesRestaurantSearchService(envConfigService);
        this.restaurantMapper = new RestaurantMapper(placesService);
        SQLiteRestaurantRepository restaurantRepository = new SQLiteRestaurantRepository();
        this.addRestaurantUseCase = new AddRestaurant(restaurantRepository);
        this.updateRestaurantUseCase = new UpdateRestaurant(restaurantRepository);
        this.findRestaurantByIdUseCase = new FindRestaurantById(restaurantRepository);
    }

    @Override
    public Optional<List<Restaurant>> execute(RestaurantSearchInput restaurantSearchInput, int maxResults) {
        try {
            DishType dishTypeFilter = restaurantSearchInput.getDishType();
            Location location = new Location(restaurantSearchInput.getLatitude(), restaurantSearchInput.getLongitude());

            System.out.println("Starting search for restaurants within "
                    + restaurantSearchInput.getDistance() + " meters from location ("
                    + restaurantSearchInput.getLatitude() + ", " + restaurantSearchInput.getLongitude() + ").");

            List<Restaurant> restaurants = fetchNearbyRestaurants(location, dishTypeFilter, Integer.parseInt(restaurantSearchInput.getDistance()), maxResults);

            return Optional.of(restaurants);
        } catch (Exception e) {
            System.err.println("Error during restaurant search: " + e.getMessage());
            logger.error("Error during restaurant search: {}", e.getMessage(), e);
            return Optional.empty();
        }
    }

    private List<Restaurant> fetchNearbyRestaurants(Location location, DishType dishTypeFilter, int radius, int maxResults) throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();

        JSONObject response = placesService.fetchNearbyRestaurants(location.getLatitude(), location.getLongitude(), radius);

        parseRestaurantsFromResponse(restaurants, response, dishTypeFilter, maxResults);
        return restaurants;
    }

    private void parseRestaurantsFromResponse(List<Restaurant> restaurants, JSONObject response, DishType dishTypeFilter, int maxResults) {
        JSONArray places = response.optJSONArray("results");
        if (places == null) {
            System.err.println("No results found in the response.");
            logger.warn("No results found in the response.");
            return;
        }

        for (int i = 0; i < Math.min(places.length(), maxResults); i++) {
            JSONObject place = places.getJSONObject(i);

            Restaurant restaurant = restaurantMapper.mapToRestaurant(place, dishTypeFilter);
            if (restaurant != null) {
                restaurants.add(restaurant);

                Optional<Restaurant> existingRestaurant = findRestaurantByIdUseCase.execute(restaurant.getRestaurantId());

                if (existingRestaurant.isPresent()) {
                    updateRestaurantUseCase.execute(restaurant);
                } else {
                    addRestaurantUseCase.execute(restaurant);
                }

                double averageRating = restaurant.getAverageRating();
                String address = restaurant.getAddress();
                String photoUrl = restaurant.getPhotoUrl();
                String restaurantID = restaurant.getRestaurantId();

                System.out.println("Restaurant Name: " + restaurant.getName() + " ID: " + restaurantID + "; Average Rating: " + averageRating + "; Address: " + address + "; PhotoUrl: " + photoUrl);
            }
        }
    }
}
