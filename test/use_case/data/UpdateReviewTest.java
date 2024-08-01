package use_case.data;

import domain.ReviewRepository;
import entity.Review;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class UpdateReviewTest {

    @Mock
    private ReviewRepository repository;

    @InjectMocks
    private UpdateReview useCase;

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
    void testUpdateReview() {
        Review review = new Review(
                "1",
                "Author",
                "Updated Content",
                false
        );

        useCase.execute(review);

        verify(repository).update(review);
    }
}
