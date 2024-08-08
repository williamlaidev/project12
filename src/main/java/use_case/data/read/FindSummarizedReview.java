package use_case.data.read;

import domain.ReviewRepository;
import entity.review.Review;

import java.util.Optional;

/**
 * Handles the use case for finding the summarized review of a specific restaurant.
 * It uses the {@link ReviewRepository} to retrieve the summarized review.
 */
public class FindSummarizedReview {

    private final ReviewRepository repository;

    /**
     * Creates an instance with the given repository.
     *
     * @param repository the {@link ReviewRepository} for finding the summarized review.
     */
    public FindSummarizedReview(ReviewRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves the summarized review associated with the specified restaurant ID.
     *
     * @param restaurantId the ID of the restaurant whose summarized review is to be retrieved.
     * @return an {@link Optional} containing the {@link Review} if found,
     *         or {@link Optional#empty()} if no summarized review is found.
     */
    public Optional<Review> execute(String restaurantId) {
        return repository.findSummarizedReview(restaurantId);
    }
}
