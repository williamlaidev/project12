package interface_adapter.search;

import domain.ReviewSearchService;
import entity.review.Review;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import use_case.data.create.AddReview;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for fetching and processing reviews using Google Places API.
 */
public class GooglePlacesReviewSearchService implements ReviewSearchService {

    private static final Logger logger = LoggerFactory.getLogger(GooglePlacesReviewSearchService.class);
    private final GooglePlacesReviewSearchAdapter searchAdapter;
    private final ReviewSearchGateways reviewSearchGateways;
    private final AddReview addReviewUseCase;

    /**
     * Constructs a GooglePlacesReviewSearchService with the given dependencies.
     *
     * @param searchAdapter        Adapter for converting JSON reviews into Review entities
     * @param reviewSearchGateways Gateway for fetching review data
     * @param addReviewUseCase     Use case for adding reviews
     */
    public GooglePlacesReviewSearchService(GooglePlacesReviewSearchAdapter searchAdapter,
                                           ReviewSearchGateways reviewSearchGateways,
                                           AddReview addReviewUseCase) {
        this.searchAdapter = searchAdapter;
        this.reviewSearchGateways = reviewSearchGateways;
        this.addReviewUseCase = addReviewUseCase;
    }

    /**
     * Fetches relevant reviews for a restaurant and adds them to the system.
     *
     * @param restaurantId The ID of the restaurant to fetch reviews for
     * @param maxReviews   Maximum number of reviews to fetch
     * @return List of reviews fetched and added to the system
     */
    @Override
    public List<Review> fetchRelevantReviews(String restaurantId, int maxReviews) {
        List<Review> reviews = new ArrayList<>();
        try {
            List<JSONObject> reviewJsonObjects = reviewSearchGateways.fetchRelevantReviews(restaurantId);
            int limit = Math.min(reviewJsonObjects.size(), maxReviews);

            for (int i = 0; i < limit; i++) {
                JSONObject reviewJson = reviewJsonObjects.get(i);
                Review review = searchAdapter.adaptToReview(reviewJson, restaurantId);
                if (review != null) {
                    reviews.add(review);
                    addReviewUseCase.execute(review);
                }
            }
        } catch (Exception e) {
            logger.error("Error fetching reviews for restaurant ID {}: {}", restaurantId, e.getMessage(), e);
        }
        return reviews;
    }
}
