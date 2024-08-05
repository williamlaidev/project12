package use_case.data;

import entity.DishType;
import entity.Location;
import entity.Restaurant;
import domain.RestaurantRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class FindAllRestaurantsTest {

    @Mock
    private RestaurantRepository repository;

    @InjectMocks
    private FindAllRestaurants useCase;

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
    void testFindAllSuccess() {
        Restaurant restaurant1 = new Restaurant(
                "1",
                "Restaurant 1",
                new Location(40.7128, -74.0060),
                "Address 1",
                DishType.AMERICAN,
                4.5,
                null,
                null,
                null
        );
        Restaurant restaurant2 = new Restaurant(
                "2",
                "Restaurant 2",
                new Location(34.0522, -118.2437),
                "Address 2",
                DishType.ITALIAN,
                4.0,
                null,
                null,
                null
        );
        List<Restaurant> restaurants = List.of(restaurant1, restaurant2);
        when(repository.findAll()).thenReturn(Optional.of(restaurants));

        Optional<List<Restaurant>> result = useCase.execute();

        assertTrue(result.isPresent());
        assertEquals(restaurants, result.get());
    }

    @Test
    void testFindAllEmpty() {
        when(repository.findAll()).thenReturn(Optional.empty());

        Optional<List<Restaurant>> result = useCase.execute();

        assertFalse(result.isPresent());
    }
}
