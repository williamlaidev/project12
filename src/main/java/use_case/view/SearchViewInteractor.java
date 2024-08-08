package use_case.view;

import entity.map.Map;
import framework.EnvConfigServiceImpl;
import framework.search.GoogleMapsImageService;
import use_case.search.SearchRestaurantInput;

import java.util.logging.Logger;

/**
 * Manages map adjustments and interactions.
 */
public class SearchViewInteractor {
    private static final Logger LOGGER = Logger.getLogger(SearchViewInteractor.class.getName());
    private final Map currentMap;

    /**
     * Constructs a SearchViewInteractor with the given map.
     *
     * @param initialMap the initial map to manage
     */
    public SearchViewInteractor(Map initialMap) {
        this.currentMap = initialMap;
    }

    /**
     * Adjusts the map zoom level.
     *
     * @param zoomChange the amount to change the zoom level
     */
    public void adjustZoomLevel(int zoomChange) {
        int newZoomLevel = this.currentMap.getZoomLevel() + zoomChange;
        this.currentMap.setZoomLevel(newZoomLevel);
        updateMapImage();
        LOGGER.info("Zoom level adjusted to: " + newZoomLevel);
    }

    /**
     * Adjusts the map center based on new coordinates.
     *
     * @param searchRestaurantInput contains latitude and longitude for the new center
     */
    public void adjustCenter(SearchRestaurantInput searchRestaurantInput) {
        double latitude = searchRestaurantInput.getLatitude();
        double longitude = searchRestaurantInput.getLongitude();
        this.currentMap.setCurrentLatitude(latitude);
        this.currentMap.setCurrentLongitude(longitude);
        updateMapImage();
        LOGGER.info("Center adjusted to: " + latitude + ", " + longitude);
    }

    /**
     * Updates the map image based on current settings.
     */
    private void updateMapImage() {
        double latitude = this.currentMap.getCurrentLatitude();
        double longitude = this.currentMap.getCurrentLongitude();
        int zoomLevel = this.currentMap.getZoomLevel();

        MapImageInteractor mapImageInteractor = new MapImageInteractor(new GoogleMapsImageService(new EnvConfigServiceImpl()));
        mapImageInteractor.fetchAndSaveMapImage(latitude, longitude, zoomLevel, 400, 400);
    }
}
