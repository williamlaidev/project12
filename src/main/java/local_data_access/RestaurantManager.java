package local_data_access;

import org.json.JSONArray;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RestaurantManager {

    private static final String FILE_PATH = "restaurants.json";

    // Function to load the JSON file
    public static JSONArray loadJson(String filePath) {
        JSONArray data;
        try {
            File file = new File(filePath);
            if (file.exists() && file.length() != 0) {
                String content = new String(Files.readAllBytes(Paths.get(filePath)));
                data = new JSONArray(content);
            } else {
                data = new JSONArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
            data = new JSONArray();
        } catch (org.json.JSONException e) {
            e.printStackTrace();
            data = new JSONArray();
        }
        return data;
    }

    // Function to save the JSON file
    public static void saveJson(String filePath, JSONArray data) {
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(data.toString(4)); // Indent with 4 spaces for readability
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
