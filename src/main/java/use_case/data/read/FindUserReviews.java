package use_case.data.read;

import domain.ReviewRepository;
import entity.review.Review;

import java.util.List;

public class FindUserReviews {

    private final ReviewRepository repository;

    public FindUserReviews(ReviewRepository repository) {
        this.repository = repository;
    }

    public List<Review> execute(String restaurantId) {
        return repository.findUserReviews(restaurantId);
    }
}
