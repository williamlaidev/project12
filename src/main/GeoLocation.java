package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class GeoLocation {

    private static final String API_KEY = "AIzaSyA6Xghq85SJSUHNSyxND9vkXrR6tDE9nXM";
    private static final String API_URL = "https://www.googleapis.com/geolocation/v1/geolocate?key=" + API_KEY;

    public static void main(String[] args) {
        try {
            String response = getLocationData();
            parseGeolocationData(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getLocationData() throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        String jsonInputString = "{}";

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        StringBuilder response = new StringBuilder();
        String responseLine;

        while ((responseLine = in.readLine()) != null) {
            response.append(responseLine.trim());
        }

        in.close();
        return response.toString();
    }

    private static void parseGeolocationData(String response) {
        JSONObject jsonObj = new JSONObject(response);
        JSONObject location = jsonObj.getJSONObject("location");
        double latitude = location.getDouble("lat");
        double longitude = location.getDouble("lng");

        System.out.println("Latitude: " + latitude);
        System.out.println("Longitude: " + longitude);
    }
}