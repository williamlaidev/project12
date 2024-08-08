package entity.restaurant;

public class RestaurantAverageRatingValidator extends RestaurantValidator {

    @Override
    public boolean check(Restaurant restaurant) {
        double averageRating = restaurant.getAverageRating();
        if (averageRating < 0 || averageRating > 5) {
            System.out.println("Average rating validation failed.");
            return false;
        }
        return checkNext(restaurant);
    }
}
