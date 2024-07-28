package interface_adapter.search;

import entity.DishType;
import entity.Location;
import entity.Restaurant;
import entity.RestaurantFactory;
import framework.search.GooglePlacesRestaurantSearchService;
import org.json.JSONArray;
import org.json.JSONObject;

public class RestaurantMapper {

    private final GooglePlacesRestaurantSearchService placesService;

    public RestaurantMapper(GooglePlacesRestaurantSearchService placesService) {
        this.placesService = placesService;
    }

    public Restaurant mapToRestaurant(JSONObject placeJson, DishType dishTypeFilter) {

        // Extract basic information from the JSON object
        String placeId = placeJson.optString("place_id", "");
        String restaurantName = placeJson.optString("name", "");
        JSONArray placeTypes = placeJson.optJSONArray("types");

        double latitude = placeJson.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
        double longitude = placeJson.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
        String address = placeJson.optString("vicinity", "");
        double averageRating = placeJson.optDouble("rating", 0.0);

        // Print details
        printRestaurantDetails(placeId, restaurantName, latitude, longitude, address, placeTypes, averageRating);

        // Continue with dish type matching
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
                if (photosArray.length() > 0) {
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
