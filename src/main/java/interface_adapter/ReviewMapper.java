package interface_adapter;

import entity.Review;
import java.util.List;

/**
 * Interface for mapping between strings and reviews.
 */
public interface ReviewMapper {

    /**
     * Converts a list of Review objects into a single string.
     *
     * @param reviews the list of Review objects to be converted
     * @return the string representation of the reviews
     */
    String mapReviewsToString(List<Review> reviews);
}
