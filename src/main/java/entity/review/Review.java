package entity.review;

import java.util.Objects;

/**
 * Represents a review for a restaurant with associated details and validation.
 */
public class Review {
    private final String restaurantId;
    private final String author;
    private final String content;
    private final boolean isSummarized;

    private static final ReviewValidator validatorChain;

    static {
        validatorChain = new ReviewRestaurantIdValidator();
        validatorChain.linkWith(new ReviewAuthorValidator())
                .linkWith(new ReviewContentValidator());
    }

    /**
     * Constructs a Review with the specified details and validates the review data.
     *
     * @param restaurantId The ID of the restaurant associated with the review.
     * @param author The author of the review.
     * @param content The content of the review.
     * @param isSummarized Indicates whether the review is summarized.
     * @throws IllegalArgumentException If the review data is invalid.
     */
    public Review(String restaurantId, String author, String content, boolean isSummarized) {
        this.restaurantId = restaurantId;
        this.author = author;
        this.content = content;
        this.isSummarized = isSummarized;

        if (!validatorChain.check(this)) {
            throw new IllegalArgumentException("Invalid review data.");
        }
    }

    /**
     * Gets the restaurant ID associated with the review.
     *
     * @return The restaurant ID.
     */
    public String getRestaurantId() {
        return restaurantId;
    }

    /**
     * Gets the author of the review.
     *
     * @return The author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Gets the content of the review.
     *
     * @return The content.
     */
    public String getContent() {
        return content;
    }

    /**
     * Checks if the review is summarized.
     *
     * @return true if the review is summarized; false otherwise.
     */
    public boolean isSummarized() {
        return isSummarized;
    }

    @Override
    public String toString() {
        return "Review{" +
                "restaurantId='" + restaurantId + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", isSummarized=" + isSummarized +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(restaurantId, review.restaurantId) &&
                Objects.equals(author, review.author);
    }
}
