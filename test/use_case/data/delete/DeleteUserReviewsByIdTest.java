package use_case.data.delete;

import domain.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DeleteUserReviewsByIdTest {

    private DeleteUserReviewsById deleteUserReviewsById;
    private ReviewRepository repository;

    @BeforeEach
    public void setUp() {
        repository = mock(ReviewRepository.class);
        deleteUserReviewsById = new DeleteUserReviewsById(repository);
    }

    @Test
    public void testExecuteSuccess() {
        String restaurantId = "restaurantId1";
        when(repository.deleteUserById(restaurantId)).thenReturn(true);

        boolean result = deleteUserReviewsById.execute(restaurantId);

        verify(repository, times(1)).deleteUserById(restaurantId);
        assertTrue(result);
    }

    @Test
    public void testExecuteFailure() {
        String restaurantId = "restaurantId1";
        when(repository.deleteUserById(restaurantId)).thenReturn(false);

        boolean result = deleteUserReviewsById.execute(restaurantId);

        verify(repository, times(1)).deleteUserById(restaurantId);
        assertFalse(result);
    }
}
