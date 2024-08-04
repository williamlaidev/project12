package interface_adapter.view;

import entity.DishType;
import entity.Map;
import use_case.search.RestaurantSearchInput;
import use_case.view.SearchViewInteractor;
import utils.MapCoordinateToLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Point;

/**
 * Controls interactions from the SearchView and triggers use cases based on user actions,
 * such as searching and zooming on a map.
 */
public class SearchController {
    private final SearchViewInteractor searchViewInteractor;
    private final SearchPresenter searchPresenter;
    private final double centerLat;
    private final double centerLng;
    private final int mapWidth;
    private final int mapHeight;
    private int zoomLevel; // Mutable state to handle zoom level changes dynamically

    /**
     * Initializes the SearchController with necessary components and map information.
     *
     * @param searchViewInteractor The interactor handling search and map updates.
     * @param map                  The initial map setup.
     * @param mapWidth             The width of the map area.
     * @param mapHeight            The height of the map area.
     */
    public SearchController(SearchViewInteractor searchViewInteractor, SearchPresenter searchPresenter, Map map, int mapWidth, int mapHeight) {
        this.searchViewInteractor = searchViewInteractor;
        this.searchPresenter = searchPresenter;
        this.centerLat = map.getCurrentLatitude();
        this.centerLng = map.getCurrentLongitude();
        this.zoomLevel = map.getZoomLevel();
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }

    /**
     * Executes the search based on the state captured from the SearchView.
     * Converts mouse position to geographic coordinates and initiates a search.
     *
     * @param searchViewState Current state of the SearchView including user inputs.
     */
    public void execute(SearchViewState searchViewState) {
        Point mousePosition = searchViewState.getMousePosition();
        String distance = searchViewState.getDistance();
        String selectedDishType = searchViewState.getSelectedDishType();

        double[] latLng = MapCoordinateToLocation.convert(mousePosition, centerLat, centerLng, zoomLevel, mapWidth, mapHeight);
        double latitude = latLng[0];
        double longitude = latLng[1];

        DishType dishType = DishType.valueOf(selectedDishType.toUpperCase()); // Converts string to enum
        RestaurantSearchInput inputData = new RestaurantSearchInput(latitude, longitude, distance, dishType);
        searchViewInteractor.execute(inputData, 10);
    }

    /**
     * Adjusts the zoom level of the map.
     */
    public void changeZoomLevel(int change) {
        this.zoomLevel += change;
        searchPresenter.updateZoomLevel(this.zoomLevel);
    }
}