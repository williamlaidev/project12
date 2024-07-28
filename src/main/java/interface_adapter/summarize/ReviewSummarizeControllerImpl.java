package interface_adapter.summarize;

import entity.Review;
import use_case.summarize.SummarizeReviews;
import java.util.List;

/**
 * Implementation of the ReviewSummarizeController interface.
 * This class coordinates the summarization process and interacts with the SummarizeReviews use case.
 */
public class ReviewSummarizeControllerImpl implements ReviewSummarizeController {
    private final SummarizeReviews summarizeReviews;
    private final ReviewMapper reviewMapper;

    /**
     * Constructs a ReviewSummarizeControllerImpl with the given SummarizeReviews use case and ReviewMapper.
     *
     * @param summarizeReviews the SummarizeReviews use case
     * @param reviewMapper the ReviewMapper for converting data
     */
    public ReviewSummarizeControllerImpl(SummarizeReviews summarizeReviews, ReviewMapper reviewMapper) {
        this.summarizeReviews = summarizeReviews;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public Review summarize(List<Review> reviews) throws InterruptedException {

        Review summarizedReview = summarizeReviews.execute(reviews);

        return summarizedReview;
    }
}
