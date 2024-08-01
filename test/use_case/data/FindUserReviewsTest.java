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

public class FindUserReviewsTest {

    @Mock
    private ReviewRepository repository;

    @InjectMocks
    private FindUserReviews useCase;

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
    void testFindUserReviewsSuccess() {
        List<Review> reviews = List.of(new Review("1", "Author", "Content", false));
        when(repository.findUserReviews("1")).thenReturn(Optional.of(reviews));

        Optional<List<Review>> result = useCase.execute("1");

        assertTrue(result.isPresent());
        assertEquals(reviews, result.get());
    }

    @Test
    void testFindUserReviewsNotFound() {
        when(repository.findUserReviews("1")).thenReturn(Optional.empty());

        Optional<List<Review>> result = useCase.execute("1");

        assertFalse(result.isPresent());
    }
}
