package use_case.data.delete;

import domain.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClearAllRestaurantsTest {

    private ClearAllRestaurants clearAllRestaurants;
    private RestaurantRepository repository;

    @BeforeEach
    public void setUp() {
        repository = mock(RestaurantRepository.class);
        clearAllRestaurants = new ClearAllRestaurants(repository);
    }

    @Test
    public void testExecuteSuccess() {
        when(repository.clearAll()).thenReturn(true);

        boolean result = clearAllRestaurants.execute();

        verify(repository, times(1)).clearAll();
        assertTrue(result);
    }

    @Test
    public void testExecuteFailure() {
        when(repository.clearAll()).thenReturn(false);

        boolean result = clearAllRestaurants.execute();

        verify(repository, times(1)).clearAll();
        assertFalse(result);
    }
}
