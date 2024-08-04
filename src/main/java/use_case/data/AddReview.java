package use_case.data;

import entity.OperationResult;
import entity.Review;
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
