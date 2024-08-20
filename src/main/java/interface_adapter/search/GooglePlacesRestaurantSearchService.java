package interface_adapter.search;

import domain.RestaurantSearchService;
import entity.restaurant.Restaurant;
import entity.DishType;
import interface_adapter.view.SearchPresenter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import use_case.data.create.AddRestaurant;
import use_case.data.read.FindRestaurantById;
import use_case.data.update.UpdateRestaurant;
import use_case.search.FetchRestaurantPhotoUrl;
import use_case.search.SearchRestaurantInput;
import use_case.view.SearchOutputData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for searching restaurants using Google Places API and managing search results.
 */
public class GooglePlacesRestaurantSearchService implements RestaurantSearchService {

    private final RestaurantSearchGateways searchGateways;
    private final GooglePlacesRestaurantSearchAdapter inputAdapter;
    private static final Logger logger = LoggerFactory.getLogger(GooglePlacesRestaurantSearchService.class);
    private final AddRestaurant addRestaurantUseCase;
    private final UpdateRestaurant updateRestaurantUseCase;
    private final FindRestaurantById findRestaurantByIdUseCase;
    private final FetchRestaurantPhotoUrl fetchRestaurantPhotoUrlUseCase;
    private final SearchPresenter searchPresenter;

    /**
     * Constructs a GooglePlacesRestaurantSearchService with the given dependencies.
     *
     * @param searchGateways             Gateway for fetching restaurant data
     * @param inputAdapter               Adapter for converting JSON data to Restaurant entities
     * @param addRestaurantUseCase       Use case for adding restaurants
     * @param updateRestaurantUseCase    Use case for updating restaurants
     * @param findRestaurantByIdUseCase  Use case for finding restaurants by ID
     * @param fetchRestaurantPhotoUrlUseCase Use case for fetching restaurant photo URLs
     * @param searchPresenter            Presenter for preparing search results view
     */
    public GooglePlacesRestaurantSearchService(RestaurantSearchGateways searchGateways,
                                               GooglePlacesRestaurantSearchAdapter inputAdapter,
                                               AddRestaurant addRestaurantUseCase,
                                               UpdateRestaurant updateRestaurantUseCase,
                                               FindRestaurantById findRestaurantByIdUseCase,
                                               FetchRestaurantPhotoUrl fetchRestaurantPhotoUrlUseCase,
                                               SearchPresenter searchPresenter) {
        this.searchGateways = searchGateways;
        this.inputAdapter = inputAdapter;
        this.addRestaurantUseCase = addRestaurantUseCase;
        this.updateRestaurantUseCase = updateRestaurantUseCase;
        this.findRestaurantByIdUseCase = findRestaurantByIdUseCase;
        this.fetchRestaurantPhotoUrlUseCase = fetchRestaurantPhotoUrlUseCase;
        this.searchPresenter = searchPresenter;
    }

    /**
     * Fetches nearby restaurants based on the search input and updates the list of restaurants.
     *
     * @param searchInput                Search parameters including location and dish type
     * @param maxRestaurantsToSearch     Maximum number of restaurants to search
     * @param maxResults                 Maximum number of results to return
     * @return List of restaurants found in the search
     */
    @Override
    public List<Restaurant> fetchNearbyRestaurants(SearchRestaurantInput searchInput, int maxRestaurantsToSearch, int maxResults) {
        logger.info("Starting search for restaurants within {} meters from location ({}, {}).",
                searchInput.getDistance(), searchInput.getLatitude(), searchInput.getLongitude());

        JSONObject response;
        try {
            response = searchGateways.fetchNearbyRestaurants(searchInput.getLatitude(), searchInput.getLongitude(), Integer.parseInt(searchInput.getDistance()));
        } catch (IOException | JSONException e) {
            logger.error("Failed to fetch restaurants: {}", e.getMessage());
            return List.of();
        }

        JSONArray places = response.optJSONArray("results");
        if (places == null) {
            logger.warn("No results found in the response.");
            return List.of();
        }

        List<Restaurant> restaurants = new ArrayList<>();
        int resultCount = 0;

        for (int i = 0; i < Math.min(places.length(), maxRestaurantsToSearch); i++) {
            if (resultCount >= maxResults) {
                break;
            }

            JSONObject place = places.getJSONObject(i);
            String photoUrl = fetchRestaurantPhotoUrlUseCase.execute(place);
            Restaurant restaurant = inputAdapter.adaptToRestaurant(place);
            restaurant.setPhotoUrl(photoUrl);

            if (isDishTypeMatching(searchInput.getDishType(), restaurant)) {
                saveOrUpdateRestaurant(restaurant);
                restaurants.add(restaurant);
                logRestaurantDetails(restaurant);
                resultCount++;
            }
        }

        // Prepare SearchOutputData
        SearchOutputData outputData = new SearchOutputData(restaurants);

        // Call prepareSuccessView
        searchPresenter.prepareSuccessView(outputData);

        return restaurants;
    }

    /**
     * Checks if the restaurant's dish type matches the filtered dish type.
     *
     * @param filteredDishType The dish type to filter
     * @param restaurant       The restaurant to check
     * @return True if the dish types match, otherwise false
     */
    private boolean isDishTypeMatching(DishType filteredDishType, Restaurant restaurant) {
        if (filteredDishType == null) {
            return true;
        }
        return filteredDishType == restaurant.getDishType();
    }

    /**
     * Saves or updates the restaurant in the system.
     *
     * @param restaurant The restaurant to be saved or updated
     */
    private void saveOrUpdateRestaurant(Restaurant restaurant) {
        if (findRestaurantByIdUseCase.execute(restaurant.getRestaurantId()).isPresent()) {
            updateRestaurantUseCase.execute(restaurant);
        } else {
            addRestaurantUseCase.execute(restaurant);
        }
    }

    /**
     * Logs details of the restaurant.
     *
     * @param restaurant The restaurant whose details are to be logged
     */
    private void logRestaurantDetails(Restaurant restaurant) {
        double averageRating = restaurant.getAverageRating();
        String address = restaurant.getAddress();
        String photoUrl = restaurant.getPhotoUrl();
        String restaurantID = restaurant.getRestaurantId();
        DishType dishType = restaurant.getDishType(); // Fetch the dish type

        logger.info("Restaurant Name: {} ID: {}; Average Rating: {}; Address: {}; Dish Type: {}; PhotoUrl: {}",
                restaurant.getName(), restaurantID, averageRating, address, dishType, photoUrl);
    }
}
