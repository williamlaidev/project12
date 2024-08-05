package use_case.data;

import domain.ReviewRepository;
import entity.Review;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class AddReviewTest {

    @Mock
    private ReviewRepository repository;

    @InjectMocks
    private AddReview useCase;

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
    void testAddReview() {
        Review review = new Review("1", "Author", "Content", false);

        useCase.execute(review);

        verify(repository).save(review);
    }
}
