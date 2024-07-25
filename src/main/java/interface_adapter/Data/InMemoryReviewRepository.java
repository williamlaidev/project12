package interface_adapter.Data;

import domain.ReviewRepository;
import data_access.ReviewDataAccess;
import entity.Review;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * An in-memory implementation of {@link ReviewRepository} that manages reviews using a list.
 * This class supports CRUD operations on reviews and can persist data to a data source using a {@link ReviewDataAccess} object.
 */
public class InMemoryReviewRepository implements ReviewRepository {
    private final List<Review> reviewStore = new ArrayList<>();
    private final ReviewDataAccess dataAccess;
    private boolean isDirty = false;

    /**
     * Constructs an {@link InMemoryReviewRepository} with the specified {@link ReviewDataAccess} for data operations.
     *
     * @param dataAccess the {@link ReviewDataAccess} instance used for loading and saving review data.
     */
    public InMemoryReviewRepository(ReviewDataAccess dataAccess) {
        this.dataAccess = dataAccess;
        this.reviewStore.addAll(dataAccess.loadReviews());
    }

    @Override
    public Review add(Review review) {
        String reviewId = review.getRestaurantId();
        if (getReviewByRestaurant(reviewId).isPresent()) {
            System.out.println("Review by author " + review.getAuthor() + " for restaurant ID " + reviewId + " already exists. Addition failed.");
            return null; // Review by the same author for the same restaurant already exists
        }
        reviewStore.add(review);
        isDirty = true;
        System.out.println("Successfully added review: " + review);
        return review; // Successfully added the new review
    }

    @Override
    public Optional<Review> getReviewByRestaurant(String restaurantId) {
        Optional<Review> review = reviewStore.stream()
                .filter(r -> r.getRestaurantId().equals(restaurantId))
                .findFirst();

        if (review.isPresent()) {
            System.out.println("Review found for restaurant ID " + restaurantId + ": " + review.get());
        } else {
            System.out.println("No review found for restaurant ID " + restaurantId + ".");
        }

        return review;
    }

    @Override
    public boolean update(Review review) {
        boolean updated = false;
        for (Review existingReview : reviewStore) {
            if (existingReview.getRestaurantId().equals(review.getRestaurantId()) && existingReview.getAuthor().equals(review.getAuthor())) {
                // Update existing review details
                existingReview.setContent(review.getContent());
                existingReview.setSummarized(review.isSummarized());
                isDirty = true;
                updated = true;
                System.out.println("Updated review: " + existingReview);
            }
        }
        if (!updated) {
            System.out.println("No review found to update for restaurant ID " + review.getRestaurantId() + " and author " + review.getAuthor() + ".");
        }
        return updated;
    }

    @Override
    public boolean deleteReviewByRestaurant(String restaurantId) {
        boolean removed = reviewStore.removeIf(review -> review.getRestaurantId().equals(restaurantId));
        if (removed) {
            isDirty = true;
            System.out.println("Deleted reviews for restaurant ID " + restaurantId + ".");
        } else {
            System.out.println("No reviews found to delete for restaurant ID " + restaurantId + ".");
        }
        return removed;
    }

    @Override
    public boolean deleteReviewBySummarized(boolean summarized) {
        boolean removed = reviewStore.removeIf(review -> review.isSummarized() == summarized);
        if (removed) {
            isDirty = true;
            System.out.println("Deleted reviews with summarized status " + summarized + ".");
        } else {
            System.out.println("No reviews found to delete with summarized status " + summarized + ".");
        }
        return removed;
    }

    public void saveIfDirty() {
        if (isDirty) {
            dataAccess.saveReviews(reviewStore);
            isDirty = false;
            System.out.println("Saved reviews to data source.");
        } else {
            System.out.println("No changes to save.");
        }
    }
}
