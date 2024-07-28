package domain;

import entity.Review;
import java.util.List;

/**
 * The ReviewRetrievalService interface provides a method for retrieving the most relevant reviews
 * for a given restaurant.
 */
public interface ReviewRetrievalService {

    /**
     * Retrieves the most relevant reviews for a given restaurant.
     * This method fetches reviews based on the restaurant's ID and returns a list of reviews
     * that are considered most relevant. If no reviews are found, an empty list is returned.
     *
     * @param restaurantId the ID of the restaurant whose reviews are to be retrieved
     * @return a list of the most relevant reviews if present, otherwise an empty list
     */
    List<Review> getReviewsForRestaurant(String restaurantId);
}
