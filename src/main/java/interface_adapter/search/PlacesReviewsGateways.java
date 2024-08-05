package interface_adapter.search;

import domain.ReviewRetrievalService;
import entity.Review;
import entity.ReviewFactory;
import framework.search.GooglePlacesReviewsService;
import framework.config.EnvConfigService;
import framework.config.EnvConfigServiceImpl;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import use_case.data.AddReview;

import java.util.ArrayList;
import java.util.List;

/**
 * Gateway class for interacting with the Google Places API to fetch and manage reviews for a specific restaurant.
 * Implements the {@link ReviewRetrievalService} interface to retrieve reviews and add them to a repository.
 */
public class PlacesReviewsGateways implements ReviewRetrievalService {

    protected final GooglePlacesReviewsService reviewsService;
    private final AddReview addReviewUseCase;
    private static final Logger logger = LoggerFactory.getLogger(PlacesReviewsGateways.class);

    /**
     * Constructs a {@code PlacesReviewsGateways} instance with the provided {@link AddReview} use case.
     * Initializes the Google Places review service using environment configurations.
     *
     * @param addReviewUseCase the {@link AddReview} use case for persisting reviews to the repository
     */
    public PlacesReviewsGateways(AddReview addReviewUseCase) {
        EnvConfigService envConfigService = new EnvConfigServiceImpl();
        this.reviewsService = new GooglePlacesReviewsService(envConfigService);
        this.addReviewUseCase = addReviewUseCase;
    }

    /**
     * Retrieves and processes the most relevant reviews for a given restaurant.
     * Fetches reviews from the Google Places API, maps them to {@link Review} objects,
     * and adds them to the repository using the {@link AddReview} use case.
     *
     * @param restaurantId the ID of the restaurant for which reviews are to be fetched
     * @return a list of the most relevant {@link Review} objects; returns an empty list if no reviews are found or an error occurs
     */
    @Override
    public List<Review> getReviewsForRestaurant(String restaurantId) {
        try {
            List<JSONObject> reviewJsonObjects = reviewsService.fetchReviews(restaurantId, "en", "most_relevant");
            List<Review> reviews = new ArrayList<>();

            int limit = Math.min(reviewJsonObjects.size(), 6);
            for (int i = 0; i < limit; i++) {
                JSONObject reviewJson = reviewJsonObjects.get(i);
                Review review = mapToReview(reviewJson, restaurantId);
                if (review != null) {
                    reviews.add(review);
                    addReviewUseCase.execute(review);
                }
            }

            return reviews;
        } catch (Exception e) {
            logger.error("Error fetching reviews for restaurant ID {}: {}", restaurantId, e.getMessage(), e);
            return new ArrayList<>(); // Return an empty list in case of error
        }
    }

    /**
     * Maps a JSON object representing a review to a {@link Review} instance using the {@link ReviewFactory}.
     *
     * @param reviewJson the JSON object containing the review details
     * @param restaurantId the ID of the restaurant associated with the review
     * @return a {@link Review} instance created from the JSON data, or {@code null} if mapping fails
     */
    private Review mapToReview(JSONObject reviewJson, String restaurantId) {
        try {
            String author = reviewJson.optString("author_name");
            String content = reviewJson.optString("text");

            return ReviewFactory.createUserReview(restaurantId, author, content);
        } catch (Exception e) {
            logger.error("Error mapping JSON to Review for restaurant ID {}: {}", restaurantId, e.getMessage(), e);
            return null;
        }
    }
}
