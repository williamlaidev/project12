package use_case.data.delete;

import domain.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DeleteSummarizedReviewByIdTest {

    private DeleteSummarizedReviewById deleteSummarizedReviewById;
    private ReviewRepository repository;

    @BeforeEach
    public void setUp() {
        repository = mock(ReviewRepository.class);
        deleteSummarizedReviewById = new DeleteSummarizedReviewById(repository);
    }

    @Test
    public void testExecuteSuccess() {
        String restaurantId = "restaurantId1";
        when(repository.deleteSummarizedById(restaurantId)).thenReturn(true);

        boolean result = deleteSummarizedReviewById.execute(restaurantId);

        verify(repository, times(1)).deleteSummarizedById(restaurantId);
        assertTrue(result);
    }

    @Test
    public void testExecuteFailure() {
        String restaurantId = "restaurantId1";
        when(repository.deleteSummarizedById(restaurantId)).thenReturn(false);

        boolean result = deleteSummarizedReviewById.execute(restaurantId);

        verify(repository, times(1)).deleteSummarizedById(restaurantId);
        assertFalse(result);
    }
}
