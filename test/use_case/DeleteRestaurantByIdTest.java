package use_case;

import domain.RestaurantRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.data.DeleteRestaurantById;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteRestaurantByIdTest {

    @Mock
    private RestaurantRepository repository;

    @InjectMocks
    private DeleteRestaurantById deleteRestaurantById;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @AfterEach
    void tearDown() {
        // Mockito.reset(repository); // Optionally reset mocks if needed
    }

    @Test
    void executeSuccess() {
        // Prepare the ID to delete
        String restaurantId = "1";

        // Mock the repository's deleteById method to return true for successful deletion
        when(repository.deleteById(restaurantId)).thenReturn(true);

        // Execute the delete operation
        boolean result = deleteRestaurantById.execute(restaurantId);

        // Verify the result and interaction with the repository
        assertTrue(result);
        verify(repository, times(1)).deleteById(restaurantId); // Verify interaction with repository
    }

    @Test
    void executeFailure() {
        // Prepare the ID to delete
        String restaurantId = "1";

        // Mock the repository's deleteById method to return false for unsuccessful deletion
        when(repository.deleteById(restaurantId)).thenReturn(false);

        // Execute the delete operation
        boolean result = deleteRestaurantById.execute(restaurantId);

        // Verify the result and interaction with the repository
        assertFalse(result);
        verify(repository, times(1)).deleteById(restaurantId); // Verify interaction with repository
    }
}
