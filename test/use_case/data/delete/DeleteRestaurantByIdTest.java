package use_case.data.delete;

import domain.RestaurantRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

class DeleteRestaurantByIdTest {

    @Test
    void testExecuteSuccess() {
        // Arrange
        RestaurantRepository mockRepository = mock(RestaurantRepository.class);
        when(mockRepository.deleteById("1")).thenReturn(true);

        DeleteRestaurantById deleteRestaurantById = new DeleteRestaurantById(mockRepository);

        // Act
        boolean result = deleteRestaurantById.execute("1");

        // Assert
        assertTrue(result);
        verify(mockRepository, times(1)).deleteById("1");
    }

    @Test
    void testExecuteFailure() {
        // Arrange
        RestaurantRepository mockRepository = mock(RestaurantRepository.class);
        when(mockRepository.deleteById("1")).thenReturn(false);

        DeleteRestaurantById deleteRestaurantById = new DeleteRestaurantById(mockRepository);

        // Act
        boolean result = deleteRestaurantById.execute("1");

        // Assert
        assertFalse(result);
        verify(mockRepository, times(1)).deleteById("1");
    }
}
