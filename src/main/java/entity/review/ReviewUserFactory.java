package entity.review;

/**
 * Factory for creating Review instances that are collected from the Google Places API.
 */
public class ReviewUserFactory implements ReviewWithAuthorFactory {

    /**
     * Creates a review with the specified restaurant ID, author, and content.
     *
     * @param restaurantId The ID of the restaurant associated with the review.
     * @param author The author of the review.
     * @param content The content of the review.
     * @return A new Review instance with the specified author and content.
     */
    @Override
    public Review createReview(String restaurantId, String author, String content) {
        return new Review(restaurantId, author, content, false);
    }
}
