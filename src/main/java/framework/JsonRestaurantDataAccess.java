package framework;

import data_access.RestaurantDataAccess;
import entity.Restaurant;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonRestaurantDataAccess implements RestaurantDataAccess {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File jsonFile = new File("src/main/resources/data/restaurants.json"); // Replace with json file path

    @Override
    public List<Restaurant> loadRestaurants() {
        if (jsonFile.exists()) {
            try {
                System.out.println("Loading restaurants from file: " + jsonFile.getPath());
                return objectMapper.readValue(jsonFile, new TypeReference<List<Restaurant>>(){});
            } catch (IOException e) {
                System.out.println("Failed to load restaurants from file: " + jsonFile.getPath());
                e.printStackTrace();
            }
        } else {
            System.out.println("File does not exist: " + jsonFile.getPath());
        }
        return List.of(); // Return empty list if file does not exist or error occurs
    }

    @Override
    public void saveRestaurants(List<Restaurant> restaurants) {
        try {
            System.out.println("Saving restaurants to file: " + jsonFile.getPath());
            objectMapper.writeValue(jsonFile, restaurants);
            System.out.println("Successfully saved restaurants to file: " + jsonFile.getPath());
        } catch (IOException e) {
            System.out.println("Failed to save restaurants to file: " + jsonFile.getPath());
            e.printStackTrace();
        }
    }
}
