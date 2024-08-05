package use_case.data;

import domain.ReviewRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class DeleteUserReviewsTest {

    @Mock
    private ReviewRepository repository;

    @InjectMocks
    private DeleteUserReviews useCase;

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
    void testDeleteUserReviews() {
        String restaurantId = "1";

        useCase.execute(restaurantId);

        verify(repository).deleteUserReviews(restaurantId);
    }
}
