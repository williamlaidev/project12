package data_access;

import entity.Review;
import java.util.List;

/**
 * This interface defines the methods for accessing review data.
 */
public interface ReviewDataAccess {

    /**
     * Loads a list of reviews from the data source.
     *
     * @return a list of {@link Review} objects loaded from the data source.
     *         If an error occurs or no data is available, an empty list is returned.
     */
    List<Review> loadReviews();

    /**
     * Saves a list of reviews to the data source.
     *
     * @param reviews the list of {@link Review} objects to be saved.
     *                If the list is null or empty, no changes will be made.
     */
    void saveReviews(List<Review> reviews);
}
