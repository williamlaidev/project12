package entity;

import java.util.List;

public class RestaurantFactory {

    /**
     * Creates a new Restaurant instance with the specified details.
     *
     * @param restaurantId     the ID of the restaurant
     * @param name             the name of the restaurant
     * @param location         the location of the restaurant
     * @param address          the address of the restaurant
     * @param dishType         the type of dishes the restaurant serves
     * @param averageRating    the average rating of the restaurant
     * @param photoUrl         the URL of the restaurant's photo
     * @param userReviews      a list of user reviews of the restaurant
     * @param summarizedReview a summarized review of the restaurant
     * @return a new Restaurant object initialized with the given parameters
     */
    public static Restaurant createRestaurant(String restaurantId, String name, Location location, String address,
                                              DishType dishType, double averageRating, String photoUrl,
                                              List<Review> userReviews, Review summarizedReview) {
        return new Restaurant(restaurantId, name, location, address, dishType, averageRating, photoUrl, userReviews, summarizedReview);
    }
}
