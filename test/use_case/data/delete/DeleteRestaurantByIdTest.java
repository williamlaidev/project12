package use_case.data.delete;

import domain.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DeleteRestaurantByIdTest {

    private DeleteRestaurantById deleteRestaurantById;
    private RestaurantRepository repository;

    @BeforeEach
    public void setUp() {
        repository = mock(RestaurantRepository.class);
        deleteRestaurantById = new DeleteRestaurantById(repository);
    }

    @Test
    public void testExecuteSuccess() {
        String restaurantId = "restaurantId1";
        when(repository.deleteById(restaurantId)).thenReturn(true);

        boolean result = deleteRestaurantById.execute(restaurantId);

        verify(repository, times(1)).deleteById(restaurantId);
        assertTrue(result);
    }

    @Test
    public void testExecuteFailure() {
        String restaurantId = "restaurantId1";
        when(repository.deleteById(restaurantId)).thenReturn(false);

        boolean result = deleteRestaurantById.execute(restaurantId);

        verify(repository, times(1)).deleteById(restaurantId);
        assertFalse(result);
    }
}
