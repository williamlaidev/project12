package use_case.data;

import domain.ReviewRepository;
import entity.Review;

import java.util.List;
import java.util.Optional;

/**
 * Handles the use case for finding user reviews of a specific restaurant.
 * It uses the {@link ReviewRepository} to retrieve user reviews.
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
     * Retrieves user reviews associated with the specified restaurant ID.
     *
     * @param restaurantId the ID of the restaurant whose user reviews are to be retrieved.
     * @return an {@link Optional} containing a list of {@link Review} if reviews are found,
     *         or {@link Optional#empty()} if no user reviews are found.
     */
    public Optional<List<Review>> execute(String restaurantId) {
        return repository.findUserReviews(restaurantId);
    }
}
