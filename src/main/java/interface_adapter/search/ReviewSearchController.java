package interface_adapter.search;

import use_case.search.FetchRestaurantReviews;
import entity.review.Review;

import java.util.List;

public class ReviewSearchController {

    private final FetchRestaurantReviews fetchRestaurantReviews;

    public ReviewSearchController(FetchRestaurantReviews fetchRestaurantReviews) {
        this.fetchRestaurantReviews = fetchRestaurantReviews;
    }

    public List<Review> getReviews(String restaurantId, int maxReviews) {
        return fetchRestaurantReviews.execute(restaurantId, maxReviews);
    }
}
