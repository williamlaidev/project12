package interface_adapter.search;

import entity.review.Review;
import org.json.JSONObject;

/**
 * Interface for adapting JSON review data into Review entities.
 */
public interface ReviewSearchAdapter {
    /**
     * Converts a JSONObject representing a review into a Review entity.
     *
     * @param reviewJson  JSON object representing the review
     * @param restaurantId The ID of the restaurant associated with the review
     * @return A Review entity created from the JSON object
     */
    Review adaptToReview(JSONObject reviewJson, String restaurantId);
}
