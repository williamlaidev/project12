package use_case.data.create;

import domain.ReviewRepository;
import entity.operation_result.OperationResult;
import entity.operation_result.OperationResultSuccessFactory;
import entity.operation_result.OperationResultFailureFactory;
import entity.review.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddReviewTest {

    private AddReview addReview;
    private ReviewRepository repository;
    private OperationResultSuccessFactory successFactory;
    private OperationResultFailureFactory failureFactory;

    @BeforeEach
    public void setUp() {
        repository = mock(ReviewRepository.class);
        addReview = new AddReview(repository);
        successFactory = new OperationResultSuccessFactory();
        failureFactory = new OperationResultFailureFactory();
    }

    @Test
    public void testExecuteSuccess() {
        // Create a sample review
        Review review = new Review("restaurantId1", "author1", "This is a great place!", false);

        // Mock the repository to return a success operation result
        OperationResult expected = successFactory.create("Review added successfully");
        when(repository.add(review)).thenReturn(expected);

        // Execute the use case
        OperationResult result = addReview.execute(review);

        // Verify the interactions and assert results
        verify(repository, times(1)).add(review);
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    public void testExecuteFailure() {
        // Create a sample review
        Review review = new Review("restaurantId1", "author1", "This is a great place!", false);

        // Mock the repository to return a failure operation result
        OperationResult expected = failureFactory.create("Failed to add review");
        when(repository.add(review)).thenReturn(expected);

        // Execute the use case
        OperationResult result = addReview.execute(review);

        // Verify the interactions and assert results
        verify(repository, times(1)).add(review);
        assertEquals(expected.getMessage(), result.getMessage());
    }
}
