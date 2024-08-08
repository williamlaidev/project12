package domain;

import entity.review.Review;
import java.util.List;

/**
 * Service for fetching relevant reviews for a restaurant.
 */
public interface ReviewSearchService {

    /**
     * Retrieves a list of the most relevant reviews for the specified restaurant.
     *
     * @param restaurantId the ID of the restaurant
     * @param maxReviews   the maximum number of reviews to return
     * @return a list of relevant reviews, or an empty list if none are found
     */
    List<Review> fetchRelevantReviews(String restaurantId, int maxReviews);
}
