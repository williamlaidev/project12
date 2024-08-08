package utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MapCenterCalculatorTest {

    @Test
    public void testCalculateMapCenter() {
        int width = 200;
        int height = 200;
        int[] expectedCenter = {100, 100};
        assertArrayEquals(expectedCenter, MapCenterCalculator.calculateMapCenter(width, height));
    }
}
