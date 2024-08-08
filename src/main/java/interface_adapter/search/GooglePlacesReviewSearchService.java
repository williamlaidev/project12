package interface_adapter.search;

import domain.ReviewSearchService;
import entity.review.Review;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import use_case.data.create.AddReview;

import java.util.ArrayList;
import java.util.List;

public class GooglePlacesReviewSearchService implements ReviewSearchService {
    private static final Logger logger = LoggerFactory.getLogger(GooglePlacesReviewSearchService.class);
    private final GooglePlacesReviewSearchAdapter searchAdapter;
    private final ReviewSearchGateways reviewSearchGateways;
    private final AddReview addReviewUseCase;

    // Constructor with dependency injection
    public GooglePlacesReviewSearchService(GooglePlacesReviewSearchAdapter searchAdapter,
                                           ReviewSearchGateways reviewSearchGateways,
                                           AddReview addReviewUseCase) {
        this.searchAdapter = searchAdapter;
        this.reviewSearchGateways = reviewSearchGateways;
        this.addReviewUseCase = addReviewUseCase;
    }

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
