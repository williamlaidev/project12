package framework.search;

import framework.config.EnvConfigService;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Implementation of GeolocationService that interacts with the Google Geolocation API.
 */
public class GoogleGeolocationService implements GeolocationService {

    // Base URL for Google Geolocation API endpoint
    private static final String GEOLOCATION_API_URL = "https://www.googleapis.com/geolocation/v1/geolocate";
    private final EnvConfigService envConfigService;

    /**
     * Constructs the GoogleGeolocationService with the provided EnvConfigService.
     *
     * @param envConfigService an instance of EnvConfigService for loading configuration
     */
    public GoogleGeolocationService(EnvConfigService envConfigService) {
        this.envConfigService = envConfigService;
    }

    /**
     * Gets the current geographical location using the Google Geolocation API.
     *
     * @return JSONObject containing the latitude and longitude of the current location
     * @throws Exception if there is a network issue or the API call fails
     */
    @Override
    public JSONObject getCurrentLocation() throws Exception {
        String apiKey = envConfigService.getGoogleMapsApiKey();
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("API key is missing. Please check your environment configuration.");
        }


        URL url = new URL(GEOLOCATION_API_URL + "?key=" + apiKey);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

        // Send an empty JSON object as request body
        String jsonInputString = "{}";

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // Check HTTP response code
        int responseCode = conn.getResponseCode();

        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : " + responseCode);
        }

        // Read the response from the input stream
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder response = new StringBuilder();
        String responseLine;

        while ((responseLine = in.readLine()) != null) {
            response.append(responseLine.trim());
        }

        in.close();
        conn.disconnect();


        // Convert the response to a JSONObject and return it
        return new JSONObject(response.toString());
    }
}
