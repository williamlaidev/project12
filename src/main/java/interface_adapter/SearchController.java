package interface_adapter;

import entity.Map;
import use_case.SearchViewInteractor;
import use_case.SearchInputData;
import java.awt.Point;
import utils.MapCoordinateToLocation;
import utils.ZoomLevelToMeter;

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

        if (distance == null || distance.isEmpty()) {
            // Calculate the map scale in meters if the distance is null or empty
            double mapSizeMeters = ZoomLevelToMeter.zoomLevelToMeter(zoomLevel, centerLat, mapWidth);
            distance = String.valueOf(mapSizeMeters);
        }

        if (mousePosition != null) {
            double[] latLng = MapCoordinateToLocation.convert(mousePosition, centerLat, centerLng, zoomLevel, mapWidth, mapHeight);
            double latitude = latLng[0];
            double longitude = latLng[1];

            searchViewInteractor.execute(new SearchInputData(latitude, longitude, distance, selectedDishType));
        } else {
            searchViewInteractor.execute(new SearchInputData(centerLat, centerLng, distance, selectedDishType));
        }
    }
}

