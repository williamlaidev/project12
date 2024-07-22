package interface_adapter;

import domain.ReviewRetrievalService;
import entity.Review;
import framework.GooglePlacesReviewsService;
import framework.EnvConfigService;
import framework.EnvConfigServiceImpl;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Gateway class for interacting with Google Places API to fetch reviews for a specific restaurant.
 */
public class PlacesReviewsGateways implements ReviewRetrievalService {

    private final GooglePlacesReviewsService reviewsService;

    /**
     * Constructs the PlacesReviewsGateway with the provided EnvConfigService.
     */
    public PlacesReviewsGateways() {
        EnvConfigService envConfigService = new EnvConfigServiceImpl();
        this.reviewsService = new GooglePlacesReviewsService(envConfigService);
    }

    /**
     * Retrieves the most relevant reviews for a given restaurant.
     *
     * @param restaurantId the ID of the restaurant
     * @return a list of the most relevant reviews if present, otherwise an empty list
     */
    @Override
    public List<Review> getReviewsForRestaurant(String restaurantId) {
        try {
            // Fetch reviews with default parameters (assuming default language and sorting)
            List<JSONObject> reviewJsonObjects = reviewsService.fetchReviews(restaurantId, "en", "most_relevant");
            List<Review> reviews = new ArrayList<>();

            // Limit to top six reviews
            int limit = Math.min(reviewJsonObjects.size(), 6);
            for (int i = 0; i < limit; i++) {
                JSONObject reviewJson = reviewJsonObjects.get(i);
                Review review = mapToReview(reviewJson, restaurantId); // Pass the restaurantId here
                if (review != null) {
                    reviews.add(review);
                }
            }

            return reviews;
        } catch (Exception e) {
            System.err.println("Error fetching reviews: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>(); // Return an empty list in case of error
        }
    }

    /**
     * Maps a JSON object to a Review instance.
     *
     * @param reviewJson the JSON object containing review details
     * @param restaurantId the ID of the restaurant
     * @return a Review instance
     */
    private Review mapToReview(JSONObject reviewJson, String restaurantId) {
        try {
            String author = reviewJson.optString("author_name");
            String content = reviewJson.optString("text");
            boolean isSummarized = false; // Set as needed

            // Return new Review instance
            return new Review(
                    restaurantId, // Set the actual restaurantId here
                    author,
                    content,
                    isSummarized
            );
        } catch (Exception e) {
            System.err.println("Error mapping JSON to Review: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
