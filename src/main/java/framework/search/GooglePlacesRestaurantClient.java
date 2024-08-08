package framework.search;

import framework.EnvConfigService;
import interface_adapter.search.RestaurantSearchGateways;
import org.json.JSONObject;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Client for fetching nearby restaurant data from Google Places API.
 * Implements the RestaurantSearchGateways interface.
 */
public class GooglePlacesRestaurantClient implements RestaurantSearchGateways {

    private static final String PLACES_API_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";
    private final EnvConfigService envConfigService;

    /**
     * Constructs a new GooglePlacesRestaurantClient.
     *
     * @param envConfigService The service for accessing environment configuration.
     */
    public GooglePlacesRestaurantClient(EnvConfigService envConfigService) {
        this.envConfigService = envConfigService;
    }

    /**
     * Fetches nearby restaurants based on latitude, longitude, and radius.
     * Constructs a request URL using the given parameters and API key, and performs an HTTP GET request
     * to the Google Places API. Parses the response into a JSON object.
     *
     * @param latitude  The latitude of the location.
     * @param longitude The longitude of the location.
     * @param radius    The search radius in meters.
     * @return A JSONObject containing the search results.
     * @throws IOException  If there is an error with the network connection or reading the response.
     * @throws JSONException If there is an error parsing the JSON response.
     * @throws IllegalArgumentException If the API key is missing or the URL is invalid.
     */
    @Override
    public JSONObject fetchNearbyRestaurants(double latitude, double longitude, int radius) throws IOException, JSONException {
        String apiKey = envConfigService.getGoogleMapsApiKey();
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("API key is missing. Please check your environment configuration.");
        }

        String requestUrl = constructUrl(latitude, longitude, radius, apiKey);

        try {
            HttpURLConnection conn = createConnection(requestUrl);

            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("Failed: HTTP error code : " + responseCode);
            }

            return parseResponse(conn);

        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid URL: " + requestUrl, e);
        }
    }

    /**
     * Constructs the URL for the Google Places API request.
     *
     * @param latitude The latitude of the location.
     * @param longitude The longitude of the location.
     * @param radius The search radius in meters.
     * @param apiKey The API key for authenticating with Google Places API.
     * @return The constructed URL as a string.
     */
    private String constructUrl(double latitude, double longitude, int radius, String apiKey) {
        return PLACES_API_URL + "?key=" + apiKey + "&location=" + latitude + "," + longitude + "&radius=" + radius + "&type=restaurant";
    }

    /**
     * Creates an HTTP connection to the given URL for a GET request.
     *
     * @param requestUrl The URL to connect to.
     * @return An HttpURLConnection object for the connection.
     * @throws IOException If there is an error opening the connection.
     */
    private HttpURLConnection createConnection(String requestUrl) throws IOException {
        URL url = new URL(requestUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        return conn;
    }

    /**
     * Parses the HTTP response from the Google Places API into a JSON object.
     *
     * @param conn The HttpURLConnection object from which to read the response.
     * @return A JSONObject containing the response data.
     * @throws IOException If there is an error reading the response.
     * @throws JSONException If there is an error parsing the JSON response.
     */
    private JSONObject parseResponse(HttpURLConnection conn) throws IOException, JSONException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return new JSONObject(response.toString());
        }
    }
}
