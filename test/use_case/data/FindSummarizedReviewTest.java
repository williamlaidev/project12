package use_case.data;

import domain.ReviewRepository;
import entity.Review;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class FindSummarizedReviewTest {

    @Mock
    private ReviewRepository repository;

    @InjectMocks
    private FindSummarizedReview useCase;

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
    void testFindSummarizedReviewSuccess() {
        Review review = new Review("1", "Gemini", "Summary Content", true);
        when(repository.findSummarizedReview("1")).thenReturn(Optional.of(review));

        Optional<Review> result = useCase.execute("1");

        assertTrue(result.isPresent());
        assertEquals(review, result.get());
    }

    @Test
    void testFindSummarizedReviewNotFound() {
        when(repository.findSummarizedReview("1")).thenReturn(Optional.empty());

        Optional<Review> result = useCase.execute("1");

        assertFalse(result.isPresent());
    }
}
