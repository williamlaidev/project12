package entity;

/**
 * Factory class for creating Review instances that are collected from Google Places API.
 */
public class UserReviewFactory implements ReviewFactory {

    @Override
    public Review createReview(String restaurantId, String author, String content) {
        return new Review(restaurantId, author, content, false);
    }
}
