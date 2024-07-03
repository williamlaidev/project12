package main.entity;

/**
 * Factory class for creating Restaurant instances.
 */
public class RestaurantFactory {

    /**
     * @param restaurantId     the unique restaurant identifier
     * @param name             the official name of the restaurant
     * @param cuisineType      the cuisine offered by the restaurant
     * @param averageRating    the average user rating of the restaurant
     * @param latitude         the geographical latitude of the restaurant
     * @param longitude        the geographical longitude of the restaurant
     * @param address          the physical address of the restaurant
     * @param photoUrl         the URL link to a photo of the restaurant
     * @param summarizedReview the AI-generated review summary of the restaurant
     * @return a new Restaurant object
     */
    public static Restaurant create(int restaurantId, String name, String cuisineType,
                                    double averageRating, double latitude, double longitude,
                                    String address, String photoUrl, String summarizedReview) {
        return new Restaurant(restaurantId, name, cuisineType, averageRating, latitude, longitude, address, photoUrl, summarizedReview);
    }
}
