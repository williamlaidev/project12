package use_case.data;

import domain.ReviewRepository;

public class DeleteReviewBySummarized {

    private final ReviewRepository reviewRepository;

    public DeleteReviewBySummarized(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    /**
     * Deletes reviews that are summarized.
     *
     * @param summarized true if reviews to be deleted are summarized, otherwise false
     * @return true if the deletion was successful, otherwise false
     */
    public boolean execute(boolean summarized) {
        return reviewRepository.deleteReviewBySummarized(summarized);
    }
}
