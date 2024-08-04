package use_case.data;

import domain.ReviewRepository;

/**
 * Handles the use case for deleting user reviews of a specific restaurant.
 * It uses the {@link ReviewRepository} to perform the deletion.
 */
public class DeleteUserReviewsById {

    private final ReviewRepository repository;

    /**
     * Creates an instance with the given repository.
     *
     * @param repository the {@link ReviewRepository} for deleting user reviews.
     */
    public DeleteUserReviewsById(ReviewRepository repository) {
        this.repository = repository;
    }

    /**
     * Deletes all user reviews associated with the specified restaurant ID.
     *
     * @param restaurantId the ID of the restaurant whose user reviews are to be deleted.
     * @return {@code true} if the deletion was successful, {@code false} otherwise.
     */
    public boolean execute(String restaurantId) {
        return repository.deleteUserById(restaurantId);
    }
}
