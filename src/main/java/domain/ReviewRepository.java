package domain;

import entity.Review;

import java.util.Optional;
import java.util.List;

// TODO: Add JavaDoc documentation to describe the purpose and usage.

public interface ReviewRepository {

    Review add(Review review);

    Optional<Review> getReviewByRestaurant(String restaurantId);

    boolean update(Review review);

    boolean deleteReviewByRestaurant(String reviewId);

    boolean deleteReviewBySummarized(boolean summarized);
}
