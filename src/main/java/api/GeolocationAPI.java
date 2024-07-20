package api;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * GeolocationAPI class interacts with the Google Geolocation API to get the current location.
 */
public class GeolocationAPI {
    private static final String GEOLOCATION_API_URL = "https://www.googleapis.com/geolocation/v1/geolocate?key=AIzaSyCSDF8JZAe-cohjXiXtCnCdbAUUOPnjWd0";

    /**
     * Gets the current geographical location using the Google Geolocation API.
     *
     * @return JSONObject containing the latitude and longitude of the current location.
     * @throws Exception if there is a network issue or the API call fails.
     */
    public JSONObject getCurrentLocation() throws Exception {
        URL url = new URL(GEOLOCATION_API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

        String jsonInputString = "{}";

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        StringBuilder response = new StringBuilder();
        String responseLine;

        while ((responseLine = in.readLine()) != null) {
            response.append(responseLine.trim());
        }

        in.close();
        conn.disconnect();

        return new JSONObject(response.toString());
    }
}
