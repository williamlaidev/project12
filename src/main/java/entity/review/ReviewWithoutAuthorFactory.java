package entity.review;

/**
 * Factory interface for creating reviews without an author.
 */
public interface ReviewWithoutAuthorFactory {
    /**
     * Creates a review with the specified restaurant ID and content, using a default author.
     *
     * @param restaurantId The ID of the restaurant associated with the review.
     * @param content The content of the review.
     * @return A new Review instance.
     */
    Review createReview(String restaurantId, String content);
}
