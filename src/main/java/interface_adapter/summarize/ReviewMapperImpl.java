package interface_adapter.summarize;

import entity.Review;
import use_case.data.GetRestaurantById;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of the ReviewMapper interface.
 * This class maps a list of Review objects to a string representation by grouping reviews
 * by restaurant and including restaurant names in the output string.
 */
public class ReviewMapperImpl implements ReviewMapper {

    private final GetRestaurantById getRestaurantById; // Use case for retrieving restaurant names

    /**
     * Constructs a ReviewMapperImpl with a specified GetRestaurantById instance.
     *
     * @param getRestaurantById the GetRestaurantById instance used to retrieve restaurant names
     */
    public ReviewMapperImpl(GetRestaurantById getRestaurantById) {
        this.getRestaurantById = getRestaurantById; // Initialize with the provided GetRestaurantById instance
    }

    @Override
    public String mapReviewsToString(List<Review> reviews) {
        // If the reviews list is null or empty, return "No Reviews"
        if (reviews == null || reviews.isEmpty()) {
            return "No Reviews";
        }

        // Group reviews by restaurantId
        Map<String, List<Review>> groupedReviews = reviews.stream()
                .collect(Collectors.groupingBy(Review::getRestaurantId));

        // For each group of reviews, retrieve the restaurant name and format the review contents
        return groupedReviews.entrySet().stream()
                .map(entry -> {
                    String restaurantId = entry.getKey(); // Get the restaurantId from the map entry
                    List<Review> reviewList = entry.getValue(); // Get the list of reviews for this restaurantId

                    // Retrieve the restaurant name using the GetRestaurantById use case
                    Optional<String> restaurantNameOpt = getRestaurantById.execute(restaurantId)
                            .map(entity.Restaurant::getName);

                    // If restaurant name is not found, use "Unknown Restaurant" as a fallback
                    String restaurantName = restaurantNameOpt.orElse("Unknown Restaurant");

                    // Concatenate the contents of all reviews for this restaurant into a single string
                    String reviewContents = reviewList.stream()
                            .map(Review::getContent) // Extract the content of each review
                            .collect(Collectors.joining(" ")); // Join the contents with a space

                    // Return a formatted string combining the restaurant name and concatenated review contents
                    return restaurantName + ": " + reviewContents;
                })
                .collect(Collectors.joining("; ")); // Join all restaurant-review strings with "; "
    }
}
