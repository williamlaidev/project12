package utils;

public class MapCenterCalculator {

    /**
     * Calculates the center coordinates for a given map size in pixels.
     *
     * @param width  The width of the map in pixels.
     * @param height The height of the map in pixels.
     * @return An array containing the x and y coordinates of the center.
     */
    public static int[] calculateMapCenter(int width, int height) {
        int centerX = width / 2;
        int centerY = height / 2;
        return new int[]{centerX, centerY};
    }

    public static void main(String[] args) {
        // Example usage
        int width = 200;
        int height = 200;

        int[] center = calculateMapCenter(width, height);
        System.out.println("Center coordinates for a " + width + "x" + height + " pixel map are: (" + center[0] + ", " + center[1] + ")");
    }
}
