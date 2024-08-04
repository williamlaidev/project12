package entity.review;

import java.util.Objects;

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

    public Review(String restaurantId, String author, String content, boolean isSummarized) {
        this.restaurantId = restaurantId;
        this.author = author;
        this.content = content;
        this.isSummarized = isSummarized;

        if (!validatorChain.check(this)) {
            throw new IllegalArgumentException("Invalid review data.");
        }
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
}
