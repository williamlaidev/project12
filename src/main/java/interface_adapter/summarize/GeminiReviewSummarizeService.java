package interface_adapter.summarize;

import domain.ReviewSummarizeService;
import entity.review.Review;
import use_case.data.create.AddReview;
import use_case.data.read.FindSummarizedReview;
import use_case.data.update.UpdateReview;

import java.util.List;

public class GeminiReviewSummarizeService implements ReviewSummarizeService {
    private final ReviewSummarizeGateways reviewSummarizeGateways;
    private final ReviewSummarizeAdapter reviewSummarizeAdapter;
    private final AddReview addReviewUseCase;
    private final UpdateReview updateReviewUseCase;
    private final FindSummarizedReview findSummarizedReviewUseCase;

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
