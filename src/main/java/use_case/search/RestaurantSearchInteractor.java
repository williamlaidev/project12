package use_case.search;

import domain.RestaurantSearchService;
import entity.DishType;
import entity.restaurant.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements the {@link RestaurantSearchService} for searching restaurants.
 * Uses a search strategy to perform the search.
 */
public class RestaurantSearchInteractor implements RestaurantSearchService {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantSearchInteractor.class);
    private final SearchRestaurants searchStrategy;

    /**
     * Constructs an instance with the given search strategy.
     *
     * @param searchStrategy the strategy used to search for restaurants.
     */
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
            logger.error("Error parsing distance input: '{}' is not a valid number. Please enter a valid numerical value.", distance, e);
            return new ArrayList<>();
        } catch (Exception e) {
            logger.error("An unexpected error occurred during restaurant search with input (latitude: {}, longitude: {}, distance: {}, dishType: {}): {}",
                    latitude, longitude, distance, dishType, e.getMessage(), e);
            return new ArrayList<>();
        }
    }
}
