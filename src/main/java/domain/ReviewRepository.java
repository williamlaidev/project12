package domain;

import entity.Review;

import java.util.Optional;
import java.util.List;

/**
 * Interface for managing {@link Review} entities in a repository.
 * Provides methods for adding, finding, updating, and deleting reviews.
 */
public interface ReviewRepository {

    /**
     * Adds a new review to the repository.
     *
     * @param review The review to add.
     * @return true if the review was added successfully, false otherwise.
     */
    boolean add(Review review);

    /**
     * Finds all reviews for a specific restaurant.
     *
     * @param restaurantId The ID of the restaurant.
     * @return An {@link Optional} containing a list of reviews if found, otherwise empty.
     */
    Optional<List<Review>> findUserReviews(String restaurantId);

    /**
     * Finds a summarized review for a specific restaurant.
     *
     * @param restaurantId The ID of the restaurant.
     * @return An {@link Optional} containing the summarized review if found, otherwise empty.
     */
    Optional<Review> findSummarizedReview(String restaurantId);

    /**
     * Finds all reviews in the repository.
     *
     * @return An {@link Optional} containing a list of all reviews if found, otherwise empty.
     */
    Optional<List<Review>> findAll();

    /**
     * Saves or updates a review in the repository.
     *
     * @param review The review to save or update.
     * @return true if the operation was successful, false otherwise.
     */
    boolean save(Review review);

    /**
     * Updates an existing review in the repository.
     *
     * @param review The review to update.
     * @return true if the review was updated successfully, false otherwise.
     */
    boolean update(Review review);

    /**
     * Deletes all reviews for a specific restaurant.
     *
     * @param restaurantId The ID of the restaurant.
     * @return true if the reviews were deleted successfully, false otherwise.
     */
    boolean deleteUserReviews(String restaurantId);

    /**
     * Deletes the summarized review for a specific restaurant.
     *
     * @param restaurantId The ID of the restaurant.
     * @return true if the summarized review was deleted successfully, false otherwise.
     */
    boolean deleteSummarizedReview(String restaurantId);

    /**
     * Deletes all reviews for a specific restaurant.
     *
     * @param restaurantId The ID of the restaurant.
     * @return true if all reviews were deleted successfully, false otherwise.
     */
    boolean deleteAllReviews(String restaurantId);
}
