package use_case.data;

import domain.ReviewRepository;
import entity.Review;

import java.util.List;
import java.util.Optional;

/**
 * Handles the use case for finding all reviews in the repository.
 * It uses the {@link ReviewRepository} to retrieve all reviews.
 */
public class FindAllReviews {

    private final ReviewRepository repository;

    /**
     * Creates an instance with the given repository.
     *
     * @param repository the {@link ReviewRepository} for finding all reviews.
     */
    public FindAllReviews(ReviewRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all reviews from the repository.
     *
     * @return an {@link Optional} containing a list of {@link Review} if reviews are found,
     *         or {@link Optional#empty()} if no reviews are found.
     */
    public Optional<List<Review>> execute() {
        return repository.findAll();
    }
}
