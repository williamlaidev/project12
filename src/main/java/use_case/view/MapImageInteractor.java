package use_case.view;

import framework.search.GoogleMapsImageService;
import framework.config.EnvConfigServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * MapImageInteractor class acts as the use case for fetching the map image using GoogleMapsImageService.
 */
public class MapImageInteractor {
    private static final Logger logger = LoggerFactory.getLogger(MapImageInteractor.class);

    private final GoogleMapsImageService googleMapsImageService;

    /**
     * Constructor to initialize MapImageInteractor with GoogleMapsImageService.
     *
     * @param googleMapsImageService An instance of GoogleMapsImageService.
     */
    public MapImageInteractor(GoogleMapsImageService googleMapsImageService) {
        this.googleMapsImageService = googleMapsImageService;
    }

    /**
     * Gets the map image based on provided parameters.
     *
     * @param latitude  The latitude of the location.
     * @param longitude The longitude of the location.
     * @param zoom      The zoom level of the map.
     * @param width     The width of the desired map image in pixels.
     * @param height    The height of the desired map image in pixels.
     * @return boolean indicating the success of the operation.
     */
    public boolean fetchAndSaveMapImage(double latitude, double longitude, int zoom, int width, int height) {
        try {
            byte[] imageData = googleMapsImageService.getMapImage(latitude, longitude, zoom, width, height);
            saveImage(imageData, "src/main/resources/map_images/map.png");
            return true;
        } catch (IOException e) {
            logger.error("Failed to fetch and save the map image", e);
            return false;
        }
    }

    /**
     * Saves the image data to the specified file path.
     *
     * @param imageData The image data in byte array.
     * @param filePath  The file path where the image will be saved.
     * @throws IOException if an I/O error occurs during saving.
     */
    private void saveImage(byte[] imageData, String filePath) throws IOException {
        File file = new File(filePath);
        // Ensure the directories are created and handle the result
        if (!file.getParentFile().mkdirs() && !file.getParentFile().exists()) {
            throw new IOException("Failed to create directories for path: " + filePath);
        }
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(imageData);
        }
    }

    public static void main(String[] args) {
        // Create instances of EnvConfigService and GoogleMapsImageService
        EnvConfigServiceImpl envConfigService = new EnvConfigServiceImpl();
        GoogleMapsImageService googleMapsImageService = new GoogleMapsImageService(envConfigService);

        // Create the MapImageInteractor with the GoogleMapsImageService
        MapImageInteractor interactor = new MapImageInteractor(googleMapsImageService);

        // Example usage
        boolean success = interactor.fetchAndSaveMapImage(37.7749, -122.4194, 12, 800, 600);
        System.out.println("Map image fetched and saved: " + success);
    }
}
