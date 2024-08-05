package use_case.data;

import domain.ReviewRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class DeleteSummarizedReviewTest {

    @Mock
    private ReviewRepository repository;

    @InjectMocks
    private DeleteSummarizedReview useCase;

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
    void testDeleteSummarizedReview() {
        String restaurantId = "1";

        useCase.execute(restaurantId);

        verify(repository).deleteSummarizedReview(restaurantId);
    }
}
