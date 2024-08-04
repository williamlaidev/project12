package use_case.data;

import domain.ReviewRepository;
import entity.Review;

import java.util.List;
import java.util.Optional;

public class FindAllReviews {

    private final ReviewRepository repository;

    public FindAllReviews(ReviewRepository repository) {
        this.repository = repository;
    }

    public List<Review> execute() {
        return repository.findAll();
    }
}
