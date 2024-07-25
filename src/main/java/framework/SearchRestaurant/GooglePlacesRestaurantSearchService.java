package framework.SearchRestaurant;

import framework.APIKeyConfig.EnvConfigService;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Implementation of RestaurantSearchService that interacts with the Google Places API.
 * This class is responsible for fetching restaurant data and building photo URLs.
 */
public class GooglePlacesRestaurantSearchService implements RestaurantSearchService {

    private static final String PLACES_API_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";
    private final EnvConfigService envConfigService;

    /**
     * Constructs the GooglePlacesRestaurantSearchService with the provided EnvConfigService.
     *
     * @param envConfigService an instance of EnvConfigService for loading configuration
     */
    public GooglePlacesRestaurantSearchService(EnvConfigService envConfigService) {
        this.envConfigService = envConfigService;
    }

    /**
     * Fetches nearby restaurants from the Google Places API based on the provided latitude, longitude, and radius.
     *
     * @param latitude  the latitude of the location
     * @param longitude the longitude of the location
     * @param radius    the radius in meters within which to find restaurants
     * @return JSONObject containing the list of restaurants found
     * @throws Exception if there is an issue with the network or the API call fails
     */
    @Override
    public JSONObject fetchNearbyRestaurants(double latitude, double longitude, int radius) throws Exception {
        String apiKey = envConfigService.getGoogleMapsApiKey();
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("API key is missing. Please check your environment configuration.");
        }

        // Construct the URL with query parameters
        String requestUrl = PLACES_API_URL + "?key=" + apiKey + "&location=" + latitude + "," + longitude + "&radius=" + radius + "&type=restaurant";
        URL url = new URL(requestUrl);

        // Open a connection to the URL
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET"); // Set the request method to GET
        conn.setRequestProperty("Accept", "application/json"); // Set request header to accept JSON response

        // Check the response code
        int responseCode = conn.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : " + responseCode);
        }

        // Read the response from the input stream
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder response = new StringBuilder();
        String responseLine;

        while ((responseLine = br.readLine()) != null) {
            response.append(responseLine.trim());
        }

        br.close();
        conn.disconnect();

        // Convert the response to a JSONObject and return it
        return new JSONObject(response.toString());
    }

    /**
     * Builds the URL to access a photo from the Google Places API based on its reference.
     *
     * @param photoReference the photo reference from the Places API
     * @return the complete URL to access the photo
     */
    @Override
    public String buildPhotoUrl(String photoReference) {
        String apiKey = envConfigService.getGoogleMapsApiKey();
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("API key is missing. Please check your environment configuration.");
        }

        // Construct and return the photo URL
        return "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=" + photoReference + "&key=" + apiKey;
    }
}
