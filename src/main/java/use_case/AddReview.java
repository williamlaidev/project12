package use_case;

import domain.ReviewRepository;
import entity.Review;

public class AddReview {

    private final ReviewRepository reviewRepository;

    public AddReview(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    /**
     * Adds a review to the repository.
     *
     * @param review the review to be added
     * @return the added review
     */
    public Review execute(Review review) {
        return reviewRepository.add(review);
    }
}
