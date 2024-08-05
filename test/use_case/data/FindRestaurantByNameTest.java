package use_case.data;

import entity.DishType;
import entity.Location;
import entity.Restaurant;
import domain.RestaurantRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class FindRestaurantByNameTest {

    @Mock
    private RestaurantRepository repository;

    @InjectMocks
    private FindRestaurantByName useCase;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void testFindByNameSuccess() {
        Restaurant restaurant = new Restaurant(
                "1",
                "Test Restaurant",
                new Location(40.7128, -74.0060),
                "Test Address",
                DishType.AMERICAN,
                4.5,
                null,
                null,
                null
        );
        when(repository.findByName("Test Restaurant")).thenReturn(Optional.of(restaurant));

        Optional<Restaurant> result = useCase.execute("Test Restaurant");

        assertTrue(result.isPresent());
        assertEquals(restaurant, result.get());
    }

    @Test
    void testFindByNameNotFound() {
        when(repository.findByName("Nonexistent Restaurant")).thenReturn(Optional.empty());

        Optional<Restaurant> result = useCase.execute("Nonexistent Restaurant");

        assertFalse(result.isPresent());
    }
}
