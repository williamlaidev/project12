package test.entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.java.entity.Map;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MapTest {

    private Map map;

    @BeforeEach
    public void setUp() {
        List<Integer> restaurantIds = Arrays.asList(1, 2, 3);
        map = new Map(40.7128, -74.0060, 12, restaurantIds);
    }

    @Test
    public void testGetters() {
        assertEquals(40.7128, map.getCurrentLatitude(), 0.001);
        assertEquals(-74.0060, map.getCurrentLongitude(), 0.001);
        assertEquals(12, map.getZoomLevel());
        assertEquals(Arrays.asList(1, 2, 3), map.getDisplayedRestaurantIds());
    }

    @Test
    public void testSetters() {
        map.setCurrentLatitude(38.8951);
        map.setCurrentLongitude(-77.0367);
        map.setZoomLevel(15);
        List<Integer> newRestaurantIds = Arrays.asList(4, 5, 6);
        map.setDisplayedRestaurantIds(newRestaurantIds);

        assertEquals(38.8951, map.getCurrentLatitude(), 0.001);
        assertEquals(-77.0367, map.getCurrentLongitude(), 0.001);
        assertEquals(15, map.getZoomLevel());
        assertEquals(Arrays.asList(4, 5, 6), map.getDisplayedRestaurantIds());
    }

    @Test
    public void testToString() {
        String expectedString = "Map{" +
                "currentLatitude=40.7128, " +
                "currentLongitude=-74.006, " +
                "zoomLevel=12, " +
                "displayedRestaurantIds=[1, 2, 3]}";

        assertEquals(expectedString, map.toString());
    }
}
