package use_case.data.delete;

import domain.RestaurantRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

class ClearAllRestaurantsTest {

    @Test
    void testExecuteSuccess() {
        // Arrange
        RestaurantRepository mockRepository = mock(RestaurantRepository.class);
        when(mockRepository.clearAll()).thenReturn(true);

        ClearAllRestaurants clearAllRestaurants = new ClearAllRestaurants(mockRepository);

        // Act
        boolean result = clearAllRestaurants.execute();

        // Assert
        assertTrue(result);
        verify(mockRepository, times(1)).clearAll();
    }

    @Test
    void testExecuteFailure() {
        // Arrange
        RestaurantRepository mockRepository = mock(RestaurantRepository.class);
        when(mockRepository.clearAll()).thenReturn(false);

        ClearAllRestaurants clearAllRestaurants = new ClearAllRestaurants(mockRepository);

        // Act
        boolean result = clearAllRestaurants.execute();

        // Assert
        assertFalse(result);
        verify(mockRepository, times(1)).clearAll();
    }
}
