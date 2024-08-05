package use_case.data;

import domain.RestaurantRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class DeleteRestaurantTest {

    @Mock
    private RestaurantRepository repository;

    @InjectMocks
    private DeleteRestaurant useCase;

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
    void testDeleteRestaurant() {
        String restaurantId = "1";

        useCase.execute(restaurantId);

        verify(repository).delete(restaurantId);
    }
}
