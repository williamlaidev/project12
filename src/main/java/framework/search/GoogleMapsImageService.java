package framework.search;

import framework.EnvConfigService;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Implementation of MapImageService that interacts with the Google Maps Static API.
 */
public class GoogleMapsImageService implements MapImageService {

    // Base URL for Google Maps Static API endpoint
    private static final String MAP_IMAGE_API_URL = "https://maps.googleapis.com/maps/api/staticmap";
    private final EnvConfigService envConfigService;

    /**
     * Constructs the GoogleMapsImageService with the provided EnvConfigService.
     *
     * @param envConfigService The EnvConfigService instance for retrieving configuration.
     */
    public GoogleMapsImageService(EnvConfigService envConfigService) {
        this.envConfigService = envConfigService;
    }

    /**
     * Retrieves a map image from the Google Maps Static API based on the provided parameters.
     *
     * @param latitude  The latitude of the location.
     * @param longitude The longitude of the location.
     * @param zoom      The zoom level of the map.
     * @param width     The width of the desired map image in pixels.
     * @param height    The height of the desired map image in pixels.
     * @return byte[] containing the map image data.
     * @throws IOException if there is a network issue or the API call fails.
     */
    @Override
    public byte[] getMapImage(double latitude, double longitude, int zoom, int width, int height) throws IOException {
        // Retrieve API key from the environment configuration
        String apiKey = envConfigService.getGoogleMapsApiKey();
        if (apiKey == null || apiKey.isEmpty()) {
            throw new RuntimeException("Google Maps API key is missing or empty.");
        }

        // Construct the URL with query parameters
        String urlString = String.format("%s?center=%f,%f&zoom=%d&size=%dx%d&key=%s",
                MAP_IMAGE_API_URL, latitude, longitude, zoom, width, height, apiKey);

        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "image/png");

        // Read the response input stream and convert it to a byte array
        try (InputStream in = new BufferedInputStream(conn.getInputStream());
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int n;
            while ((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }
            System.out.println("Map image data retrieved successfully.");

            return out.toByteArray();
        } finally {
            conn.disconnect();
        }
    }
}
