package use_case.search;

import domain.ReviewRetrievalService;
import entity.review.Review;

import java.util.List;

/**
 * Use case for retrieving reviews for a specific restaurant.
 * This class uses a {@link ReviewRetrievalService} to fetch reviews associated with a given restaurant ID.
 */
public class FetchRestaurantReviews {
    private final ReviewRetrievalService reviewRetrievalService;

    /**
     * Constructs a new {@code FetchRestaurantReviews} use case with the specified {@link ReviewRetrievalService}.
     *
     * @param reviewRetrievalService the service used to retrieve reviews
     */
    public FetchRestaurantReviews(ReviewRetrievalService reviewRetrievalService) {
        this.reviewRetrievalService = reviewRetrievalService;
    }

    /**
     * Executes the use case to retrieve reviews for the specified restaurant.
     *
     * @param restaurantId the ID of the restaurant whose reviews are to be retrieved
     * @return a list of {@link Review} objects associated with the specified restaurant ID
     */
    public List<Review> execute(String restaurantId) {
        return reviewRetrievalService.fetchRelevantReviews(restaurantId);
    }
}
