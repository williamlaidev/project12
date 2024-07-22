package domain;

import entity.Review;

import java.util.List;

public interface ReviewRetrievalService {

    /**
     * Retrieves the most relevant reviews for a given restaurant.
     *
     * @param restaurantId the ID of the restaurant
     * @return a list of the most relevant reviews if present, otherwise an empty list
     */
    List<Review> getReviewsForRestaurant(String restaurantId);
}
