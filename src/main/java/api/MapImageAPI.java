package api;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * MapImageAPI class interacts with the map image API to get a map image based on provided parameters.
 */
public class MapImageAPI {
    private static final String MAP_IMAGE_API_URL = "https://maps.googleapis.com/maps/api/staticmap";

    /**
     * Gets the map image from the map image API.
     *
     * @param latitude  The latitude of the location.
     * @param longitude The longitude of the location.
     * @param zoom      The zoom level of the map.
     * @param width     The width of the desired map image in pixels.
     * @param height    The height of the desired map image in pixels.
     * @param apiKey    The API key for the map image API.
     * @return byte[] containing the map image data.
     * @throws Exception if there is a network issue or the API call fails.
     */
    public byte[] getMapImage(double latitude, double longitude, int zoom, int width, int height, String apiKey) throws Exception {
        String urlString = String.format("%s?center=%f,%f&zoom=%d&size=%dx%d&key=%s",
                MAP_IMAGE_API_URL, latitude, longitude, zoom, width, height, apiKey);
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "image/png");

        InputStream in = new BufferedInputStream(conn.getInputStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int n;

        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }

        in.close();
        conn.disconnect();

        return out.toByteArray();
    }
}
