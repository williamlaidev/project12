package interface_adapter;

import entity.DishType;
import entity.Map;
import entity.SearchInput;
import use_case.SearchViewInteractor;
import utils.MapCoordinateToLocation;
import utils.ZoomLevelToMeter;

import java.awt.Point;

public class SearchController {
    private final SearchViewInteractor searchViewInteractor;
    private final double centerLat;
    private final double centerLng;
    private final int zoomLevel;
    private final int mapWidth;
    private final int mapHeight;

    public SearchController(SearchViewInteractor searchViewInteractor, Map map, int mapWidth, int mapHeight) {
        this.searchViewInteractor = searchViewInteractor;
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


        System.out.println("Selected dish type string: " + selectedDishType);

        // Convert selected dish type string to DishType enum
        DishType dishType = DishType.fromDishTypeString(selectedDishType);

        System.out.println("Selected dish type: " + dishType);

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

        // Create SearchInput object
        SearchInput searchInput = new SearchInput(latitude, longitude, distance, dishType);

        // Execute the search and handle any exceptions
        try {
            searchViewInteractor.execute(searchInput, 10); // Assuming maxResults is 10
        } catch (Exception e) {
            System.err.println("An error occurred while executing the search: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
