package framework.SearchReviews;

import framework.APIKeyConfig.EnvConfigService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class to interact with Google Places API to fetch reviews for a specific place.
 */
public class GooglePlacesReviewsService {

    private static final String PLACES_DETAILS_API_URL = "https://maps.googleapis.com/maps/api/place/details/json";
    private final EnvConfigService envConfigService;

    /**
     * Constructs the GooglePlacesReviewsService with the provided EnvConfigService.
     *
     * @param envConfigService an instance of EnvConfigService for loading configuration
     */
    public GooglePlacesReviewsService(EnvConfigService envConfigService) {
        this.envConfigService = envConfigService;
    }

    /**
     * Fetches reviews for a specific place from the Google Places API based on the provided place ID.
     *
     * @param restaurantId the ID of the place for which to fetch reviews
     * @param language     optional language parameter for reviews
     * @param reviewsSort  optional sorting method for reviews
     * @return List of review JSONObjects
     * @throws Exception if there is an issue with the network or the API call fails
     */
    public List<JSONObject> fetchReviews(String restaurantId, String language, String reviewsSort) throws Exception {
        String apiKey = envConfigService.getGoogleMapsApiKey();
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("API key is missing. Please check your environment configuration.");
        }

        // Construct the URL with query parameters
        StringBuilder requestUrl = new StringBuilder(PLACES_DETAILS_API_URL);
        requestUrl.append("?key=").append(apiKey)
                .append("&place_id=").append(restaurantId)
                .append("&fields=reviews");

        if (language != null && !language.isEmpty()) {
            requestUrl.append("&language=").append(language);
        }
        if (reviewsSort != null && !reviewsSort.isEmpty()) {
            requestUrl.append("&reviews_sort=").append(reviewsSort);
        }

        URL url = new URL(requestUrl.toString());

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

        // Parse the response to extract reviews
        JSONObject jsonResponse = new JSONObject(response.toString());
        JSONObject result = jsonResponse.optJSONObject("result");
        if (result == null) {
            throw new RuntimeException("Invalid response format. 'result' field missing.");
        }
        JSONArray reviewsArray = result.optJSONArray("reviews");

        List<JSONObject> reviews = new ArrayList<>();
        if (reviewsArray != null) {
            for (int i = 0; i < reviewsArray.length(); i++) {
                reviews.add(reviewsArray.getJSONObject(i));
            }
        }

        return reviews;
    }
}
