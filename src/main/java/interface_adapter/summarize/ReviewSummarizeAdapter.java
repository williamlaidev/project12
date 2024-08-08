package interface_adapter.summarize;

import entity.review.Review;

import java.util.List;

/**
 * Interface for adapting reviews to different formats and creating review entities.
 */
public interface ReviewSummarizeAdapter {
    /**
     * Adapts a list of reviews to a formatted string.
     *
     * @param reviews List of reviews to be adapted
     * @return A formatted string containing the review details
     */
    String adaptToReviewString(List<Review> reviews);

    /**
     * Creates a Review entity with the given restaurant ID and content.
     *
     * @param restaurantId The ID of the restaurant
     * @param content       The review content
     * @return A Review entity
     */
    Review adaptToReview(String restaurantId, String content);
}
