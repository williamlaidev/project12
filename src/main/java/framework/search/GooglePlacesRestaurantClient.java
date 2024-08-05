package framework.search;

import framework.config.EnvConfigService;
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

public class GooglePlacesRestaurantClient implements RestaurantSearchGateways {

    private static final String PLACES_API_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";
    private final EnvConfigService envConfigService;

    public GooglePlacesRestaurantClient(EnvConfigService envConfigService) {
        this.envConfigService = envConfigService;
    }

    @Override
    public JSONObject fetchNearbyRestaurants(double latitude, double longitude, int radius) throws IOException, JSONException {
        String apiKey = envConfigService.getGoogleMapsApiKey();
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("API key is missing. Please check your environment configuration.");
        }

        String requestUrl = constructUrl(latitude, longitude, radius, apiKey);

        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("Failed: HTTP error code : " + responseCode);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine;

                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                return new JSONObject(response.toString());
            }

        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid URL: " + requestUrl, e);
        }
    }

    private String constructUrl(double latitude, double longitude, int radius, String apiKey) {
        return PLACES_API_URL + "?key=" + apiKey + "&location=" + latitude + "," + longitude + "&radius=" + radius + "&type=restaurant";
    }
}
