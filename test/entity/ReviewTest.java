package entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewTest {
    private Review review;

    @BeforeEach
    public void setUp() {
        review = new Review("ChIJZejXHbA0K4gRVuq7QVSGyNI", "Kera", "Great place!", false);
    }

    @AfterEach
    public void tearDown() {
        review = null;
    }

    @Test
    public void testValidReviewCreation() {
        assertNotNull(review);
        assertEquals("ChIJZejXHbA0K4gRVuq7QVSGyNI", review.getRestaurantId());
        assertEquals("Kera", review.getAuthor());
        assertEquals("Great place!", review.getContent());
        assertFalse(review.isSummarized());
    }

    @Test
    public void testInvalidRestaurantId() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() {
                new Review("", "Kera", "Great place!", false);
            }
        });
        assertEquals("Restaurant ID cannot be null or empty.", thrown.getMessage());

        thrown = assertThrows(IllegalArgumentException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() {
                new Review(null, "Kera", "Great place!", false);
            }
        });
        assertEquals("Restaurant ID cannot be null or empty.", thrown.getMessage());
    }

    @Test
    public void testInvalidAuthor() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() {
                new Review("ChIJZejXHbA0K4gRVuq7QVSGyNI", "", "Great place!", false);
            }
        });
        assertEquals("Author cannot be null or empty.", thrown.getMessage());

        thrown = assertThrows(IllegalArgumentException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() {
                new Review("ChIJZejXHbA0K4gRVuq7QVSGyNI", null, "Great place!", false);
            }
        });
        assertEquals("Author cannot be null or empty.", thrown.getMessage());
    }

    @Test
    public void testInvalidContent() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() {
                new Review("ChIJZejXHbA0K4gRVuq7QVSGyNI", "Kera", "", false);
            }
        });
        assertEquals("Content cannot be null or empty.", thrown.getMessage());

        thrown = assertThrows(IllegalArgumentException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() {
                new Review("ChIJZejXHbA0K4gRVuq7QVSGyNI", "Kera", null, false);
            }
        });
        assertEquals("Content cannot be null or empty.", thrown.getMessage());
    }

    @Test
    public void testSetRestaurantId() {
        review.setRestaurantId("ChIJYwMaga8QK4gRs-UgftpmL20");
        assertEquals("ChIJYwMaga8QK4gRs-UgftpmL20", review.getRestaurantId());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() {
                review.setRestaurantId("");
            }
        });
        assertEquals("Restaurant ID cannot be null or empty.", thrown.getMessage());
    }

    @Test
    public void testSetAuthor() {
        review.setAuthor("William");
        assertEquals("William", review.getAuthor());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() {
                review.setAuthor("");
            }
        });
        assertEquals("Author cannot be null or empty.", thrown.getMessage());
    }

    @Test
    public void testSetContent() {
        review.setContent("Not bad");
        assertEquals("Not bad", review.getContent());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() {
                review.setContent("");
            }
        });
        assertEquals("Content cannot be null or empty.", thrown.getMessage());
    }

    @Test
    public void testSetSummarized() {
        review.setSummarized(true);
        assertTrue(review.isSummarized());

        review.setSummarized(false);
        assertFalse(review.isSummarized());
    }

    @Test
    public void testToString() {
        String expectedString = "Review{restaurantId='ChIJZejXHbA0K4gRVuq7QVSGyNI', author='Kera', content='Great place!', isSummarized=false}";
        assertEquals(expectedString, review.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        Review review1 = new Review("ChIJZejXHbA0K4gRVuq7QVSGyNI", "Kera", "Great place!", false);
        Review review2 = new Review("ChIJZejXHbA0K4gRVuq7QVSGyNI", "Kera", "Great place!", false);
        Review review3 = new Review("ChIJYwMaga8QK4gRs-UgftpmL20", "William", "Not bad", false);

        assertEquals(review1, review2);
        assertNotEquals(review1, review3);
        assertEquals(review1.hashCode(), review2.hashCode());
        assertNotEquals(review1.hashCode(), review3.hashCode());
    }
}
