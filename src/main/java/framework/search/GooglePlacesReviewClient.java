package framework.search;

import framework.EnvConfigService;
import interface_adapter.search.ReviewSearchGateways;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Client for fetching restaurant reviews from Google Places API.
 * Implements the ReviewSearchGateways interface.
 */
public class GooglePlacesReviewClient implements ReviewSearchGateways {
    private static final String PLACES_API_URL = "https://maps.googleapis.com/maps/api/place/details/json";
    private final EnvConfigService envConfigService;

    /**
     * Constructs a new GooglePlacesReviewClient.
     *
     * @param envConfigService The service for accessing environment configuration.
     */
    public GooglePlacesReviewClient(EnvConfigService envConfigService) {
        this.envConfigService = envConfigService;
    }

    /**
     * Fetches relevant reviews for a given restaurant ID.
     * Constructs a request URL using the restaurant ID and API key, performs an HTTP GET request to the
     * Google Places API, and processes the response to extract reviews.
     *
     * @param restaurantId The ID of the restaurant for which to fetch reviews.
     * @return A list of JSONObjects representing the reviews.
     * @throws RuntimeException If there is an error fetching or parsing the reviews.
     */
    @Override
    public List<JSONObject> fetchRelevantReviews(String restaurantId) {
        List<JSONObject> reviews = new ArrayList<>();
        try {
            String apiKey = envConfigService.getGoogleMapsApiKey();
            if (apiKey == null || apiKey.isEmpty()) {
                throw new IllegalArgumentException("API key is missing. Please check your environment configuration.");
            }

            String requestUrl = buildRequestUrl(apiKey, restaurantId);
            HttpURLConnection conn = createConnection(requestUrl);

            // Check the response code
            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed: HTTP error code: " + responseCode);
            }

            // Read and process the response
            String jsonResponse = readResponse(conn);
            JSONArray reviewsArray = extractReviewsArray(jsonResponse);

            if (reviewsArray != null) {
                for (int i = 0; i < reviewsArray.length(); i++) {
                    reviews.add(reviewsArray.getJSONObject(i));
                }
            }
        } catch (JSONException e) {
            throw new RuntimeException("Failed to parse JSON response: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching reviews: " + e.getMessage(), e);
        }
        return reviews;
    }

    /**
     * Constructs the URL for the Google Places API request to fetch reviews.
     *
     * @param apiKey The API key for authenticating with Google Places API.
     * @param restaurantId The ID of the restaurant for which to fetch reviews.
     * @return The constructed URL as a string.
     */
    private String buildRequestUrl(String apiKey, String restaurantId) {
        return PLACES_API_URL + "?key=" + apiKey +
                "&place_id=" + restaurantId +
                "&fields=reviews";
    }

    /**
     * Creates an HTTP connection to the given URL for a GET request.
     *
     * @param requestUrl The URL to connect to.
     * @return An HttpURLConnection object for the connection.
     * @throws Exception If there is an error opening the connection.
     */
    private HttpURLConnection createConnection(String requestUrl) throws Exception {
        URL url = new URL(requestUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        return conn;
    }

    /**
     * Reads the response from the HTTP connection as a string.
     *
     * @param conn The HttpURLConnection object from which to read the response.
     * @return The response content as a string.
     * @throws Exception If there is an error reading the response.
     */
    private String readResponse(HttpURLConnection conn) throws Exception {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();
        }
    }

    /**
     * Extracts the reviews array from the JSON response.
     *
     * @param jsonResponse The response content as a JSON string.
     * @return A JSONArray containing the reviews, or null if no reviews are found.
     * @throws JSONException If there is an error parsing the JSON response.
     */
    private JSONArray extractReviewsArray(String jsonResponse) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONObject result = jsonObject.optJSONObject("result");
        if (result == null) {
            throw new RuntimeException("Invalid response format. 'result' field missing.");
        }
        return result.optJSONArray("reviews");
    }
}
