package entity;

import java.util.Objects;

/**
 * Represents a review for a restaurant.
 * A review contains the ID of the restaurant being reviewed, the author of the review,
 * the content of the review, and a flag indicating whether the review is summarized.
 */
public class Review {
    private String restaurantId;
    private String author;
    private String content;
    private boolean isSummarized;

    /**
     * Constructs a new Review with the specified details.
     *
     * @param restaurantId the ID of the restaurant being reviewed
     * @param author the author of the review
     * @param content the content of the review
     * @param isSummarized flag indicating whether the review is summarized
     * @throws IllegalArgumentException if any of the validation checks fail
     */
    public Review(String restaurantId, String author, String content, boolean isSummarized) {
        validateRestaurantId(restaurantId);
        validateAuthor(author);
        validateContent(content);

        this.restaurantId = restaurantId;
        this.author = author;
        this.content = content;
        this.isSummarized = isSummarized;
    }

    /**
     * Returns the ID of the restaurant being reviewed.
     *
     * @return the restaurant ID
     */
    public String getRestaurantId() {
        return restaurantId;
    }

    /**
     * Sets the ID of the restaurant being reviewed.
     *
     * @param restaurantId the new restaurant ID
     * @throws IllegalArgumentException if the restaurant ID is invalid
     */
    public void setRestaurantId(String restaurantId) {
        validateRestaurantId(restaurantId);
        this.restaurantId = restaurantId;
    }

    /**
     * Returns the author of the review.
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the review.
     *
     * @param author the new author
     * @throws IllegalArgumentException if the author is invalid
     */
    public void setAuthor(String author) {
        validateAuthor(author);
        this.author = author;
    }

    /**
     * Returns the content of the review.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the review.
     *
     * @param content the new content
     * @throws IllegalArgumentException if the content is invalid
     */
    public void setContent(String content) {
        validateContent(content);
        this.content = content;
    }

    /**
     * Returns whether the review is summarized.
     *
     * @return true if the review is summarized, false otherwise
     */
    public boolean isSummarized() {
        return isSummarized;
    }

    /**
     * Sets the summarized flag of the review.
     *
     * @param summarized the new summarized flag
     */
    public void setSummarized(boolean summarized) {
        this.isSummarized = summarized;
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

    @Override
    public int hashCode() {
        return Objects.hash(restaurantId, author);
    }

    /**
     * Validates the restaurant ID.
     *
     * @param restaurantId the restaurant ID to validate
     * @throws IllegalArgumentException if the restaurant ID is null or empty
     */
    private void validateRestaurantId(String restaurantId) {
        if (restaurantId == null || restaurantId.trim().isEmpty()) {
            throw new IllegalArgumentException("Restaurant ID cannot be null or empty.");
        }
    }

    /**
     * Validates the author.
     *
     * @param author the author to validate
     * @throws IllegalArgumentException if the author is null or empty
     */
    private void validateAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be null or empty.");
        }
    }

    /**
     * Validates the content.
     *
     * @param content the content to validate
     * @throws IllegalArgumentException if the content is null or empty
     */
    private void validateContent(String content) {
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty.");
        }
    }
}
