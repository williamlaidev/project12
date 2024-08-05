package entity.review;

public interface ReviewWithoutAuthorFactory {
    Review createReview(String restaurantId, String content);
}
