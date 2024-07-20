package use_case;

import api.ApiKeyReader;
import api.MapImageAPI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * MapImageInteractor class acts as the use case for fetching the map image using MapImageAPI.
 */
public class MapImageInteractor {
    private final MapImageAPI mapImageAPI;

    /**
     * Constructor to initialize MapImageInteractor with MapImageAPI.
     *
     * @param mapImageAPI An instance of MapImageAPI.
     */
    public MapImageInteractor(MapImageAPI mapImageAPI) {
        this.mapImageAPI = mapImageAPI;
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
            ApiKeyReader apiKeyReader = new ApiKeyReader("MapImageAPI", "src/main/java/api/API_KEYS.txt");
            String apiKey = apiKeyReader.readApiKey();
            byte[] imageData = mapImageAPI.getMapImage(latitude, longitude, zoom, width, height, apiKey);
            saveImage(imageData, "src/main/java/map_images/map.png");
            return true;
        } catch (ApiKeyReader.ApiKeyFileNotFoundException e) {
            System.err.println(e.getMessage());
            return false;
        } catch (ApiKeyReader.ApiKeyNotFoundException e) {
            System.err.println(e.getMessage());
            return false;
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
