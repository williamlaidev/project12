package use_case.view;

import framework.search.GoogleGeolocationService;
import entity.DishType;
import entity.location.Location;
import entity.map.Map;
import entity.map.MapFactory;
import org.json.JSONObject;

/**
 * Initializes application data including the current location, available dish types, and a map.
 * Uses GoogleGeolocationService to get the current location and MapFactory to create a map.
 */
public class Initializer {

    private final GoogleGeolocationService geolocationService;
    private final DishType[] dishTypes;
    private final MapImageInteractor mapImageInteractor;
    private final MapFactory mapFactory;
    private Location currentLocation;
    private Map map;

    /**
     * Constructs an Initializer.
     *
     * @param geolocationService    Service for obtaining current location.
     * @param mapImageInteractor    Service for fetching and saving map images.
     * @param mapFactory            Factory for creating map objects.
     */
    public Initializer(GoogleGeolocationService geolocationService,
                       MapImageInteractor mapImageInteractor,
                       MapFactory mapFactory) {
        this.geolocationService = geolocationService;
        this.dishTypes = DishType.values();
        this.mapImageInteractor = mapImageInteractor;
        this.mapFactory = mapFactory;
    }

    /**
     * Fetches the current location and initializes a map centered at this location.
     *
     * @throws Exception if there is an error during location retrieval or map image saving.
     */
    public void initializeCurrentLocation() throws Exception {
        JSONObject locationJson = geolocationService.getCurrentLocation();
        double latitude = locationJson.getJSONObject("location").getDouble("lat");
        double longitude = locationJson.getJSONObject("location").getDouble("lng");

        this.currentLocation = new Location(latitude, longitude);

        // Create and save map image
        int zoom = 15; // Approximate zoom level
        int width = 400; // Image width
        int height = 400; // Image height

        this.map = mapFactory.createMap(latitude, longitude, zoom, width, height);
        boolean success = mapImageInteractor.fetchAndSaveMapImage(latitude, longitude, zoom, width, height);
        if (!success) {
            throw new RuntimeException("Failed to fetch and save the map image.");
        }
    }

    /**
     * Gets the latitude of the current location.
     *
     * @return Latitude of the current location.
     */
    public double getLatitude() {
        return currentLocation.getLatitude();
    }

    /**
     * Gets the longitude of the current location.
     *
     * @return Longitude of the current location.
     */
    public double getLongitude() {
        return currentLocation.getLongitude();
    }

    /**
     * Provides an array of dish type names.
     *
     * @return Array of dish type names.
     */
    public String[] getDishTypes() {
        return java.util.Arrays.stream(dishTypes)
                .map(DishType::name)
                .toArray(String[]::new);
    }

    /**
     * Gets the current map object.
     *
     * @return The current map.
     */
    public Map getMap() {
        return map;
    }
}
