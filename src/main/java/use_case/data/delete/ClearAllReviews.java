package use_case.data.delete;

import domain.ReviewRepository;

/**
 * Use case for clearing all reviews.
 * It uses the {@link ReviewRepository} to perform the clearance.
 */
public class ClearAllReviews {

    private final ReviewRepository repository;

    /**
     * Creates an instance with the given repository.
     *
     * @param repository the {@link ReviewRepository} for clearing reviews.
     */
    public ClearAllReviews(ReviewRepository repository) {
        this.repository = repository;
    }

    /**
     * Clears all reviews.
     *
     * @return {@code true} if the clearance was successful, {@code false} otherwise.
     */
    public boolean execute() {
        return repository.clearAll();
    }
}
