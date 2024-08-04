package entity;

public interface ReviewFactoryWithoutAuthor {
    Review createReview(String restaurantId, String content);
}
