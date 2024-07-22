package framework;
/**
 * Interface for interacting with a map image service to fetch map images.
 */
public interface MapImageService {

    /**
     * Retrieves a map image based on provided parameters.
     *
     * @param latitude  The latitude of the location.
     * @param longitude The longitude of the location.
     * @param zoom      The zoom level of the map.
     * @param width     The width of the desired map image in pixels.
     * @param height    The height of the desired map image in pixels.
     * @return byte[] containing the map image data.
     * @throws Exception if there is a network issue or the API call fails.
     */
    byte[] getMapImage(double latitude, double longitude, int zoom, int width, int height) throws Exception;
}
