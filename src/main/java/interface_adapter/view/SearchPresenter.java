package interface_adapter.view;

import use_case.view.SearchOutputBoundary;
import use_case.view.SearchOutputData;
import utils.LocationToMapCoordinate;

import java.awt.*;

/**
 * Presenter class for the search functionality, bridging the use case output with the view model.
 */
public class SearchPresenter implements SearchOutputBoundary {
    private final SearchViewModel searchViewModel;
    private final RestaurantViewModel restaurantViewModel;
    private final ViewManagerModel viewManagerModel;

    public SearchPresenter(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, RestaurantViewModel restaurantViewModel) {

        this.searchViewModel = searchViewModel;
        this.restaurantViewModel = restaurantViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void setZoomLevel(int zoomLevel) {
        searchViewModel.setZoomLevel(zoomLevel);
    }

    @Override
    public void prepareFailView(String error) {
        SearchState state = searchViewModel.getState();
        state.setDistanceError(error);
        searchViewModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(SearchOutputData result, double centerLat, double centerLon, int mapWidth, int mapHeight) {
        // Update RestaurantViewModel with results
        RestaurantState restaurantState = restaurantViewModel.getState();
        restaurantState.setRestaurantsInfo(result.getRestaurantsInfo());
        restaurantViewModel.setState(restaurantState);
        restaurantViewModel.firePropertyChanged();

        // Switch to RestaurantView
        viewManagerModel.setActiveView(restaurantViewModel.getViewName());

        // Extract and process location data for each restaurant string info and update the search view with map markers
        result.getRestaurantsInfo().forEach(info -> {
            String[] parts = info.split(" - ");
            if (parts.length >= 6) {
                String name = parts[1];
                String locationString = parts[3]; // Assuming location is the fourth part in the formatted string
                System.out.println("Location string: " + locationString);
                Point location = parseLocation(locationString, centerLat, centerLon, mapWidth, mapHeight);
                searchViewModel.addMapMarker(location, name);
            }
        });
        searchViewModel.firePropertyChanged();
    }

    private Point parseLocation(String locationString, double centerLat, double centerLon, int mapWidth, int mapHeight) {
        // Removing all non-numeric characters except for the decimal point and minus sign
        String cleanedLocation = locationString.replaceAll("[^0-9.,-]", "");
        String[] latLng = cleanedLocation.split(",");
        try {
            double lat = Double.parseDouble(latLng[0].trim());
            double lng = Double.parseDouble(latLng[1].trim());
            return LocationToMapCoordinate.convert(lat, lng, centerLat, centerLon, searchViewModel.getZoomLevel(), mapWidth, mapHeight);
        } catch (NumberFormatException e) {
            System.err.println("Failed to parse latitude or longitude from: " + locationString);
            throw e;
        }
    }
}
