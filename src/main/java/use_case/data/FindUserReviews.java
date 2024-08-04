package use_case.data;

import domain.ReviewRepository;
import entity.Review;

import java.util.List;
import java.util.Optional;

public class FindUserReviews {

    private final ReviewRepository repository;

    public FindUserReviews(ReviewRepository repository) {
        this.repository = repository;
    }

    public List<Review> execute(String restaurantId) {
        return repository.findUserReviews(restaurantId);
    }
}
