package domain;

import entity.Review;
import java.util.List;

/**
 * Interface for summarizing a list of reviews.
 */
public interface ReviewSummarizeService {

    /**
     * Summarizes a list of reviews into a single review.
     *
     * @param reviews the list of reviews to summarize
     * @return the summarized review
     * @throws InterruptedException if the thread is interrupted during processing
     */
    Review summarize(List<Review> reviews) throws InterruptedException;
}
