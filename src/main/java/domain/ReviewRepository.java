package domain;

import entity.Review;

import java.util.Optional;
import java.util.List;

/**
 * Interface for managing review data in a repository.
 * Provides methods to add, retrieve, update, and delete reviews.
 */
public interface ReviewRepository {

    /**
     * Adds a new review to the repository.
     * If a review by the same author for the same restaurant already exists, it is not added.
     *
     * @param review the review to be added
     * @return the added review if successful, otherwise null
     */
    Review add(Review review);

    /**
     * Retrieves a review for a specific restaurant by its ID.
     *
     * @param restaurantId the ID of the restaurant
     * @return an Optional containing the review if found, or an empty Optional if not found
     */
    Optional<Review> getReviewByRestaurant(String restaurantId);

    /**
     * Updates an existing review in the repository.
     * If no review is found for the given restaurant ID and author, the update fails.
     *
     * @param review the review to be updated
     * @return true if the review was updated successfully, false otherwise
     */
    boolean update(Review review);

    /**
     * Deletes a review from the repository by the restaurant's ID.
     *
     * @param reviewId the ID of the review to be deleted
     * @return true if the review was deleted successfully, false otherwise
     */
    boolean deleteReviewByRestaurant(String reviewId);

    /**
     * Deletes reviews from the repository based on whether they are summarized.
     * The summarized status typically indicates whether the review is a summary of multiple reviews.
     *
     * @param summarized the summarized status of the reviews to be deleted
     * @return true if the reviews were deleted successfully, false otherwise
     */
    boolean deleteReviewBySummarized(boolean summarized);
}
