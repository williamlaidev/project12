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

public class FindRestaurantByLocationTest {

    @Mock
    private RestaurantRepository repository;

    @InjectMocks
    private FindRestaurantByLocation useCase;

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
    void testFindByLocationSuccess() {
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
        Location location = new Location(40.7128, -74.0060);
        when(repository.findByLocation(location)).thenReturn(Optional.of(restaurant));

        Optional<Restaurant> result = useCase.execute(location);

        assertTrue(result.isPresent());
        assertEquals(restaurant, result.get());
    }

    @Test
    void testFindByLocationNotFound() {
        Location location = new Location(40.7128, -74.0060);
        when(repository.findByLocation(location)).thenReturn(Optional.empty());

        Optional<Restaurant> result = useCase.execute(location);

        assertFalse(result.isPresent());
    }
}
