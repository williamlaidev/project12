package use_case;

import domain.ReviewSummarizeService;
import entity.Review;

import java.util.List;

/**
 * Use case for summarizing a list of reviews.
 */
public class SummarizeReviews {
    private final ReviewSummarizeService summarizeService;

    /**
     * Constructs a SummarizeReviews use case with the given ReviewSummarizeService.
     *
     * @param summarizeService the ReviewSummarizeService to use for summarization
     */
    public SummarizeReviews(ReviewSummarizeService summarizeService) {
        this.summarizeService = summarizeService;
    }

    /**
     * Executes the summarization of the provided list of reviews.
     *
     * @param reviews the list of reviews to be summarized
     * @return the summarized review
     * @throws InterruptedException if the thread is interrupted while processing
     */
    public Review execute(List<Review> reviews) throws InterruptedException {
        // Use the summarizeService to perform the actual summarization
        return summarizeService.summarize(reviews);
    }
}
