package interface_adapter;

import entity.Review;
import java.util.List;

/**
 * Interface for the review summarization controller.
 * This controller handles the summarization of a list of reviews.
 */
public interface ReviewSummarizeController {
    /**
     * Summarizes the provided list of reviews.
     *
     * @param reviews the list of reviews to be summarized
     * @return the summarized review
     * @throws InterruptedException if the thread is interrupted while processing
     */
    Review summarize(List<Review> reviews) throws InterruptedException;
}
