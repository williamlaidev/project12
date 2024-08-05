package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory class to create instances of Restaurant with various configurations.
 */
public class RestaurantFactory {

    /**
     * Creates a Restaurant instance without any reviews.
     *
     * @param restaurantId  the unique identifier of the restaurant
     * @param name          the name of the restaurant
     * @param location      the geographical location of the restaurant
     * @param address       the physical address of the restaurant
     * @param dishType      the primary type of dishes served by the restaurant
     * @param averageRating the average rating of the restaurant (e.g., from other sources or initial value)
     * @param photoUrl      the URL of the restaurant's photo (optional, can be null)
     * @return              a Restaurant object with the specified attributes and no reviews
     */
    public static Restaurant createRestaurantWithoutReviews(
            String restaurantId,
            String name,
            Location location,
            String address,
            DishType dishType,
            double averageRating,
            String photoUrl) {
        return new Restaurant(restaurantId, name, location, address, dishType, averageRating, photoUrl, new ArrayList<>(), null);
    }

    /**
     * Creates a Restaurant instance with user reviews and no summarized review.
     *
     * @param restaurantId  the unique identifier of the restaurant
     * @param name          the name of the restaurant
     * @param location      the geographical location of the restaurant
     * @param address       the physical address of the restaurant
     * @param dishType      the primary type of dishes served by the restaurant
     * @param averageRating the average rating of the restaurant (e.g., from other sources or initial value)
     * @param photoUrl      the URL of the restaurant's photo (optional, can be null)
     * @param userReviews   the list of user reviews for the restaurant
     * @return              a Restaurant object with the specified attributes and user reviews
     */
    public static Restaurant createRestaurantWithUserReviews(
            String restaurantId,
            String name,
            Location location,
            String address,
            DishType dishType,
            double averageRating,
            String photoUrl,
            List<Review> userReviews) {
        return new Restaurant(restaurantId, name, location, address, dishType, averageRating, photoUrl, userReviews, null);
    }

    /**
     * Creates a Restaurant instance with both user reviews and a summarized review.
     *
     * @param restaurantId      the unique identifier of the restaurant
     * @param name              the name of the restaurant
     * @param location          the geographical location of the restaurant
     * @param address           the physical address of the restaurant
     * @param dishType          the primary type of dishes served by the restaurant
     * @param averageRating     the average rating of the restaurant (e.g., from other sources or initial value)
     * @param photoUrl          the URL of the restaurant's photo (optional, can be null)
     * @param userReviews       the list of user reviews for the restaurant
     * @param summarizedReview  the summarized review of the restaurant (optional, can be null)
     * @return                  a Restaurant object with the specified attributes, user reviews, and a summarized review
     */
    public static Restaurant createRestaurantWithUserReviewsAndSummarizedReview(
            String restaurantId,
            String name,
            Location location,
            String address,
            DishType dishType,
            double averageRating,
            String photoUrl,
            List<Review> userReviews,
            Review summarizedReview) {
        return new Restaurant(restaurantId, name, location, address, dishType, averageRating, photoUrl, userReviews, summarizedReview);
    }
}
