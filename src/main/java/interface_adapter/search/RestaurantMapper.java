package interface_adapter.search;

import entity.DishType;
import entity.Location;
import entity.Restaurant;
import entity.RestaurantFactory;
import framework.search.GooglePlacesRestaurantSearchService;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Maps JSON data from the Google Places API to {@link Restaurant} objects.
 * Utilizes a {@link GooglePlacesRestaurantSearchService} to interact with the Google Places API.
 */
public class RestaurantMapper {

    private final GooglePlacesRestaurantSearchService placesService;

    /**
     * Constructs a {@code RestaurantMapper} with the provided Google Places search service.
     *
     * @param placesService the {@link GooglePlacesRestaurantSearchService} used to fetch restaurant data
     */
    public RestaurantMapper(GooglePlacesRestaurantSearchService placesService) {
        this.placesService = placesService;
    }

    /**
     * Maps a JSON object representing a place to a {@link Restaurant} object.
     * Applies a filter based on {@link DishType} to determine if the restaurant matches the specified dish type.
     *
     * @param placeJson      the JSON object containing the place details from the Google Places API
     * @param dishTypeFilter the {@link DishType} filter to apply; {@code null} if no filtering is required
     * @return a {@link Restaurant} object if the place matches the dish type filter; {@code null} otherwise
     */
    public Restaurant mapToRestaurant(JSONObject placeJson, DishType dishTypeFilter) {
        // Extract basic information from the JSON object
        String placeId = placeJson.optString("place_id", "");
        String restaurantName = placeJson.optString("name", "");
        JSONArray placeTypes = placeJson.optJSONArray("types");

        double latitude = placeJson.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
        double longitude = placeJson.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
        String address = placeJson.optString("vicinity", "");
        double averageRating = placeJson.optDouble("rating", 0.0);

        // Print restaurant details for debugging
        printRestaurantDetails(placeId, restaurantName, latitude, longitude, address, placeTypes, averageRating);

        // Determine the matched dish type from place types
        DishType matchedDishType = null;
        if (placeTypes != null) {
            for (int i = 0; i < placeTypes.length(); i++) {
                String type = placeTypes.getString(i);
                for (DishType dishType : DishType.values()) {
                    for (String apiType : dishType.getApiTypes()) {
                        if (type.equals(apiType)) {
                            matchedDishType = dishType;
                            break;
                        }
                    }
                    if (matchedDishType != null) {
                        break;
                    }
                }
                if (matchedDishType != null) {
                    break;
                }
            }
        }

        boolean isDishTypeMatch = (dishTypeFilter == null) || (matchedDishType != null && matchedDishType.equals(dishTypeFilter));
        if (isDishTypeMatch) {
            System.out.println("Restaurant '" + restaurantName + "' matches the dish type filter: " + (dishTypeFilter != null ? dishTypeFilter : "all"));

            String photoUrl = "";
            if (placeJson.has("photos")) {
                JSONArray photosArray = placeJson.getJSONArray("photos");
                if (!photosArray.isEmpty()) {
                    JSONObject firstPhoto = photosArray.getJSONObject(0);
                    photoUrl = placesService.buildPhotoUrl(firstPhoto.getString("photo_reference"));
                }
            }

            return RestaurantFactory.createRestaurantWithoutReviews(
                    placeId,
                    restaurantName,
                    new Location(latitude, longitude),
                    address,
                    matchedDishType,
                    averageRating,
                    photoUrl
            );
        } else {
            System.out.println("Restaurant '" + restaurantName + "' does not match the dish type filter: " + dishTypeFilter);
        }

        return null;
    }

    /**
     * Prints the details of a restaurant for debugging purposes.
     *
     * @param placeId          the unique identifier of the place
     * @param restaurantName   the name of the restaurant
     * @param latitude         the latitude of the restaurant's location
     * @param longitude        the longitude of the restaurant's location
     * @param address          the address of the restaurant
     * @param placeTypes       the types of the place as reported by the Google Places API
     * @param averageRating    the average rating of the restaurant
     */
    private void printRestaurantDetails(String placeId, String restaurantName, double latitude, double longitude, String address, JSONArray placeTypes, double averageRating) {
        System.out.println("Restaurant ID: " + placeId);
        System.out.println("Restaurant Name: " + restaurantName);
        System.out.println("Latitude: " + latitude);
        System.out.println("Longitude: " + longitude);
        System.out.println("Address: " + address);

        if (placeTypes != null) {
            System.out.print("Types: ");
            for (int i = 0; i < placeTypes.length(); i++) {
                System.out.print(placeTypes.getString(i));
                if (i < placeTypes.length() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }

        System.out.println("Average Rating: " + averageRating);
    }
}
