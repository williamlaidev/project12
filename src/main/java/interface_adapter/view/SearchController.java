package interface_adapter.view;

import entity.DishType;
import entity.map.Map;
import use_case.search.RestaurantSearchInteractor;
import use_case.search.SearchRestaurantInput;
import use_case.view.SearchViewInteractor;
import utils.MapCoordinateToLocation;

import java.awt.Point;

/**
 * Manages interactions between the SearchView and use cases, such as searching for restaurants and adjusting map views.
 */
public class SearchController {

    private final SearchViewInteractor searchViewInteractor;
    private final RestaurantSearchInteractor searchRestaurantInteractor;
    private final SearchPresenter searchPresenter;
    private double centerLat;
    private double centerLng;
    private final int mapWidth;
    private final int mapHeight;
    private int zoomLevel;

    /**
     * Constructs a SearchController with the given parameters.
     *
     * @param searchRestaurantInteractor The interactor to fetch restaurant data.
     * @param searchViewInteractor       The interactor for updating the search view.
     * @param searchPresenter            The presenter to update the view with search results.
     * @param map                        The initial map containing geographic information.
     * @param mapWidth                   The width of the map area.
     * @param mapHeight                  The height of the map area.
     */
    public SearchController(RestaurantSearchInteractor searchRestaurantInteractor,
                            SearchViewInteractor searchViewInteractor,
                            SearchPresenter searchPresenter,
                            Map map,
                            int mapWidth,
                            int mapHeight) {
        this.searchRestaurantInteractor = searchRestaurantInteractor;
        this.searchViewInteractor = searchViewInteractor;
        this.searchPresenter = searchPresenter;
        this.centerLat = map.getCurrentLatitude();
        this.centerLng = map.getCurrentLongitude();
        this.zoomLevel = map.getZoomLevel();
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }

    /**
     * Executes a search for nearby restaurants based on the current search state.
     * Converts mouse position to geographic coordinates and initiates the search.
     *
     * @param searchState The current state of the search view including user inputs and search parameters.
     */
    public void execute(SearchState searchState) {
        Point mousePosition = searchState.getMouseLeftClickPosition();
        String distance = searchState.getDistance();
        String selectedDishType = searchState.getSelectedDishType();

        double[] latLng = MapCoordinateToLocation.convert(mousePosition, centerLat, centerLng, zoomLevel, mapWidth, mapHeight);
        double latitude = latLng[0];
        double longitude = latLng[1];

        DishType dishType = DishType.valueOf(selectedDishType.toUpperCase());
        SearchRestaurantInput inputData = new SearchRestaurantInput(latitude, longitude, distance, dishType);
        int maxRestaurantsToSearch = 50;
        int maxResults = 10;
        searchRestaurantInteractor.fetchNearbyRestaurants(inputData, maxRestaurantsToSearch, maxResults);
    }

    /**
     * Updates the zoom level of the map based on user interaction.
     *
     * @param change       The amount by which to change the zoom level.
     * @param searchState  The current state of the search view.
     */
    public void changeZoomLevel(int change, SearchState searchState) {
        this.zoomLevel += change;

        searchViewInteractor.adjustZoomLevel(change);
        searchPresenter.setZoomLevel(this.zoomLevel);
    }

    /**
     * Updates the center position of the map based on user interaction.
     * Converts right-click position to geographic coordinates and adjusts the map center.
     *
     * @param searchState The current state of the search view including user inputs.
     */
    public void changeCenter(SearchState searchState) {
        String distance = searchState.getDistance();
        String selectedDishType = searchState.getSelectedDishType();
        DishType dishType = DishType.valueOf(selectedDishType.toUpperCase());

        Point rightClickPosition = searchState.getMouseRightClickPosition();

        double[] latLng = MapCoordinateToLocation.convert(rightClickPosition, centerLat, centerLng, zoomLevel, mapWidth, mapHeight);
        double latitude = latLng[0];
        double longitude = latLng[1];
        this.centerLat = latitude;
        this.centerLng = longitude;

        SearchRestaurantInput inputData = new SearchRestaurantInput(latitude, longitude, distance, dishType);

        searchViewInteractor.adjustCenter(inputData);
    }
}
