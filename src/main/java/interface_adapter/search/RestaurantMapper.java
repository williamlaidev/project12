package interface_adapter.search;

import entity.DishType;
import entity.Location;
import entity.Restaurant;
import entity.RestaurantFactory;
import framework.search.GooglePlacesRestaurantSearchService;
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

        // Check if the dishTypeFilter is null or if any of the place's types match the dishTypeFilter
        if (dishTypeFilter == null) {
            // No filter provided, include all restaurants
            isDishTypeMatch = true;
        } else if (placeTypes != null) {
            for (int i = 0; i < placeTypes.length(); i++) {
                String dishTypeString = placeTypes.getString(i);
                DishType dishType = DishType.fromDishTypeString(dishTypeString);
                if (dishType != null && dishType.equals(dishTypeFilter)) {
                    isDishTypeMatch = true;
                    break;
                }
            }
        }

        // If a matching dish type is found or no filter is applied, extract additional information and create a Restaurant object
        if (isDishTypeMatch) {
            System.out.println("Restaurant '" + restaurantName + "' matches the dish type filter: " + (dishTypeFilter != null ? dishTypeFilter : "all"));

            double averageRating = placeJson.optDouble("rating", 0.0);
            JSONObject locationJson = placeJson.getJSONObject("geometry").getJSONObject("location");
            Location restaurantLocation = new Location(locationJson.getDouble("lat"), locationJson.getDouble("lng"));
            String restaurantAddress = placeJson.optString("vicinity", "");

            String photoUrl = "";
            if (placeJson.has("photos")) {
                JSONArray photosArray = placeJson.getJSONArray("photos");
                if (photosArray.length() > 0) {
                    JSONObject firstPhoto = photosArray.getJSONObject(0);
                    photoUrl = placesService.buildPhotoUrl(firstPhoto.getString("photo_reference"));
                }
            }


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
        }

        return null;
    }
}
