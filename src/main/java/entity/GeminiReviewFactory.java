package entity;

/**
 * Factory class for creating Review instances that are summarized by Gemini API.
 */
public class GeminiReviewFactory implements ReviewFactory {

    @Override
    public Review createReview(String restaurantId, String author, String content) {
        return new Review(restaurantId, "Gemini", content, true);
    }
}
