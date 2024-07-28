package use_case.data;

import domain.ReviewRepository;
import entity.Review;

public class UpdateReview {

    private final ReviewRepository reviewRepository;

    public UpdateReview(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    /**
     * Updates an existing review in the repository.
     *
     * @param review the review with updated information
     * @return true if the update was successful, otherwise false
     */
    public boolean execute(Review review) {
        return reviewRepository.update(review);
    }
}
