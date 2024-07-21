package use_case;

import api.MapImageAPI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * MapImageInteractor class acts as the use case for fetching the map image using MapImageAPI.
 */
public class MapImageInteractor {
    private final MapImageAPI mapImageAPI;
    private final String apiKey;

    /**
     * Constructor to initialize MapImageInteractor with MapImageAPI.
     *
     * @param mapImageAPI An instance of MapImageAPI.
     * @param apiKey      The API key for accessing the map image API.
     */
    public MapImageInteractor(MapImageAPI mapImageAPI, String apiKey) {
        this.mapImageAPI = mapImageAPI;
        this.apiKey = apiKey;
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
            byte[] imageData = mapImageAPI.getMapImage(latitude, longitude, zoom, width, height, apiKey);
            saveImage(imageData, "src/main/resources/map_images/map.png");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
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
        file.getParentFile().mkdirs(); // Ensure the directories are created
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(imageData);
        }
    }
}
