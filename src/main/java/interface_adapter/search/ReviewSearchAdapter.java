package interface_adapter.search;

import entity.review.Review;
import org.json.JSONObject;

public interface ReviewSearchAdapter {
    Review adaptToReview(JSONObject reviewJson, String restaurantId);
}
