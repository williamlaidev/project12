package use_case;

import domain.ReviewRepository;
import entity.Review;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddReviewTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private AddReview addReview;

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
    void execute() {
        // Arrange
        Review review = new Review("1", "John Doe", "Great food!", false);
        when(reviewRepository.add(review)).thenReturn(review);

        // Act
        Review result = addReview.execute(review);

        // Assert
        assertEquals(review, result);
        verify(reviewRepository, times(1)).add(review);
    }
}
