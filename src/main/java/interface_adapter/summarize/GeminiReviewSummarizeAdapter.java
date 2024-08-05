package interface_adapter.summarize;

import entity.review.Review;
import entity.review.ReviewGeminiFactory;
import use_case.data.read.FindRestaurantById;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GeminiReviewSummarizeAdapter implements ReviewSummarizeAdapter {

    private final FindRestaurantById getRestaurantById;
    private final ReviewGeminiFactory reviewFactory;

    public GeminiReviewSummarizeAdapter(FindRestaurantById getRestaurantById, ReviewGeminiFactory reviewFactory) {
        this.getRestaurantById = getRestaurantById;
        this.reviewFactory = reviewFactory;
    }

    @Override
    public String adaptToReviewString(List<Review> reviews) {
        if (reviews == null || reviews.isEmpty()) {
            return "This restaurant have no reviews";
        }

        Optional<String> restaurantNameOpt = reviews.stream()
                .findFirst() // Get the first review to retrieve the restaurantId
                .map(Review::getRestaurantId)
                .flatMap(restaurantId -> getRestaurantById.execute(restaurantId)
                        .map(entity.restaurant.Restaurant::getName));

        // If restaurant name is not found, use "Unknown Restaurant" as a fallback
        String restaurantName = restaurantNameOpt.orElse("Unknown Restaurant");

        // Format the contents of all reviews into a single string with each review on a new line prefixed by "-"
        String reviewContents = reviews.stream()
                .map(review -> "- " + review.getContent())
                .collect(Collectors.joining("\n"));

        return restaurantName + ":\n" + reviewContents;
    }

    @Override
    public Review adaptToReview(String restaurantId, String content) {
        return reviewFactory.createReview(restaurantId, content);
    }
}
