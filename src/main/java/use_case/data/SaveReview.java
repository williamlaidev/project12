package use_case.data;

import entity.OperationResult;
import entity.Review;
import domain.ReviewRepository;

/**
 * Handles the use case for saving a review to the repository.
 * It uses the {@link ReviewRepository} to persist the review.
 */
public class SaveReview {

    private final ReviewRepository repository;

    /**
     * Creates an instance with the given repository.
     *
     * @param repository the {@link ReviewRepository} for saving reviews.
     */
    public SaveReview(ReviewRepository repository) {
        this.repository = repository;
    }

    /**
     * Saves the specified review to the repository.
     *
     * @param review the {@link Review} to be saved.
     * @return {@code true} if the save operation was successful, {@code false} otherwise.
     */
    public OperationResult execute(Review review) {
        return repository.save(review);
    }
}
