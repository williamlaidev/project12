package main;

import main.java.entity.DishType;
import main.java.entity.Location;
import main.java.entity.Restaurant;
import main.java.entity.RestaurantFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NearbyRestaurantFinder {
    private static final String GEOLOCATION_API_URL = "https://www.googleapis.com/geolocation/v1/geolocate?key=AIzaSyCSDF8JZAe-cohjXiXtCnCdbAUUOPnjWd0"; // put your API key after key=
    private static final String PLACES_API_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json"; // end point
    private static final String API_KEY = "AIzaSyCSDF8JZAe-cohjXiXtCnCdbAUUOPnjWd0"; // put your API key

    public static void main(String[] args) {
        try {
            // 1. Get current location
            JSONObject location = getCurrentLocation();
            double latitude = location.getJSONObject("location").getDouble("lat");
            double longitude = location.getJSONObject("location").getDouble("lng");
            Location currentLocation = new Location(latitude, longitude);
            System.out.println("Current location: " + currentLocation);

            // 2. Use current location to get nearby restaurants
            int radius = 500; // example radius in meters
            int maxResults = 5; // example maximum number of results
            List<Restaurant> restaurants = getNearbyRestaurants(currentLocation, radius, maxResults);

            // Print the restaurant objects
            for (Restaurant restaurant : restaurants) {
                System.out.println(restaurant);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getCurrentLocation() throws Exception {
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

    public static List<Restaurant> getNearbyRestaurants(Location currentLocation, int radius, int maxResults) throws IOException {
        List<Restaurant> restaurants = new ArrayList<>();
        try {
            URL url = new URL(PLACES_API_URL + "?key=" + API_KEY + "&location=" + currentLocation.getLatitude() + "," + currentLocation.getLongitude() + "&radius=" + radius + "&type=restaurant");
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

            try {
                JSONObject jsonObject = new JSONObject(response.toString());
                JSONArray places = jsonObject.getJSONArray("results");

                for (int i = 0; i < Math.min(places.length(), maxResults); i++) {
                    JSONObject place = places.getJSONObject(i); // Access the restaurant's information

                    String restaurantId = place.optString("place_id", ""); // Restaurant ID
                    String name = place.optString("name", ""); // Restaurant name
                    JSONArray types = place.optJSONArray("types"); // Restaurant types
                    List<String> cuisineType = new ArrayList<>(); // Store types in a list
                    if (types != null) {
                        for (int j = 0; j < types.length(); j++) {
                            cuisineType.add(types.getString(j));
                        }
                    }

                    double averageRating = place.optDouble("rating", 0.0); // Restaurant rating

                    JSONObject location = place.getJSONObject("geometry").getJSONObject("location");
                    Location placeLocation = new Location(location.getDouble("lat"), location.getDouble("lng")); // Restaurant location

                    String address = place.optString("vicinity", ""); // Restaurant address

                    String photoUri = "";
                    if (place.has("photos")) {
                        JSONArray photos = place.getJSONArray("photos");
                        if (photos.length() > 0) {
                            JSONObject firstPhoto = photos.getJSONObject(0);
                            photoUri = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=" + firstPhoto.getString("photo_reference") + "&key=" + API_KEY;
                        }
                    }

                    // create a restaurant object
                    Restaurant restaurant = RestaurantFactory.createRestaurant(
                            restaurantId,
                            name,
                            placeLocation,
                            address,
                            DishType.CHINESE, // You might want to determine how to set the DishType
                            averageRating,
                            photoUri,
                            "Summarized Review Placeholder");

                    restaurants.add(restaurant);
                }

            } catch (JSONException e) {
                System.out.println("Error: " + e.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurants;
    }
}
