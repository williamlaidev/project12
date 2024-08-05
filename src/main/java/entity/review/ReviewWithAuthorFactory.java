package entity.review;

public interface ReviewWithAuthorFactory {
    Review createReview(String restaurantId, String author, String content);
}
