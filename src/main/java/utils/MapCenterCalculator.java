package utils;

/**
 * Utility class for calculating the center coordinates of a map.
 */
public class MapCenterCalculator {

    /**
     * Calculates the center coordinates of a map.
     *
     * @param width  the width of the map in pixels
     * @param height the height of the map in pixels
     * @return an array with the x and y coordinates of the center
     */
    public static int[] calculateMapCenter(int width, int height) {
        int centerX = width / 2;
        int centerY = height / 2;
        return new int[]{centerX, centerY};
    }
}
