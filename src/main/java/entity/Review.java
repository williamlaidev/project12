package entity;

import java.util.Objects;

public class Review {
    private final String restaurantId;
    private final String author;
    private final String content;
    private final boolean isSummarized;

    public Review(String restaurantId, String author, String content, boolean isSummarized) {
        validateRestaurantId(restaurantId);
        validateAuthor(author);
        validateContent(content);

        this.restaurantId = restaurantId;
        this.author = author;
        this.content = content;
        this.isSummarized = isSummarized;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

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
