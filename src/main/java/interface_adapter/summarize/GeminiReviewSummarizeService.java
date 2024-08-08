package interface_adapter.summarize;

import domain.ReviewSummarizeService;
import entity.review.Review;
import use_case.data.create.AddReview;
import use_case.data.read.FindSummarizedReview;
import use_case.data.update.UpdateReview;

import java.util.List;

/**
 * Provides review summarization services including creating, updating, and finding reviews.
 */
public class GeminiReviewSummarizeService implements ReviewSummarizeService {

    private final ReviewSummarizeGateways reviewSummarizeGateways;
    private final ReviewSummarizeAdapter reviewSummarizeAdapter;
    private final AddReview addReviewUseCase;
    private final UpdateReview updateReviewUseCase;
    private final FindSummarizedReview findSummarizedReviewUseCase;

    /**
     * Constructs a GeminiReviewSummarizeService with the given dependencies.
     *
     * @param reviewSummarizeGateways  Gateway for summarizing reviews
     * @param reviewSummarizeAdapter   Adapter for converting reviews to strings and entities
     * @param addReviewUseCase         Use case for adding reviews
     * @param updateReviewUseCase      Use case for updating reviews
     * @param findSummarizedReviewUseCase Use case for finding summarized reviews
     */
    public GeminiReviewSummarizeService(ReviewSummarizeGateways reviewSummarizeGateways,
                                        ReviewSummarizeAdapter reviewSummarizeAdapter,
                                        AddReview addReviewUseCase,
                                        UpdateReview updateReviewUseCase,
                                        FindSummarizedReview findSummarizedReviewUseCase) {
        this.reviewSummarizeGateways = reviewSummarizeGateways;
        this.reviewSummarizeAdapter = reviewSummarizeAdapter;
        this.addReviewUseCase = addReviewUseCase;
        this.updateReviewUseCase = updateReviewUseCase;
        this.findSummarizedReviewUseCase = findSummarizedReviewUseCase;
    }

    /**
     * Creates a summarized review from a list of reviews.
     *
     * @param reviews List of reviews to be summarized
     * @return The summarized review
     * @throws InterruptedException If the summarization process is interrupted
     */
    @Override
    public Review createSummaryFromReviews(List<Review> reviews) throws InterruptedException {
        String reviewContent = reviewSummarizeAdapter.adaptToReviewString(reviews);
        String summarizedContent = reviewSummarizeGateways.summarizeReviews(reviewContent);
        String restaurantId = reviews.get(0).getRestaurantId();

        Review summarizedReview = reviewSummarizeAdapter.adaptToReview(restaurantId, summarizedContent);

        if (findSummarizedReviewUseCase.execute(restaurantId).isPresent()) {
            updateReviewUseCase.execute(summarizedReview);
        } else {
            addReviewUseCase.execute(summarizedReview);
        }

        return summarizedReview;
    }
}
