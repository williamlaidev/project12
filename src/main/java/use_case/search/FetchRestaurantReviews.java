package use_case.search;

import domain.ReviewSearchService;
import entity.review.Review;
import java.util.List;

/**
 * Use case for fetching reviews of a specific restaurant.
 * It utilizes a {@link ReviewSearchService} to retrieve reviews by restaurant ID.
 */
public class FetchRestaurantReviews {

    private final ReviewSearchService reviewRetrievalService;

    /**
     * Constructs an instance with the given service.
     *
     * @param reviewRetrievalService the service used to fetch reviews.
     */
    public FetchRestaurantReviews(ReviewSearchService reviewRetrievalService) {
        this.reviewRetrievalService = reviewRetrievalService;
    }

    /**
     * Retrieves reviews for the specified restaurant.
     *
     * @param restaurantId the ID of the restaurant.
     * @param maxReviews   the maximum number of reviews to retrieve.
     * @return a list of {@link Review} for the restaurant.
     */
    public List<Review> execute(String restaurantId, int maxReviews) {
        return reviewRetrievalService.fetchRelevantReviews(restaurantId, maxReviews);
    }
}
