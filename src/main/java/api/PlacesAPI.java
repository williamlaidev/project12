package api;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * PlacesAPI is responsible for interacting with the Google Places API to fetch nearby restaurants.
 */
public class PlacesAPI {
    private static final String PLACES_API_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json"; // end point
    private static final String API_KEY = "AIzaSyCSDF8JZAe-cohjXiXtCnCdbAUUOPnjWd0"; // put your API key

    /**
     * Fetches nearby restaurants from Google Places API based on the provided latitude, longitude, and radius.
     *
     * @param latitude  the latitude of the location
     * @param longitude the longitude of the location
     * @param radius    the radius in meters within which to find restaurants
     * @return JSONObject containing the list of restaurants found
     * @throws Exception if there is an issue with the network or the API call fails
     */
    public JSONObject getNearbyRestaurants(double latitude, double longitude, int radius) throws Exception {
        URL url = new URL(PLACES_API_URL + "?key=" + API_KEY + "&location=" + latitude + "," + longitude + "&radius=" + radius + "&type=restaurant");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream()), "utf-8"));
        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = br.readLine()) != null) {
            response.append(responseLine.trim());
        }

        conn.disconnect();

        return new JSONObject(response.toString());
    }

    public String getPhotoUrl(String photoReference) {
        return "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=" + photoReference + "&key=" + API_KEY;
    }
}