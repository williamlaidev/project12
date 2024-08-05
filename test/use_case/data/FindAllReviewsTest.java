package use_case.data;

import domain.ReviewRepository;
import entity.Review;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class FindAllReviewsTest {

    @Mock
    private ReviewRepository repository;

    @InjectMocks
    private FindAllReviews useCase;

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
    void testFindAllReviewsSuccess() {
        Review review1 = new Review("1", "Author 1", "Content 1", false);
        Review review2 = new Review("2", "Author 2", "Content 2", true);
        List<Review> reviews = List.of(review1, review2);
        when(repository.findAll()).thenReturn(Optional.of(reviews));

        Optional<List<Review>> result = useCase.execute();

        assertTrue(result.isPresent());
        assertEquals(reviews, result.get());
    }

    @Test
    void testFindAllReviewsEmpty() {
        when(repository.findAll()).thenReturn(Optional.empty());

        Optional<List<Review>> result = useCase.execute();

        assertFalse(result.isPresent());
    }
}
