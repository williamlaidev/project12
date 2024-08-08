package use_case.data.create;

import entity.operation_result.OperationResult;
import entity.review.Review;
import domain.ReviewRepository;

public class AddReview {

    private final ReviewRepository repository;

    public AddReview(ReviewRepository repository) {
        this.repository = repository;
    }

    public OperationResult execute(Review review) {
        return repository.add(review);
    }
}
