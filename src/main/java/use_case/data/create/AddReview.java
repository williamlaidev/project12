package use_case.data.create;

import domain.ReviewRepository;
import entity.operation_result.OperationResult;
import entity.review.Review;

/**
 * Use case for adding a review.
 * It uses the {@link ReviewRepository} to perform the addition.
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
     * Adds the specified review.
     *
     * @param review the review to be added.
     * @return an {@link OperationResult} indicating the outcome of the operation.
     */
    public OperationResult execute(Review review) {
        return repository.add(review);
    }
}
