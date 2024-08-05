package use_case.data;

import entity.Review;
import domain.ReviewRepository;

/**
 * Handles the use case for updating an existing review in the repository.
 * It uses the {@link ReviewRepository} to update the review.
 */
public class UpdateReview {

    private final ReviewRepository repository;

    /**
     * Creates an instance with the given repository.
     *
     * @param repository the {@link ReviewRepository} for updating reviews.
     */
    public UpdateReview(ReviewRepository repository) {
        this.repository = repository;
    }

    /**
     * Updates the specified review in the repository.
     *
     * @param review the {@link Review} to be updated.
     * @return {@code true} if the update operation was successful, {@code false} otherwise.
     */
    public boolean execute(Review review) {
        return repository.update(review);
    }
}
