package use_case.data.update;

import entity.operation_result.OperationResult;
import entity.review.Review;
import domain.ReviewRepository;

/**
 * Use case for updating an existing review.
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
     * Updates the specified review.
     *
     * @param review the {@link Review} to be updated.
     * @return an {@link OperationResult} indicating the outcome of the operation.
     */
    public OperationResult execute(Review review) {
        return repository.update(review);
    }
}
