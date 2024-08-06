package interface_adapter.data;

import entity.operation_result.OperationResult;
import entity.operation_result.OperationResultFailureFactory;
import entity.operation_result.OperationResultSuccessFactory;
import entity.review.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReviewControllerTest {

    private ReviewController controller;
    private AddReview addReviewUseCase;
    private UpdateReview updateReviewUseCase;
    private DeleteUserReviewsById deleteUserReviewsByIdUseCase;
    private DeleteSummarizedReviewById deleteSummarizedReviewByIdUseCase;
    private DeleteAllReviewsById deleteAllReviewsByIdUseCase;
    private ClearAllReviews clearAllReviewsUseCase;
    private FindAllReviews findAllReviewsUseCase;
    private FindUserReviews findUserReviewsUseCase;
    private FindSummarizedReview findSummarizedReviewUseCase;
    private OperationResultSuccessFactory successFactory;
    private OperationResultFailureFactory failureFactory;

    @BeforeEach
    public void setUp() {
        addReviewUseCase = mock(AddReview.class);
        updateReviewUseCase = mock(UpdateReview.class);
        deleteUserReviewsByIdUseCase = mock(DeleteUserReviewsById.class);
        deleteSummarizedReviewByIdUseCase = mock(DeleteSummarizedReviewById.class);
        deleteAllReviewsByIdUseCase = mock(DeleteAllReviewsById.class);
        clearAllReviewsUseCase = mock(ClearAllReviews.class);
        findAllReviewsUseCase = mock(FindAllReviews.class);
        findUserReviewsUseCase = mock(FindUserReviews.class);
        findSummarizedReviewUseCase = mock(FindSummarizedReview.class);
        successFactory = new OperationResultSuccessFactory();
        failureFactory = new OperationResultFailureFactory();

        controller = new ReviewController(
                addReviewUseCase,
                updateReviewUseCase,
                deleteUserReviewsByIdUseCase,
                deleteSummarizedReviewByIdUseCase,
                deleteAllReviewsByIdUseCase,
                clearAllReviewsUseCase,
                findAllReviewsUseCase,
                findUserReviewsUseCase,
                findSummarizedReviewUseCase,
                successFactory,
                failureFactory
        );
    }

    @Test
    public void testAddReview() {
        Review review = new Review("id1", "Kera", "Excellent food and service!", false);
        OperationResult expected = successFactory.create("Review added successfully");
        when(addReviewUseCase.execute(review)).thenReturn(expected);

        OperationResult result = controller.addReview("id1", "Kera", "Excellent food and service!", false);
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    public void testUpdateReview() {
        Review review = new Review("id1", "Kera", "Updated review with new details.", false);
        OperationResult expected = successFactory.create("Review updated successfully");
        when(updateReviewUseCase.execute(review)).thenReturn(expected);

        OperationResult result = controller.updateReview("id1", "Kera", "Updated review with new details.");
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    public void testDeleteUserReviews() {
        when(deleteUserReviewsByIdUseCase.execute("id1")).thenReturn(true);
        OperationResult expected = successFactory.create("User reviews deleted successfully");

        OperationResult result = controller.deleteUserReviews("id1");
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    public void testDeleteSummarizedReviews() {
        when(deleteSummarizedReviewByIdUseCase.execute("id1")).thenReturn(true);
        OperationResult expected = successFactory.create("Summarized reviews deleted successfully");

        OperationResult result = controller.deleteSummarizedReviews("id1");
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    public void testDeleteAllReviews() {
        when(deleteAllReviewsByIdUseCase.execute("id1")).thenReturn(true);
        OperationResult expected = successFactory.create("All reviews deleted successfully");

        OperationResult result = controller.deleteAllReviews("id1");
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    public void testClearAllReviews() {
        when(clearAllReviewsUseCase.execute()).thenReturn(true);
        OperationResult expected = successFactory.create("All reviews cleared successfully");

        OperationResult result = controller.clearAllReviews();
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    public void testFindUserReviews() {
        List<Review> expectedReviews = List.of(new Review("id1", "Kera", "The ambiance was perfect.", false));
        when(findUserReviewsUseCase.execute("id1")).thenReturn(expectedReviews);

        List<Review> result = controller.findUserReviews("id1");
        assertEquals(expectedReviews, result);
    }

    @Test
    public void testFindSummarizedReview() {
        Review expectedReview = new Review("id1", "Gemini", "Summary of multiple reviews.", true);
        when(findSummarizedReviewUseCase.execute("id1")).thenReturn(Optional.of(expectedReview));

        Optional<Review> result = controller.findSummarizedReview("id1");
        assertTrue(result.isPresent());
        assertEquals(expectedReview, result.get());
    }

    @Test
    public void testFindAllReviews() {
        List<Review> expectedReviews = List.of(new Review("id1", "Kera", "Loved the experience!", false));
        when(findAllReviewsUseCase.execute()).thenReturn(expectedReviews);

        List<Review> result = controller.findAllReviews();
        assertEquals(expectedReviews, result);
    }
}
