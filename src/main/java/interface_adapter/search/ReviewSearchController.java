package interface_adapter.search;

import use_case.search.FetchRestaurantReviews;
import entity.review.Review;

import java.util.List;

/**
 * Controller for handling review search requests.
 */
public class ReviewSearchController {

    private final FetchRestaurantReviews fetchRestaurantReviews;

    /**
     * Constructs a ReviewSearchController with the given FetchRestaurantReviews use case.
     *
     * @param fetchRestaurantReviews Use case for fetching restaurant reviews
     */
    public ReviewSearchController(FetchRestaurantReviews fetchRestaurantReviews) {
        this.fetchRestaurantReviews = fetchRestaurantReviews;
    }

    /**
     * Fetches reviews for a restaurant.
     *
     * @param restaurantId The ID of the restaurant to fetch reviews for
     * @param maxReviews   Maximum number of reviews to fetch
     * @return List of reviews fetched for the restaurant
     */
    public List<Review> getReviews(String restaurantId, int maxReviews) {
        return fetchRestaurantReviews.execute(restaurantId, maxReviews);
    }
}
