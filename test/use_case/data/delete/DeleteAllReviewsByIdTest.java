package use_case.data.delete;

import domain.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DeleteAllReviewsByIdTest {

    private DeleteAllReviewsById deleteAllReviewsById;
    private ReviewRepository repository;

    @BeforeEach
    public void setUp() {
        repository = mock(ReviewRepository.class);
        deleteAllReviewsById = new DeleteAllReviewsById(repository);
    }

    @Test
    public void testExecuteSuccess() {
        String restaurantId = "restaurantId1";
        when(repository.deleteAllById(restaurantId)).thenReturn(true);

        boolean result = deleteAllReviewsById.execute(restaurantId);

        verify(repository, times(1)).deleteAllById(restaurantId);
        assertTrue(result);
    }

    @Test
    public void testExecuteFailure() {
        String restaurantId = "restaurantId1";
        when(repository.deleteAllById(restaurantId)).thenReturn(false);

        boolean result = deleteAllReviewsById.execute(restaurantId);

        verify(repository, times(1)).deleteAllById(restaurantId);
        assertFalse(result);
    }
}
