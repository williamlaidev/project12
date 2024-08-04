package entity;

public class GeminiReviewFactory implements ReviewFactoryWithoutAuthor {

    @Override
    public Review createReview(String restaurantId, String content) {
        return new Review(restaurantId, "Gemini", content, true);
    }
}
