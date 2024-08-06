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
        Review review = new Review("1", "Kera", "The food was amazing.", false);
        OperationResult expected = successFactory.create("Review added successfully");
        when(addReviewUseCase.execute(review)).thenReturn(expected);

        OperationResult result = controller.addReview("1", "Kera", "The food was amazing.", false);
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    public void testUpdateReview() {
        Review review = new Review("1", "Kera", "The food was amazing.", false);
        OperationResult expected = successFactory.create("Review updated successfully");
        when(updateReviewUseCase.execute(review)).thenReturn(expected);

        OperationResult result = controller.updateReview("1", "Kera", "The food was amazing.");
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    public void testDeleteUserReviews() {
        String restaurantId = "1";
        OperationResult expected = successFactory.create("User reviews deleted successfully");
        when(deleteUserReviewsByIdUseCase.execute(restaurantId)).thenReturn(true);

        OperationResult result = controller.deleteUserReviews(restaurantId);
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    public void testDeleteSummarizedReviews() {
        String restaurantId = "1";
        OperationResult expected = successFactory.create("Summarized reviews deleted successfully");
        when(deleteSummarizedReviewByIdUseCase.execute(restaurantId)).thenReturn(true);

        OperationResult result = controller.deleteSummarizedReviews(restaurantId);
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    public void testDeleteAllReviews() {
        String restaurantId = "1";
        OperationResult expected = successFactory.create("All reviews deleted successfully");
        when(deleteAllReviewsByIdUseCase.execute(restaurantId)).thenReturn(true);

        OperationResult result = controller.deleteAllReviews(restaurantId);
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    public void testClearAllReviews() {
        OperationResult expected = successFactory.create("All reviews cleared successfully");
        when(clearAllReviewsUseCase.execute()).thenReturn(true);

        OperationResult result = controller.clearAllReviews();
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    public void testFindUserReviews() {
        String restaurantId = "1";
        List<Review> expectedReviews = List.of(new Review(restaurantId, "Kera", "The food was amazing.", false));
        when(findUserReviewsUseCase.execute(restaurantId)).thenReturn(expectedReviews);

        List<Review> result = controller.findUserReviews(restaurantId);
        assertEquals(expectedReviews, result);
    }

    @Test
    public void testFindSummarizedReview() {
        String restaurantId = "1";
        Optional<Review> expectedReview = Optional.of(new Review(restaurantId, "Gemini", "Summary review.", true));
        when(findSummarizedReviewUseCase.execute(restaurantId)).thenReturn(expectedReview);

        Optional<Review> result = controller.findSummarizedReview(restaurantId);
        assertEquals(expectedReview, result);
    }

    @Test
    public void testFindAllReviews() {
        List<Review> expectedReviews = List.of(new Review("1", "Kera", "The food was amazing.", false));
        when(findAllReviewsUseCase.execute()).thenReturn(expectedReviews);

        List<Review> result = controller.findAllReviews();
        assertEquals(expectedReviews, result);
    }

    // 추가된 실패 테스트 메서드들

    @Test
    public void testAddReviewFailure() {
        Review review = new Review("1", "Kera", "The food was amazing.", false);
        OperationResult expected = failureFactory.create("Failed to add review");
        when(addReviewUseCase.execute(review)).thenThrow(new RuntimeException("Error"));

        OperationResult result = controller.addReview("1", "Kera", "The food was amazing.", false);
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    public void testUpdateReviewFailure() {
        Review review = new Review("1", "Kera", "The food was amazing.", false);
        OperationResult expected = failureFactory.create("Failed to update review");
        when(updateReviewUseCase.execute(review)).thenThrow(new RuntimeException("Error"));

        OperationResult result = controller.updateReview("1", "Kera", "The food was amazing.");
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    public void testDeleteUserReviewsFailure() {
        String restaurantId = "1";
        OperationResult expected = failureFactory.create("Failed to delete user reviews");
        when(deleteUserReviewsByIdUseCase.execute(restaurantId)).thenThrow(new RuntimeException("Error"));

        OperationResult result = controller.deleteUserReviews(restaurantId);
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    public void testDeleteSummarizedReviewsFailure() {
        String restaurantId = "1";
        OperationResult expected = failureFactory.create("Failed to delete summarized reviews");
        when(deleteSummarizedReviewByIdUseCase.execute(restaurantId)).thenThrow(new RuntimeException("Error"));

        OperationResult result = controller.deleteSummarizedReviews(restaurantId);
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    public void testDeleteAllReviewsFailure() {
        String restaurantId = "1";
        OperationResult expected = failureFactory.create("Failed to delete all reviews");
        when(deleteAllReviewsByIdUseCase.execute(restaurantId)).thenThrow(new RuntimeException("Error"));

        OperationResult result = controller.deleteAllReviews(restaurantId);
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    public void testClearAllReviewsFailure() {
        OperationResult expected = failureFactory.create("Failed to clear all reviews");
        when(clearAllReviewsUseCase.execute()).thenThrow(new RuntimeException("Error"));

        OperationResult result = controller.clearAllReviews();
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    public void testFindUserReviewsFailure() {
        String restaurantId = "1";
        when(findUserReviewsUseCase.execute(restaurantId)).thenThrow(new RuntimeException("Error"));

        List<Review> result = controller.findUserReviews(restaurantId);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindSummarizedReviewFailure() {
        String restaurantId = "1";
        when(findSummarizedReviewUseCase.execute(restaurantId)).thenThrow(new RuntimeException("Error"));

        Optional<Review> result = controller.findSummarizedReview(restaurantId);
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindAllReviewsFailure() {
        when(findAllReviewsUseCase.execute()).thenThrow(new RuntimeException("Error"));

        List<Review> result = controller.findAllReviews();
        assertTrue(result.isEmpty());
    }
}
