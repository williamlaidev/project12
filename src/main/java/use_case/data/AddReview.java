package use_case.data;

import entity.Review;
import domain.ReviewRepository;

/**
 * Handles the use case for adding a review.
 * It uses the {@link ReviewRepository} to save the review.
 */
public class AddReview {

    private final ReviewRepository repository;

    /**
     * Creates an instance with the given repository.
     *
     * @param repository the {@link ReviewRepository} for adding reviews.
     */
    public AddReview(ReviewRepository repository) {
        this.repository = repository;
    }

    /**
     * Adds the specified review to the repository.
     *
     * @param review the {@link Review} to add.
     */
    public void execute(Review review) {
        repository.save(review);
    }
}
