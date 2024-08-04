package use_case.view;

import framework.search.GoogleGeolocationService;
import framework.search.GoogleMapsImageService;
import framework.config.EnvConfigServiceImpl;
import entity.DishType;
import entity.Location;
import entity.Map;
import entity.MapFactory;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Initializer class is responsible for creating and storing the necessary information
 * before the first screen of the application (SearchView) is displayed.
 * It fetches the current geographical location using the GoogleGeolocationService and stores it in a Location entity.
 * It also stores the dish types available in the application.
 * Additionally, it creates a map centered at the current location.
 */
public class Initializer {

    private static final Logger logger = LoggerFactory.getLogger(Initializer.class);

    private final GoogleGeolocationService geolocationService;
    private Location currentLocation;
    private final DishType[] dishTypes;
    private final MapImageInteractor mapImageInteractor;
    private Map map;

    /**
     * Constructs an Initializer with a GoogleGeolocationService and a MapImageInteractor.
     *
     * @param geolocationService    An instance of GoogleGeolocationService to get the current location.
     * @param mapImageInteractor    An instance of MapImageInteractor to fetch and save the map image.
     */
    public Initializer(GoogleGeolocationService geolocationService, MapImageInteractor mapImageInteractor) {
        this.geolocationService = geolocationService;
        this.dishTypes = DishType.values(); // Initialize dish types
        this.mapImageInteractor = mapImageInteractor;
    }

    /**
     * Initializes and stores a Location entity with the current geographical location.
     * Also creates a map image centered at the current location.
     *
     * @throws Exception if there is a network issue or the API call fails.
     */
    public void initializeCurrentLocation() throws Exception {
        JSONObject currentLocationJson = geolocationService.getCurrentLocation();
        double latitude = currentLocationJson.getJSONObject("location").getDouble("lat");
        double longitude = currentLocationJson.getJSONObject("location").getDouble("lng");

        this.currentLocation = new Location(latitude, longitude);

        // Create a map entity
        int zoom = 15; // Approximate zoom level for 1km radius
        int width = 400; // Width of the image in pixels
        int height = 400; // Height of the image in pixels

        this.map = MapFactory.createMap(latitude, longitude, zoom, width, height); // Empty list of restaurant IDs for now

        // Create and save the map image centered at the current location

        boolean success = mapImageInteractor.fetchAndSaveMapImage(latitude, longitude, zoom, width, height);
        if (!success) {
            throw new RuntimeException("Failed to fetch and save the map image.");
        }
    }

    public void initializeNewMap(int zoom, double latitude, double longitude) throws Exception {
        JSONObject currentLocationJson = geolocationService.getCurrentLocation();

        this.currentLocation = new Location(latitude, longitude);

        // Create a map entity
        int width = 400; // Width of the image in pixels
        int height = 400; // Height of the image in pixels

        this.map = MapFactory.createMap(latitude, longitude, zoom, width, height); // Empty list of restaurant IDs for now

        // Create and save the map image centered at the current location

        boolean success = mapImageInteractor.fetchAndSaveMapImage(latitude, longitude, zoom, width, height);
        if (!success) {
            throw new RuntimeException("Failed to fetch and save the map image.");
        }
    }

    /**
     * Gets the latitude of the current location.
     *
     * @return The latitude of the current location.
     */
    public double getLatitude() {
        return currentLocation.getLatitude();
    }

    /**
     * Gets the longitude of the current location.
     *
     * @return The longitude of the current location.
     */
    public double getLongitude() {
        return currentLocation.getLongitude();
    }

    /**
     * Gets the available dish types as an array of Strings.
     *
     * @return An array of dish type names.
     */
    public String[] getDishTypes() {
        String[] dishTypeNames = new String[dishTypes.length];
        for (int i = 0; i < dishTypeNames.length; i++) {
            dishTypeNames[i] = dishTypes[i].name();
        }
        return dishTypeNames;
    }

    public Map getMap() {
        return map;
    }

    /**
     * Main method for testing purposes.
     */
    public static void main(String[] args) {
        try {
            // Initialize EnvConfigService and GoogleGeolocationService
            EnvConfigServiceImpl envConfigService = new EnvConfigServiceImpl();
            GoogleGeolocationService geolocationService = new GoogleGeolocationService(envConfigService);

            // Initialize GoogleMapsImageService with EnvConfigService
            GoogleMapsImageService googleMapsImageService = new GoogleMapsImageService(envConfigService);
            MapImageInteractor mapImageInteractor = new MapImageInteractor(googleMapsImageService);

            // Initialize the Initializer with the updated services
            Initializer initializer = new Initializer(geolocationService, mapImageInteractor);
            initializer.initializeCurrentLocation();
            System.out.println("Current Location: Latitude = " + initializer.getLatitude() + ", Longitude = " + initializer.getLongitude());

            String[] dishTypes = initializer.getDishTypes();
            System.out.println("Available Dish Types:");
            for (String dishType : dishTypes) {
                System.out.println(dishType);
            }
        } catch (Exception e) {
            logger.error("An error occurred during initialization", e);
        }
    }
}
