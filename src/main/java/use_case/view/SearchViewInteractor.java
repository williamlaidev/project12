package use_case.view;

import entity.map.Map;
import framework.EnvConfigServiceImpl;
import framework.search.GoogleMapsImageService;
import use_case.search.SearchRestaurantInput;

import java.util.logging.Logger;

/**
 * Manages the business logic for adjusting map settings and interacting with map services.
 */
public class SearchViewInteractor {

    private static final Logger LOGGER = Logger.getLogger(SearchViewInteractor.class.getName());

    private final Map currentMap;

    /**
     * Creates a new SearchViewInteractor to manage the given map.
     *
     * @param initialMap the initial map to be managed by this interactor
     */
    public SearchViewInteractor(Map initialMap) {
        this.currentMap = initialMap;
    }

    /**
     * Adjusts the zoom level of the map by the specified amount.
     *
     * @param zoomChange the amount to change the zoom level (typically +1 or -1)
     */
    public void adjustZoomLevel(int zoomChange) {
        int newZoomLevel = this.currentMap.getZoomLevel() + zoomChange;
        this.currentMap.setZoomLevel(newZoomLevel);

        // Update the map image to reflect the new zoom level
        updateMapImage();

        LOGGER.info("Zoom level adjusted to: " + newZoomLevel);
    }

    /**
     * Changes the center of the map to the specified latitude and longitude.
     *
     * @param searchRestaurantInput contains latitude and longitude for the new map center
     */
    public void adjustCenter(SearchRestaurantInput searchRestaurantInput) {
        double latitude = searchRestaurantInput.getLatitude();
        double longitude = searchRestaurantInput.getLongitude();
        this.currentMap.setCurrentLatitude(latitude);
        this.currentMap.setCurrentLongitude(longitude);

        // Update the map image to reflect the new center
        updateMapImage();

        LOGGER.info("Center adjusted to: " + latitude + ", " + longitude);
    }

    /**
     * Updates the map image based on the current map's latitude, longitude, and zoom level.
     */
    private void updateMapImage() {
        double latitude = this.currentMap.getCurrentLatitude();
        double longitude = this.currentMap.getCurrentLongitude();
        int zoomLevel = this.currentMap.getZoomLevel();

        MapImageInteractor mapImageInteractor = new MapImageInteractor(new GoogleMapsImageService(new EnvConfigServiceImpl()));
        mapImageInteractor.fetchAndSaveMapImage(latitude, longitude, zoomLevel, 400, 400);
    }
}
