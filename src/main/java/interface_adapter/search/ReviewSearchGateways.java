package interface_adapter.search;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public interface ReviewSearchGateways {
    List<JSONObject> fetchRelevantReviews(String restaurantId) throws JSONException;
}
