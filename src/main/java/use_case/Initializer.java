package use_case;

import api.GeolocationAPI;
import api.MapImageAPI;
import entity.DishType;
import entity.Location;
import org.json.JSONObject;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * Initializer class is responsible for creating and storing the necessary information
 * before the first screen of the application (SearchView) is displayed.
 * It fetches the current geographical location using the GeolocationAPI and stores it in a Location entity.
 * It also stores the dish types available in the application.
 * Additionally, it creates a map centered at the current location.
 */
public class Initializer {

    private GeolocationAPI geolocationAPI;
    private Location currentLocation;
    private DishType[] dishTypes;
    private MapImageInteractor mapImageInteractor;

    /**
     * Constructs an Initializer with a GeolocationAPI and a MapImageInteractor.
     *
     * @param geolocationAPI    An instance of GeolocationAPI to get the current location.
     * @param mapImageInteractor An instance of MapImageInteractor to fetch and save the map image.
     */
    public Initializer(GeolocationAPI geolocationAPI, MapImageInteractor mapImageInteractor) {
        this.geolocationAPI = geolocationAPI;
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
        JSONObject currentLocationJson = geolocationAPI.getCurrentLocation();
        double latitude = currentLocationJson.getJSONObject("location").getDouble("lat");
        double longitude = currentLocationJson.getJSONObject("location").getDouble("lng");

        this.currentLocation = new Location(latitude, longitude);

        // Create and save the map image centered at the current location
        int zoom = 15; // Approximate zoom level for 1km radius
        int width = 200; // Width of the image in pixels
        int height = 200; // Height of the image in pixels
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

    /**
     * Main method for testing purposes.
     */
    public static void main(String[] args) {
        try {
            Dotenv dotenv = Dotenv.load();
            String apiKey = dotenv.get("GOOGLE_MAPS_API_KEY");

            GeolocationAPI geolocationAPI = new GeolocationAPI();
            MapImageAPI mapImageAPI = new MapImageAPI();
            MapImageInteractor mapImageInteractor = new MapImageInteractor(mapImageAPI, apiKey);

            Initializer initializer = new Initializer(geolocationAPI, mapImageInteractor);
            initializer.initializeCurrentLocation();
            System.out.println("Current Location: Latitude = " + initializer.getLatitude() + ", Longitude = " + initializer.getLongitude());

            String[] dishTypes = initializer.getDishTypes();
            System.out.println("Available Dish Types:");
            for (String dishType : dishTypes) {
                System.out.println(dishType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

