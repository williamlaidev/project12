package entity.review;

public class ReviewGeminiFactory implements ReviewWithoutAuthorFactory {

    @Override
    public Review createReview(String restaurantId, String content) {
        return new Review(restaurantId, "Gemini", content, true);
    }
}
