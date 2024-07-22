package use_case;

import domain.ReviewRetrievalService;
import entity.Review;

import java.util.List;

public class GetReviewsForRestaurant {
    private final ReviewRetrievalService reviewRetrievalService;

    public GetReviewsForRestaurant(ReviewRetrievalService reviewRetrievalService) {
        this.reviewRetrievalService = reviewRetrievalService;
    }

    public List<Review> execute(String restaurantId) {
        return reviewRetrievalService.getReviewsForRestaurant(restaurantId);
    }
}