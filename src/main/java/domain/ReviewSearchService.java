package domain;

import entity.review.Review;
import java.util.List;

/**
 * The {@code ReviewSearchService} interface defines a method for retrieving the most relevant
 * reviews for a specified restaurant.
 */
public interface ReviewSearchService {

    /**
     * Fetches the most relevant reviews for the specified restaurant.
     * The method uses the restaurant's ID to retrieve and return a list of reviews deemed
     * most relevant. The maximum number of reviews returned is limited by the provided parameter.
     * If no reviews are found, an empty list is returned.
     *
     * @param restaurantId the unique identifier of the restaurant whose reviews are to be fetched
     * @param maxReviews   the maximum number of reviews to retrieve
     * @return a {@code List} of the most relevant {@code Review} objects; returns an empty list
     *         if no relevant reviews are found
     */
    List<Review> fetchRelevantReviews(String restaurantId, int maxReviews);
}
