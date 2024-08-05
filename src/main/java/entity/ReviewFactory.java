package entity;

/**
 * Factory class for creating Review instances.
 */
public class ReviewFactory {

    /**
     * Creates a UserReview with the specified details and isSummarized set to false.
     *
     * @param restaurantId the ID of the restaurant being reviewed
     * @param author the author of the review
     * @param content the content of the review
     * @return a Review instance with isSummarized set to false
     * @throws IllegalArgumentException if any of the parameters are invalid
     */
    public static Review createUserReview(String restaurantId, String author, String content) {
        return new Review(restaurantId, author, content, false);
    }

    /**
     * Creates a GeminiSummarizedReview with the specified restaurant ID and content.
     * The author is set to "Gemini" and isSummarized is set to true.
     *
     * @param restaurantId the ID of the restaurant being reviewed
     * @param content the content of the review
     * @return a Review instance with author set to "Gemini" and isSummarized set to true
     * @throws IllegalArgumentException if any of the parameters are invalid
     */
    public static Review createGeminiSummarizedReview(String restaurantId, String content) {
        return new Review(restaurantId, "Gemini", content, true);
    }
}
