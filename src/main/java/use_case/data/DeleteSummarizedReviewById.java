package use_case.data;

import domain.ReviewRepository;

/**
 * Handles the use case for deleting the summarized review of a specific restaurant.
 * It uses the {@link ReviewRepository} to perform the deletion.
 */
public class DeleteSummarizedReviewById {

    private final ReviewRepository repository;

    /**
     * Creates an instance with the given repository.
     *
     * @param repository the {@link ReviewRepository} for deleting the summarized review.
     */
    public DeleteSummarizedReviewById(ReviewRepository repository) {
        this.repository = repository;
    }

    /**
     * Deletes the summarized review associated with the specified restaurant ID.
     *
     * @param restaurantId the ID of the restaurant whose summarized review is to be deleted.
     * @return {@code true} if the deletion was successful, {@code false} otherwise.
     */
    public boolean execute(String restaurantId) {
        return repository.deleteSummarizedById(restaurantId);
    }
}
