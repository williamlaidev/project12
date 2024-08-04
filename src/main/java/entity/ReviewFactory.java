package entity;

/**
 * Interface for creating Review instances.
 */
public interface ReviewFactory {

    /**
     * Creates a Review instance with the specified details.
     *
     * @param restaurantId the ID of the restaurant being reviewed
     * @param author the author of the review
     * @param content the content of the review
     * @return a Review instance
     * @throws IllegalArgumentException if any of the parameters are invalid
     */
    Review createReview(String restaurantId, String author, String content);
}
