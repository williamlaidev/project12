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

public class GooglePlacesReviewClient implements ReviewSearchGateways {
    private static final String PLACES_API_URL = "https://maps.googleapis.com/maps/api/place/details/json";
    private final EnvConfigService envConfigService;

    public GooglePlacesReviewClient(EnvConfigService envConfigService) {
        this.envConfigService = envConfigService;
    }

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

    private String buildRequestUrl(String apiKey, String restaurantId) {
        return PLACES_API_URL + "?key=" + apiKey +
                "&place_id=" + restaurantId +
                "&fields=reviews";
    }

    private HttpURLConnection createConnection(String requestUrl) throws Exception {
        URL url = new URL(requestUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        return conn;
    }

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

    private JSONArray extractReviewsArray(String jsonResponse) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONObject result = jsonObject.optJSONObject("result");
        if (result == null) {
            throw new RuntimeException("Invalid response format. 'result' field missing.");
        }
        return result.optJSONArray("reviews");
    }
}
