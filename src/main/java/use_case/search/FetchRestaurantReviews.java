package use_case.search;

import domain.ReviewSearchService;
import entity.review.Review;

import java.util.List;

/**
 * Use case for retrieving reviews for a specific restaurant.
 * This class uses a {@link ReviewSearchService} to fetch reviews associated with a given restaurant ID.
 */
public class FetchRestaurantReviews {
    private final ReviewSearchService reviewRetrievalService;

    /**
     * Constructs a new {@code FetchRestaurantReviews} use case with the specified {@link ReviewSearchService}.
     *
     * @param reviewRetrievalService the service used to retrieve reviews
     */
    public FetchRestaurantReviews(ReviewSearchService reviewRetrievalService) {
        this.reviewRetrievalService = reviewRetrievalService;
    }

    /**
     * Executes the use case to retrieve reviews for the specified restaurant.
     *
     * @param restaurantId the ID of the restaurant whose reviews are to be retrieved
     * @param maxReviews   the maximum number of reviews to retrieve
     * @return a list of {@link Review} objects associated with the specified restaurant ID
     */
    public List<Review> execute(String restaurantId, int maxReviews) {
        return reviewRetrievalService.fetchRelevantReviews(restaurantId, maxReviews);
    }
}
