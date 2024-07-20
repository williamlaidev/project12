package interface_adapter;

import entity.DishType;
import entity.Location;
import entity.Restaurant;
import entity.RestaurantFactory;
import api.GeolocationAPI;
import api.PlacesAPI;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.List;
import java.util.ArrayList;

public class PlacesAPIGateway implements IPlacesAPIGateway {
    private final GeolocationAPI geolocationAPI;
    private final PlacesAPI placesAPI;

    public PlacesAPIGateway() {
        this.geolocationAPI = new GeolocationAPI();
        this.placesAPI = new PlacesAPI();
    }

    @Override
    public Location getCurrentLocation() throws Exception {
        JSONObject locationJson = geolocationAPI.getCurrentLocation();
        double latitude = locationJson.getJSONObject("location").getDouble("lat");
        double longitude = locationJson.getJSONObject("location").getDouble("lng");
        return new Location(latitude, longitude);
    }

    @Override
    public List<Restaurant> getNearbyRestaurants(Location location, int radius, int maxResults) throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        JSONObject response = placesAPI.getNearbyRestaurants(location.getLatitude(), location.getLongitude(), radius);
        parseRestaurantsFromResponse(restaurants, response, maxResults, null);
        return restaurants;
    }

    @Override
    public List<Restaurant> getNearbyRestaurantsByDishType(Location location, String dishType, int radius, int maxResults) throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        JSONObject response = placesAPI.getNearbyRestaurants(location.getLatitude(), location.getLongitude(), radius);
        parseRestaurantsFromResponse(restaurants, response, maxResults, dishType);
        return restaurants;
    }

    private void parseRestaurantsFromResponse(List<Restaurant> restaurants, JSONObject response, int maxResults, String dishTypeFilter) throws Exception {
        JSONArray places = response.getJSONArray("results");

        for (int i = 0; i < Math.min(places.length(), maxResults); i++) {
            JSONObject place = places.getJSONObject(i);

            String restaurantId = place.optString("place_id", "");
            String name = place.optString("name", "");
            JSONArray types = place.optJSONArray("types");
            DishType dishType = null;
            if (types != null) {
                for (int j = 0; j < types.length(); j++) {
                    dishType = DishType.fromApiType(types.getString(j));
                    if (dishType != null && (dishTypeFilter == null || dishType.toString().equalsIgnoreCase(dishTypeFilter))) {
                        break;
                    }
                }
            }

            if (dishTypeFilter == null || (dishType != null && dishType.toString().equalsIgnoreCase(dishTypeFilter))) {
                double averageRating = place.optDouble("rating", 0.0);
                JSONObject location = place.getJSONObject("geometry").getJSONObject("location");
                Location placeLocation = new Location(location.getDouble("lat"), location.getDouble("lng"));
                String address = place.optString("vicinity", "");

                String photoUri = "";
                if (place.has("photos")) {
                    JSONArray photos = place.getJSONArray("photos");
                    if (photos.length() > 0) {
                        JSONObject firstPhoto = photos.getJSONObject(0);
                        photoUri = placesAPI.getPhotoUrl(firstPhoto.getString("photo_reference"));
                    }
                }

                Restaurant restaurant = RestaurantFactory.createRestaurant(
                        restaurantId,
                        name,
                        placeLocation,
                        address,
                        dishType,
                        averageRating,
                        photoUri,
                        "Summarized Review Placeholder");

                restaurants.add(restaurant);
            }
        }
    }
}
