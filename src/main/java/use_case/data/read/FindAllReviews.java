package use_case.data.read;

import domain.ReviewRepository;
import entity.review.Review;

import java.util.List;

public class FindAllReviews {

    private final ReviewRepository repository;

    public FindAllReviews(ReviewRepository repository) {
        this.repository = repository;
    }

    public List<Review> execute() {
        return repository.findAll();
    }
}
