package framework;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Restaurant;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonFileHandler {
    private final File jsonFile;
    private final ObjectMapper objectMapper;
    private final TypeReference<List<Restaurant>> typeReference;

    public JsonFileHandler(String filePath) {
        this.jsonFile = new File(filePath);
        this.objectMapper = new ObjectMapper();
        this.typeReference = new TypeReference<List<Restaurant>>() {};
    }

    public List<Restaurant> readFromFile() {
        try {
            if (jsonFile.exists()) {
                return objectMapper.readValue(jsonFile, typeReference);
            } else {
                return new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("Failed to read from JSON file: " + e.getMessage());
            throw new RuntimeException("Failed to read from JSON file", e);
        }
    }

    public boolean writeToFile(List<Restaurant> items) {
        try {
            objectMapper.writeValue(jsonFile, items);
            return true;
        } catch (IOException e) {
            System.out.println("Failed to write to JSON file: " + e.getMessage());
            return false;
        }
    }
}
