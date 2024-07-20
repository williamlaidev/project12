package use_case;

import domain.RestaurantRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteRestaurantByNameTest {

    @Mock
    private RestaurantRepository repository;

    @InjectMocks
    private DeleteRestaurantByName deleteRestaurantByName;

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
        // Prepare the name to delete
        String restaurantName = "Test Restaurant";

        // Mock the repository's deleteByName method to return true for successful deletion
        when(repository.deleteByName(restaurantName)).thenReturn(true);

        // Execute the delete operation
        boolean result = deleteRestaurantByName.execute(restaurantName);

        // Verify the result and interaction with the repository
        assertTrue(result);
        verify(repository, times(1)).deleteByName(restaurantName); // Verify interaction with repository
    }

    @Test
    void executeFailure() {
        // Prepare the name to delete
        String restaurantName = "Test Restaurant";

        // Mock the repository's deleteByName method to return false for unsuccessful deletion
        when(repository.deleteByName(restaurantName)).thenReturn(false);

        // Execute the delete operation
        boolean result = deleteRestaurantByName.execute(restaurantName);

        // Verify the result and interaction with the repository
        assertFalse(result);
        verify(repository, times(1)).deleteByName(restaurantName); // Verify interaction with repository
    }
}
