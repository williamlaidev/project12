package entity;

public interface ReviewFactoryWithAuthor {
    Review createReview(String restaurantId, String author, String content);
}
