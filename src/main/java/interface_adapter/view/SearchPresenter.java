package interface_adapter.view;

import use_case.view.SearchOutputBoundary;
import use_case.view.SearchOutputData;
import utils.LocationToMapCoordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Presenter class responsible for bridging the use case output with the view model for the search functionality.
 */
public class SearchPresenter implements SearchOutputBoundary {

    private static final Logger logger = LoggerFactory.getLogger(SearchPresenter.class);

    private final SearchViewModel searchViewModel;
    private final RestaurantViewModel restaurantViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs a SearchPresenter with the specified view models and manager model.
     * @param viewManagerModel The ViewManagerModel to manage view transitions.
     * @param searchViewModel The SearchViewModel to update search-related information.
     * @param restaurantViewModel The RestaurantViewModel to update restaurant-related information.
     */
    public SearchPresenter(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, RestaurantViewModel restaurantViewModel) {
        this.searchViewModel = searchViewModel;
        this.restaurantViewModel = restaurantViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Sets the zoom level in the SearchViewModel.
     * @param zoomLevel The zoom level to be set.
     */
    public void setZoomLevel(int zoomLevel) {
        searchViewModel.setZoomLevel(zoomLevel);
    }

    /**
     * Prepares the success view with the search results and updates the view models.
     * @param result The SearchOutputData containing the search results.
     */
    @Override
    public void prepareSuccessView(SearchOutputData result) {
        // Update RestaurantViewModel with results
        List<String> restaurantInfo = result.getRestaurantsInfo();
        SearchState searchState = searchViewModel.getState();


        RestaurantState restaurantState = restaurantViewModel.getState();
        restaurantState.setRestaurantsInfo(result.getRestaurantsInfo());
        restaurantViewModel.setState(restaurantState);
        restaurantViewModel.firePropertyChanged();

        double centerLat = searchState.getCenterLat();
        double centerLon = searchState.getCenterLon();
        int mapWidth = searchState.getMapWidth();
        int mapHeight = searchState.getMapHeight();

        // Switch to RestaurantView
        viewManagerModel.setActiveView(restaurantViewModel.getViewName());

        // Process location data for each restaurant and update the search view with map markers
        result.getRestaurantsInfo().forEach(info -> {
            String[] parts = info.split(" - ");
            if (parts.length >= 6) {
                String name = parts[1];
                String locationString = parts[3]; // Assuming location is the fourth part in the formatted string
                try {
                    Point location = parseLocation(locationString, centerLat, centerLon, mapWidth, mapHeight);
                    searchViewModel.addMapMarker(location, name);
                } catch (NumberFormatException e) {
                    logger.error("Error parsing location: {}", locationString, e);
                }
            }
        });
        searchViewModel.firePropertyChanged();
    }

    public void prepareFailureView(String error) {
        logger.error(error);
    }

    public void prepareFailureView(String error,String distance, Exception e) {
        logger.error(error, distance, e);
    }

    public void prepareFailureView(String error,Exception e) {
        logger.error(error, e.getMessage());
    }

    /**
     * Parses a location string and converts it to a map coordinate point.
     * @param locationString The string containing location coordinates.
     * @param centerLat The latitude of the map's center.
     * @param centerLon The longitude of the map's center.
     * @param mapWidth The width of the map.
     * @param mapHeight The height of the map.
     * @return A Point representing the location on the map.
     * @throws NumberFormatException If the latitude or longitude cannot be parsed.
     */
    private Point parseLocation(String locationString, double centerLat, double centerLon, int mapWidth, int mapHeight) {
        // Removing all non-numeric characters except for the decimal point and minus sign
        String cleanedLocation = locationString.replaceAll("[^0-9.,-]", "");
        String[] latLng = cleanedLocation.split(",");
        try {
            double lat = Double.parseDouble(latLng[0].trim());
            double lng = Double.parseDouble(latLng[1].trim());
            return LocationToMapCoordinate.convert(lat, lng, centerLat, centerLon, searchViewModel.getZoomLevel(), mapWidth, mapHeight);
        } catch (NumberFormatException e) {
            logger.error("Failed to parse latitude or longitude from: {}", locationString, e);
            throw e;
        }
    }
}

