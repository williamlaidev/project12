package entity.review;

/**
 * Factory interface for creating reviews with an author.
 */
public interface ReviewWithAuthorFactory {
    /**
     * Creates a review with the specified restaurant ID, author, and content.
     *
     * @param restaurantId The ID of the restaurant associated with the review.
     * @param author The author of the review.
     * @param content The content of the review.
     * @return A new Review instance.
     */
    Review createReview(String restaurantId, String author, String content);
}
