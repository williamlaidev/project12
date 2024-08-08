package interface_adapter.view;

import entity.DishType;
import entity.map.Map;
import entity.restaurant.Restaurant;
import use_case.search.SearchRestaurantInput;
import use_case.search.RestaurantSearchInteractor;
import utils.MapCoordinateToLocation;

import java.awt.Point;

/**
 * Controls interactions from the SearchView and triggers use cases based on user actions,
 * such as searching and zooming on a map.
 */
public class SearchController {
    private final SearchViewInteractor searchViewInteractor;
    private final SearchPresenter searchPresenter;
    private double centerLat;
    private double centerLng;
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
     * @param searchState Current state of the SearchView including user inputs.
     */
    public void execute(SearchState searchState) {
        Point mousePosition = searchState.getMouseLeftClickPosition();
        String distance = searchState.getDistance();
        String selectedDishType = searchState.getSelectedDishType();

        double[] latLng = MapCoordinateToLocation.convert(mousePosition, centerLat, centerLng, zoomLevel, mapWidth, mapHeight);
        double latitude = latLng[0];
        double longitude = latLng[1];

        DishType dishType = DishType.valueOf(selectedDishType.toUpperCase()); // Converts string to enum
        RestaurantSearchInput inputData = new RestaurantSearchInput(latitude, longitude, distance, dishType);
        searchViewInteractor.execute(inputData, 50);
    }

    /**
     * Adjusts the zoom level of the map.
     */
    public void changeZoomLevel(int change, SearchState searchState) {
        this.zoomLevel += change;

        String distance = searchState.getDistance();
        String selectedDishType = searchState.getSelectedDishType();
        DishType dishType = DishType.valueOf(selectedDishType.toUpperCase()); // Converts string to enum

        RestaurantSearchInput inputData = new RestaurantSearchInput(centerLat, centerLng, distance, dishType);

        searchViewInteractor.adjustZoomLevel(change, inputData);
        searchPresenter.setZoomLevel(this.zoomLevel);
    }


    /**
     * Adjusts the center of the map.
     */
    public void changeCenter(SearchState searchState) {
        String distance = searchState.getDistance();
        String selectedDishType = searchState.getSelectedDishType();
        DishType dishType = DishType.valueOf(selectedDishType.toUpperCase()); // Converts string to enum

        Point rightClickPosition = searchState.getMouseRightClickPosition();

        double[] latLng = MapCoordinateToLocation.convert(rightClickPosition, centerLat, centerLng, zoomLevel, mapWidth, mapHeight);
        double latitude = latLng[0];
        double longitude = latLng[1];
        this.centerLat = latitude;
        this.centerLng = longitude;


        RestaurantSearchInput inputData = new RestaurantSearchInput(latitude, longitude, distance, dishType);

        searchViewInteractor.adjustCenter(inputData);

    }
}
