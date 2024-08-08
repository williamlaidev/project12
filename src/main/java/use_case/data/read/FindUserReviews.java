package use_case.data.read;

import domain.ReviewRepository;
import entity.review.Review;
import java.util.List;

/**
 * Use case for finding user reviews of a specific restaurant.
 * It uses the {@link ReviewRepository} to retrieve the reviews.
 */
public class FindUserReviews {

    private final ReviewRepository repository;

    /**
     * Creates an instance with the given repository.
     *
     * @param repository the {@link ReviewRepository} for finding user reviews.
     */
    public FindUserReviews(ReviewRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves user reviews for the specified restaurant ID.
     *
     * @param restaurantId the ID of the restaurant whose user reviews are to be retrieved.
     * @return a {@link List} of {@link Review}.
     */
    public List<Review> execute(String restaurantId) {
        return repository.findUserReviews(restaurantId);
    }
}
