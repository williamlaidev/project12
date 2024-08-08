package use_case.data.delete;

import domain.ReviewRepository;

/**
 * Use case for deleting all reviews for a specific restaurant.
 * It uses the {@link ReviewRepository} to perform the deletion.
 */
public class DeleteAllReviewsById {

    private final ReviewRepository repository;

    /**
     * Creates an instance with the given repository.
     *
     * @param repository the {@link ReviewRepository} for deleting reviews.
     */
    public DeleteAllReviewsById(ReviewRepository repository) {
        this.repository = repository;
    }

    /**
     * Deletes all reviews for the given restaurant ID.
     *
     * @param restaurantId the ID of the restaurant whose reviews are to be deleted.
     * @return {@code true} if the deletion was successful, {@code false} otherwise.
     */
    public boolean execute(String restaurantId) {
        return repository.deleteAllById(restaurantId);
    }
}
