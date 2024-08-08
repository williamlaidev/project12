package interface_adapter.search;

import entity.review.Review;
import entity.review.ReviewUserFactory;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Adapter for converting Google Places JSON review data into Review entities.
 */
public class GooglePlacesReviewSearchAdapter implements ReviewSearchAdapter {

    private static final Logger logger = LoggerFactory.getLogger(GooglePlacesReviewSearchAdapter.class);
    private static final ReviewUserFactory reviewUserFactory = new ReviewUserFactory();

    /**
     * Converts a JSONObject representing a review into a Review entity.
     *
     * @param reviewJson  JSON object representing the review
     * @param restaurantId The ID of the restaurant associated with the review
     * @return A Review entity created from the JSON object
     */
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
