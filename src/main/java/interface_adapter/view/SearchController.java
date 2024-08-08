package interface_adapter.view;

import entity.DishType;
import entity.map.Map;
import entity.restaurant.Restaurant;
import use_case.search.SearchRestaurantInput;
import use_case.search.RestaurantSearchInteractor;
import utils.MapCoordinateToLocation;
import utils.ZoomLevelToMeter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Point;
import java.util.List;

public class SearchController {
    private final RestaurantSearchInteractor searchInteractor;
    private final double centerLat;
    private final double centerLng;
    private final int zoomLevel;
    private final int mapWidth;
    private final int mapHeight;
    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    public SearchController(RestaurantSearchInteractor searchInteractor, Map map, int mapWidth, int mapHeight) {
        this.searchInteractor = searchInteractor;
        this.centerLat = map.getCurrentLatitude();
        this.centerLng = map.getCurrentLongitude();
        this.zoomLevel = map.getZoomLevel();
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }

    public void execute(SearchViewState searchViewState) {
        Point mousePosition = searchViewState.getMousePosition();
        String distance = searchViewState.getDistance();
        String selectedDishType = searchViewState.getSelectedDishType();

        // Convert zoom level to distance if not provided
        if (distance.isEmpty()) {
            distance = Double.toString(ZoomLevelToMeter.zoomLevelToMeter(zoomLevel, centerLat, mapWidth));
        }

        // Convert selected dish type string to DishType enum
        DishType dishType = DishType.fromDishTypeString(selectedDishType);

        // Determine latitude and longitude based on mouse position or default to center
        double latitude;
        double longitude;
        if (mousePosition != null) {
            double[] latLng = MapCoordinateToLocation.convert(mousePosition, centerLat, centerLng, zoomLevel, mapWidth, mapHeight);
            latitude = latLng[0];
            longitude = latLng[1];
        } else {
            latitude = centerLat;
            longitude = centerLng;
        }

        // Create SearchRestaurantInput object
        SearchRestaurantInput searchInput = new SearchRestaurantInput(latitude, longitude, distance, dishType);

        // Define maxRestaurantsToSearch and maxResults parameters
        int maxRestaurantsToSearch = 30; // Example value, adjust as needed
        int maxResults = 10; // Example value, adjust as needed

        // Execute the search and handle any exceptions
        try {
            List<Restaurant> results = searchInteractor.fetchNearbyRestaurants(searchInput, maxRestaurantsToSearch, maxResults);
            // Handle results (e.g., update UI or display results)
        } catch (Exception e) {
            System.err.println("An error occurred while executing the search: " + e.getMessage()); // Retaining the println call
            logger.error("An error occurred while executing the search: {}", e.getMessage(), e);
        }
    }
}
