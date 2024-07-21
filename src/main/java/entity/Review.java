package entity;

import java.util.Objects;

public class Review {
    private String restaurantId; // Change to store restaurant ID
    private String author;
    private String content;
    private boolean summarizedReview;

    public Review(String restaurantId, String author, String content, boolean summarizedReview) {
        validateRestaurantId(restaurantId);
        validateAuthor(author);
        validateContent(content);

        this.restaurantId = restaurantId;
        this.author = author;
        this.content = content;
        this.summarizedReview = summarizedReview;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        validateRestaurantId(restaurantId);
        this.restaurantId = restaurantId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        validateAuthor(author);
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        validateContent(content);
        this.content = content;
    }

    public boolean isSummarizedReview() {
        return summarizedReview;
    }

    public void setSummarizedReview(boolean summarizedReview) {
        this.summarizedReview = summarizedReview;
    }

    @Override
    public String toString() {
        return "Review{" +
                "restaurantId='" + restaurantId + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", summarizedReview=" + summarizedReview +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return summarizedReview == review.summarizedReview &&
                Objects.equals(restaurantId, review.restaurantId) &&
                Objects.equals(author, review.author) &&
                Objects.equals(content, review.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurantId, author, content, summarizedReview);
    }

    // Validation methods
    private void validateRestaurantId(String restaurantId) {
        if (restaurantId == null || restaurantId.trim().isEmpty()) {
            throw new IllegalArgumentException("Restaurant ID cannot be null or empty.");
        }
    }

    private void validateAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be null or empty.");
        }
    }

    private void validateContent(String content) {
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty.");
        }
    }
}
