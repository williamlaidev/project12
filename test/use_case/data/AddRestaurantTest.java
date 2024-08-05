package use_case.data;

import entity.DishType;
import entity.Location;
import entity.Restaurant;
import domain.RestaurantRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class AddRestaurantTest {

    @Mock
    private RestaurantRepository repository;

    @InjectMocks
    private AddRestaurant useCase;

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
    void testAddRestaurant() {
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

        useCase.execute(restaurant);

        verify(repository).add(restaurant);
    }
}
