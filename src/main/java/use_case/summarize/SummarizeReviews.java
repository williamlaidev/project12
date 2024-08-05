package use_case.summarize;

import domain.ReviewSummarizeService;
import entity.Review;

import java.util.List;

/**
 * Use case for summarizing a list of reviews.
 * This class uses a {@link ReviewSummarizeService} to generate a summarized review
 * from a list of individual reviews.
 */
public class SummarizeReviews {
    private final ReviewSummarizeService summarizeService;

    /**
     * Constructs a SummarizeReviews use case with the specified {@link ReviewSummarizeService}.
     *
     * @param summarizeService the service used to summarize the reviews
     */
    public SummarizeReviews(ReviewSummarizeService summarizeService) {
        this.summarizeService = summarizeService;
    }

    /**
     * Executes the summarization of the provided list of reviews.
     * This method processes the list of reviews and returns a single summarized review.
     *
     * @param reviews the list of {@link Review} objects to be summarized
     * @return a single summarized {@link Review} object
     * @throws InterruptedException if the thread is interrupted while processing
     */
    public Review execute(List<Review> reviews) throws InterruptedException {
        return summarizeService.summarize(reviews);
    }
}
