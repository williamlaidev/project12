package interface_adapter.search;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Interface for fetching review data from external sources.
 */
public interface ReviewSearchGateways {
    /**
     * Fetches relevant reviews for a restaurant.
     *
     * @param restaurantId The ID of the restaurant to fetch reviews for
     * @return List of JSON objects representing the reviews
     * @throws JSONException If a JSON parsing error occurs
     */
    List<JSONObject> fetchRelevantReviews(String restaurantId) throws JSONException;
}
