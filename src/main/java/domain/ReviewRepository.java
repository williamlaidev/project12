package domain;

import entity.operation_result.OperationResult;
import entity.review.Review;

import java.util.Optional;
import java.util.List;

/**
 * Repository interface for managing {@code Review} entities.
 */
public interface ReviewRepository {

    /**
     * Adds a new review to the repository.
     *
     * @param review the review to be added
     * @return the result of the operation
     */
    OperationResult add(Review review);

    /**
     * Finds all reviews for a specific restaurant.
     *
     * @param restaurantId the unique identifier of the restaurant
     * @return a list of reviews for the specified restaurant
     */
    List<Review> findUserReviews(String restaurantId);

    /**
     * Finds a summarized review for a specific restaurant.
     *
     * @param restaurantId the unique identifier of the restaurant
     * @return an {@code Optional} containing the summarized review if found, or empty if not
     */
    Optional<Review> findSummarizedReview(String restaurantId);

    /**
     * Retrieves all reviews from the repository.
     *
     * @return a list of all reviews
     */
    List<Review> findAll();

    /**
     * Saves or updates a review in the repository.
     *
     * @param review the review to be saved or updated
     * @return the result of the operation
     */
    OperationResult save(Review review);

    /**
     * Updates an existing review in the repository.
     *
     * @param review the review to be updated
     * @return the result of the operation
     */
    OperationResult update(Review review);

    /**
     * Deletes all reviews by user for a specific restaurant.
     *
     * @param restaurantId the unique identifier of the restaurant
     * @return {@code true} if the reviews were deleted successfully, {@code false} otherwise
     */
    boolean deleteUserById(String restaurantId);

    /**
     * Deletes the summarized review for a specific restaurant.
     *
     * @param restaurantId the unique identifier of the restaurant
     * @return {@code true} if the summarized review was deleted successfully, {@code false} otherwise
     */
    boolean deleteSummarizedById(String restaurantId);

    /**
     * Deletes all reviews for a specific restaurant.
     *
     * @param restaurantId the unique identifier of the restaurant
     * @return {@code true} if all reviews were deleted successfully, {@code false} otherwise
     */
    boolean deleteAllById(String restaurantId);

    /**
     * Clears all reviews from the repository.
     *
     * @return {@code true} if all reviews were cleared successfully, {@code false} otherwise
     */
    boolean clearAll();
}
