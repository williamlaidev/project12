package interface_adapter.data;

import entity.operation_result.OperationResult;
import entity.review.Review;
import entity.operation_result.OperationResultSuccessFactory;
import entity.operation_result.OperationResultFailureFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import use_case.data.create.AddReview;
import use_case.data.delete.ClearAllReviews;
import use_case.data.delete.DeleteAllReviewsById;
import use_case.data.delete.DeleteSummarizedReviewById;
import use_case.data.delete.DeleteUserReviewsById;
import use_case.data.read.FindAllReviews;
import use_case.data.read.FindSummarizedReview;
import use_case.data.read.FindUserReviews;
import use_case.data.update.UpdateReview;

import java.util.List;
import java.util.Optional;

public class ReviewController {
    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

    private final AddReview addReviewUseCase;
    private final UpdateReview updateReviewUseCase;
    private final DeleteUserReviewsById deleteUserReviewsByIdUseCase;
    private final DeleteSummarizedReviewById deleteSummarizedReviewByIdUseCase;
    private final DeleteAllReviewsById deleteAllReviewsByIdUseCase;
    private final ClearAllReviews clearAllReviewsUseCase;
    private final FindAllReviews findAllReviewsUseCase;
    private final FindUserReviews findUserReviewsUseCase;
    private final FindSummarizedReview findSummarizedReviewUseCase;

    private final OperationResultSuccessFactory successFactory;
    private final OperationResultFailureFactory failureFactory;

    public ReviewController(AddReview addReviewUseCase,
                            UpdateReview updateReviewUseCase,
                            DeleteUserReviewsById deleteUserReviewsByIdUseCase,
                            DeleteSummarizedReviewById deleteSummarizedReviewByIdUseCase,
                            DeleteAllReviewsById deleteAllReviewsByIdUseCase,
                            ClearAllReviews clearAllReviewsUseCase,
                            FindAllReviews findAllReviewsUseCase,
                            FindUserReviews findUserReviewsUseCase,
                            FindSummarizedReview findSummarizedReviewUseCase,
                            OperationResultSuccessFactory successFactory,
                            OperationResultFailureFactory failureFactory) {
        this.addReviewUseCase = addReviewUseCase;
        this.updateReviewUseCase = updateReviewUseCase;
        this.deleteUserReviewsByIdUseCase = deleteUserReviewsByIdUseCase;
        this.deleteSummarizedReviewByIdUseCase = deleteSummarizedReviewByIdUseCase;
        this.deleteAllReviewsByIdUseCase = deleteAllReviewsByIdUseCase;
        this.clearAllReviewsUseCase = clearAllReviewsUseCase;
        this.findAllReviewsUseCase = findAllReviewsUseCase;
        this.findUserReviewsUseCase = findUserReviewsUseCase;
        this.findSummarizedReviewUseCase = findSummarizedReviewUseCase;
        this.successFactory = successFactory;
        this.failureFactory = failureFactory;
    }

    public OperationResult addReview(String restaurantId, String author, String content, boolean isSummarized) {
        Review review = new Review(restaurantId, author, content, isSummarized);
        try {
            return addReviewUseCase.execute(review);
        } catch (Exception e) {
            logger.error("Error adding review", e);
            return failureFactory.create("Failed to add review");
        }
    }

    public OperationResult updateReview(String restaurantId, String author, String content) {
        Review review = new Review(restaurantId, author, content, false); // assuming isSummarized as false for update
        try {
            return updateReviewUseCase.execute(review);
        } catch (Exception e) {
            logger.error("Error updating review", e);
            return failureFactory.create("Failed to update review");
        }
    }

    public OperationResult deleteUserReviews(String restaurantId) {
        try {
            boolean result = deleteUserReviewsByIdUseCase.execute(restaurantId);
            return result ? successFactory.create("User reviews deleted successfully")
                    : failureFactory.create("Failed to delete user reviews");
        } catch (Exception e) {
            logger.error("Error deleting user reviews", e);
            return failureFactory.create("Failed to delete user reviews");
        }
    }

    public OperationResult deleteSummarizedReviews(String restaurantId) {
        try {
            boolean result = deleteSummarizedReviewByIdUseCase.execute(restaurantId);
            return result ? successFactory.create("Summarized reviews deleted successfully")
                    : failureFactory.create("Failed to delete summarized reviews");
        } catch (Exception e) {
            logger.error("Error deleting summarized reviews", e);
            return failureFactory.create("Failed to delete summarized reviews");
        }
    }

    public OperationResult deleteAllReviews(String restaurantId) {
        try {
            boolean result = deleteAllReviewsByIdUseCase.execute(restaurantId);
            return result ? successFactory.create("All reviews deleted successfully")
                    : failureFactory.create("Failed to delete all reviews");
        } catch (Exception e) {
            logger.error("Error deleting all reviews by ID", e);
            return failureFactory.create("Failed to delete all reviews");
        }
    }

    public OperationResult clearAllReviews() {
        try {
            boolean result = clearAllReviewsUseCase.execute();
            return result ? successFactory.create("All reviews cleared successfully")
                    : failureFactory.create("Failed to clear all reviews");
        } catch (Exception e) {
            logger.error("Error clearing all reviews", e);
            return failureFactory.create("Failed to clear all reviews");
        }
    }

    public List<Review> findUserReviews(String restaurantId) {
        try {
            return findUserReviewsUseCase.execute(restaurantId);
        } catch (Exception e) {
            logger.error("Error finding user reviews", e);
            return List.of(); // or handle as appropriate
        }
    }

    public Optional<Review> findSummarizedReview(String restaurantId) {
        try {
            return findSummarizedReviewUseCase.execute(restaurantId);
        } catch (Exception e) {
            logger.error("Error finding summarized review", e);
            return Optional.empty(); // or handle as appropriate
        }
    }

    public List<Review> findAllReviews() {
        try {
            return findAllReviewsUseCase.execute();
        } catch (Exception e) {
            logger.error("Error finding all reviews", e);
            return List.of(); // or handle as appropriate
        }
    }
}
