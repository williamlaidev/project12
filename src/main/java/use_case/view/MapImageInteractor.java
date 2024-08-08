package use_case.view;

import framework.search.GoogleMapsImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Handles fetching and saving map images using GoogleMapsImageService.
 */
public class MapImageInteractor {
    private static final Logger logger = LoggerFactory.getLogger(MapImageInteractor.class);
    private final GoogleMapsImageService googleMapsImageService;

    /**
     * Constructs a MapImageInteractor with the given GoogleMapsImageService.
     *
     * @param googleMapsImageService service to fetch map images
     */
    public MapImageInteractor(GoogleMapsImageService googleMapsImageService) {
        this.googleMapsImageService = googleMapsImageService;
    }

    /**
     * Fetches and saves a map image based on the specified parameters.
     *
     * @param latitude  the latitude of the map center
     * @param longitude the longitude of the map center
     * @param zoom      the zoom level of the map
     * @param width     the width of the map image
     * @param height    the height of the map image
     * @return true if successful, false otherwise
     */
    public boolean fetchAndSaveMapImage(double latitude, double longitude, int zoom, int width, int height) {
        try {
            byte[] imageData = googleMapsImageService.getMapImage(latitude, longitude, zoom, width, height);
            saveImage(imageData);
            return true;
        } catch (IOException e) {
            logger.error("Failed to fetch and save the map image", e);
            return false;
        }
    }

    /**
     * Saves image data to a file.
     *
     * @param imageData the image data in byte array
     * @throws IOException if an I/O error occurs
     */
    private void saveImage(byte[] imageData) throws IOException {
        File file = new File("src/main/resources/map_images/map.png");
        if (!file.getParentFile().mkdirs() && !file.getParentFile().exists()) {
            throw new IOException("Failed to create directories for path: " + file.getPath());
        }
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(imageData);
        }
    }
}
