package interface_adapter;

import entity.*;
import framework.GooglePlacesRestaurantSearchService;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Maps JSON objects from Google Places API responses to Restaurant entities.
 */
public class RestaurantMapper {

    private final GooglePlacesRestaurantSearchService placesService;

    /**
     * Constructs a RestaurantMapper with the specified GooglePlacesRestaurantSearchService.
     *
     * @param placesService The service used to interact with the Google Places API.
     */
    public RestaurantMapper(GooglePlacesRestaurantSearchService placesService) {
        this.placesService = placesService;
    }

    /**
     * Maps a JSON object representing a place to a Restaurant entity, if it matches the dish type filter.
     *
     * @param placeJson The JSON object containing place information from the Google Places API.
     * @param dishTypeFilter The dish type filter to match against the restaurant's type.
     * @return A Restaurant object if the dish type matches the filter; otherwise, returns null.
     */
    public Restaurant mapToRestaurant(JSONObject placeJson, DishType dishTypeFilter) {

        // Extract basic information from the JSON object
        String placeId = placeJson.optString("place_id", "");
        String restaurantName = placeJson.optString("name", "");
        JSONArray placeTypes = placeJson.optJSONArray("types");

        boolean isDishTypeMatch = false;

        // Check if any of the place's types match the dishTypeFilter or "restaurant" if filter is null
        if (placeTypes != null) {
            for (int i = 0; i < placeTypes.length(); i++) {
                String apiType = placeTypes.getString(i);
                DishType dishType = DishType.fromApiType(apiType);
                if (dishType != null && (dishTypeFilter == null || dishType.equals(dishTypeFilter))) {
                    isDishTypeMatch = true;
                    break;
                }
            }
        }

        // If a matching dish type is found or no filter is applied (default to "restaurant"), extract additional information and create a Restaurant object
        if (isDishTypeMatch || dishTypeFilter == null) {
            System.out.println("Restaurant '" + restaurantName + "' matches the dish type filter: " + (dishTypeFilter != null ? dishTypeFilter : "restaurant"));

            double averageRating = placeJson.optDouble("rating", 0.0);
            JSONObject locationJson = placeJson.getJSONObject("geometry").getJSONObject("location");
            Location restaurantLocation = new Location(locationJson.getDouble("lat"), locationJson.getDouble("lng"));
            String restaurantAddress = placeJson.optString("vicinity", "");

            String photoUrl = "";
            if (placeJson.has("photos")) {
                JSONArray photosArray = placeJson.getJSONArray("photos");
                if (!photosArray.isEmpty()) {
                    System.out.println("Generating photo URL for restaurant: " + restaurantName);
                    JSONObject firstPhoto = photosArray.getJSONObject(0);
                    photoUrl = placesService.buildPhotoUrl(firstPhoto.getString("photo_reference"));
                }
            }

            System.out.println("Restaurant information retrieved for '" + restaurantName + "' (ID: " + placeId + ").");

            // Create and return a Restaurant object
            return RestaurantFactory.createRestaurant(
                    placeId,
                    restaurantName,
                    restaurantLocation,
                    restaurantAddress,
                    dishTypeFilter, // Use the targetDishType if found
                    averageRating,
                    photoUrl,
                    null,
                    null
            );
        } else {
            System.out.println("Restaurant '" + restaurantName + "' does not match the dish type filter: " + dishTypeFilter);
        }

        return null;
    }
}
