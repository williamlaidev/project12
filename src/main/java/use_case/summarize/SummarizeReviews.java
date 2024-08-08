package use_case.summarize;

import domain.ReviewSummarizeService;
import entity.review.Review;

import java.util.List;

/**
 * Use case for summarizing a list of reviews.
 */
public class SummarizeReviews {
    private final ReviewSummarizeService summarizeService;

    /**
     * Constructs a SummarizeReviews instance.
     *
     * @param summarizeService service to summarize the reviews
     */
    public SummarizeReviews(ReviewSummarizeService summarizeService) {
        this.summarizeService = summarizeService;
    }

    /**
     * Summarizes the provided list of reviews.
     *
     * @param reviews list of reviews to summarize
     * @return summarized review
     * @throws InterruptedException if the thread is interrupted during processing
     */
    public Review execute(List<Review> reviews) throws InterruptedException {
        return summarizeService.createSummaryFromReviews(reviews);
    }
}
