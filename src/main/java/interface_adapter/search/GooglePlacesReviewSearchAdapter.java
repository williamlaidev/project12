package interface_adapter.search;

import entity.review.Review;
import entity.review.ReviewUserFactory;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GooglePlacesReviewSearchAdapter implements ReviewSearchAdapter {

    private static final Logger logger = LoggerFactory.getLogger(GooglePlacesReviewSearchAdapter.class);
    private static final ReviewUserFactory reviewUserFactory = new ReviewUserFactory();

    @Override
    public Review adaptToReview(JSONObject reviewJson, String restaurantId) {
        try {
            String author = reviewJson.optString("author_name", "Unknown Author");
            String content = reviewJson.optString("text", "No content available");

            return reviewUserFactory.createReview(restaurantId, author, content);
        } catch (Exception e) {
            logger.error("Error mapping JSON to Review for restaurant ID {}: {}", restaurantId, e.getMessage(), e);
            return null;
        }
    }
}
