package use_case.data.delete;

import domain.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClearAllReviewsTest {

    private ClearAllReviews clearAllReviews;
    private ReviewRepository repository;

    @BeforeEach
    public void setUp() {
        repository = mock(ReviewRepository.class);
        clearAllReviews = new ClearAllReviews(repository);
    }

    @Test
    public void testExecuteSuccess() {
        when(repository.clearAll()).thenReturn(true);

        boolean result = clearAllReviews.execute();

        verify(repository, times(1)).clearAll();
        assertTrue(result);
    }

    @Test
    public void testExecuteFailure() {
        when(repository.clearAll()).thenReturn(false);

        boolean result = clearAllReviews.execute();

        verify(repository, times(1)).clearAll();
        assertFalse(result);
    }
}
