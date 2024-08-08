package entity.review;

public class ReviewRestaurantIdValidator extends ReviewValidator {

    @Override
    public boolean check(Review review) {
        String restaurantId = review.getRestaurantId();
        if (restaurantId == null || restaurantId.trim().isEmpty()) {
            System.out.println("Restaurant ID validation failed.");
            return false;
        }
        return checkNext(review);
    }
}
