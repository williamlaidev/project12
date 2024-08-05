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

/**
 * Gateway class for searching restaurants using the Google Places API.
 * Implements the {@link SearchRestaurantService} interface to provide restaurant search functionality.
 */
public class SearchRestaurantGateways implements SearchRestaurantService {
    private final GooglePlacesRestaurantSearchService placesService;
    private final RestaurantMapper restaurantMapper;
    private static final Logger logger = LoggerFactory.getLogger(SearchRestaurantGateways.class);
    private final AddRestaurant addRestaurantUseCase;
    private final UpdateRestaurant updateRestaurantUseCase;
    private final FindRestaurantById findRestaurantByIdUseCase;

    /**
     * Constructs a {@code SearchRestaurantGateways} with dependencies for restaurant search,
     * including Google Places service and use cases for managing restaurants.
     */
    public SearchRestaurantGateways() {
        EnvConfigService envConfigService = new EnvConfigServiceImpl();
        this.placesService = new GooglePlacesRestaurantSearchService(envConfigService);
        this.restaurantMapper = new RestaurantMapper(placesService);
        SQLiteRestaurantRepository restaurantRepository = new SQLiteRestaurantRepository();
        this.addRestaurantUseCase = new AddRestaurant(restaurantRepository);
        this.updateRestaurantUseCase = new UpdateRestaurant(restaurantRepository);
        this.findRestaurantByIdUseCase = new FindRestaurantById(restaurantRepository);
    }

    /**
     * Executes a search for restaurants based on the provided {@link RestaurantSearchInput}.
     * Retrieves nearby restaurants, applies a dish type filter if specified, and limits the number of results.
     *
     * @param restaurantSearchInput the input containing search parameters including location, distance, and dish type
     * @param maxResults            the maximum number of restaurants to return
     * @return an {@link Optional} containing a list of {@link Restaurant} objects if the search is successful;
     *         otherwise, an empty {@link Optional}
     */
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

    /**
     * Fetches nearby restaurants from the Google Places API based on the provided location,
     * dish type filter, search radius, and maximum number of results.
     *
     * @param location        the {@link Location} to search around
     * @param dishTypeFilter the {@link DishType} filter to apply; {@code null} if no filtering is required
     * @param radius         the search radius in meters
     * @param maxResults     the maximum number of results to retrieve
     * @return a list of {@link Restaurant} objects found within the search parameters
     * @throws Exception if an error occurs while fetching data from the API
     */
    private List<Restaurant> fetchNearbyRestaurants(Location location, DishType dishTypeFilter, int radius, int maxResults) throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();

        JSONObject response = placesService.fetchNearbyRestaurants(location.getLatitude(), location.getLongitude(), radius);

        parseRestaurantsFromResponse(restaurants, response, dishTypeFilter, maxResults);
        return restaurants;
    }

    /**
     * Parses the JSON response from the Google Places API to extract and map restaurants.
     * Updates or adds restaurants to the repository based on their presence in the system.
     *
     * @param restaurants     the list to which parsed {@link Restaurant} objects will be added
     * @param response        the JSON response from the Google Places API
     * @param dishTypeFilter  the {@link DishType} filter to apply; {@code null} if no filtering is required
     * @param maxResults      the maximum number of results to process
     */
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
