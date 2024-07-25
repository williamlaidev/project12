package use_case.Data;

import domain.ReviewRepository;
import entity.Review;

import java.util.Optional;

public class GetReviewByRestaurant {

    private final ReviewRepository reviewRepository;

    public GetReviewByRestaurant(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    /**
     * Retrieves a review for a given restaurant.
     *
     * @param restaurantId the ID of the restaurant whose review is to be retrieved
     * @return an Optional containing the review if present, otherwise an empty Optional
     */
    public Optional<Review> execute(String restaurantId) {
        return reviewRepository.getReviewByRestaurant(restaurantId);
    }
}
