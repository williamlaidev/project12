package entity.review;

/**
 * Factory for creating Review instances with a default author "Gemini".
 */
public class ReviewGeminiFactory implements ReviewWithoutAuthorFactory {

    /**
     * Creates a review with the specified restaurant ID and content, using "Gemini" as the author.
     *
     * @param restaurantId The ID of the restaurant associated with the review.
     * @param content The content of the review.
     * @return A new Review instance with "Gemini" as the author.
     */
    @Override
    public Review createReview(String restaurantId, String content) {
        return new Review(restaurantId, "Gemini", content, true);
    }
}
