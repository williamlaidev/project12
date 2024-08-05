package interface_adapter.summarize;

import entity.review.Review;

import java.util.List;

public interface ReviewSummarizeAdapter {
    String adaptToReviewString(List<Review> reviews);
    Review adaptToReview(String restaurantId, String content);
}
