package use_case.data;

import domain.ReviewRepository;

public class DeleteReviewByRestaurant {

    private final ReviewRepository reviewRepository;

    public DeleteReviewByRestaurant(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    /**
     * Deletes a review associated with a given restaurant.
     *
     * @param restaurantId the ID of the restaurant whose review is to be deleted
     * @return true if the deletion was successful, otherwise false
     */
    public boolean execute(String restaurantId) {
        return reviewRepository.deleteReviewByRestaurant(restaurantId);
    }
}
