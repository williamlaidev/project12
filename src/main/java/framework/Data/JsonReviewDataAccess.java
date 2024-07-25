package framework.Data;

import data_access.ReviewDataAccess;
import entity.Review;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Implementation of {@link ReviewDataAccess} that uses JSON file storage.
 */
public class JsonReviewDataAccess implements ReviewDataAccess {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File jsonFile = new File("src/resources/data/reviews.json"); // Path to JSON file

    public JsonReviewDataAccess() {
        // Ensure the file exists, create it if it does not
        try {
            if (jsonFile.getParentFile() != null && !jsonFile.getParentFile().exists()) {
                boolean dirsCreated = jsonFile.getParentFile().mkdirs(); // Create directories if they do not exist
                if (!dirsCreated) {
                    System.out.println("Failed to create directories: " + jsonFile.getParentFile().getPath());
                }
            }
            if (!jsonFile.exists()) {
                boolean fileCreated = jsonFile.createNewFile(); // Create the file if it does not exist
                if (fileCreated) {
                    System.out.println("Created new file: " + jsonFile.getPath());
                    // Initialize the file with an empty list if needed
                    objectMapper.writeValue(jsonFile, List.of());
                } else {
                    System.out.println("File already exists: " + jsonFile.getPath());
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to create or initialize file: " + jsonFile.getPath());
            e.printStackTrace();
        }
    }

    /**
     * Loads a list of reviews from a JSON file.
     *
     * @return a list of {@link Review} objects loaded from the JSON file.
     *         If the file does not exist or an error occurs, an empty list is returned.
     */
    @Override
    public List<Review> loadReviews() {
        if (jsonFile.exists()) {
            try {
                System.out.println("Loading reviews from file: " + jsonFile.getPath());
                return objectMapper.readValue(jsonFile, new TypeReference<>() {
                });
            } catch (IOException e) {
                System.out.println("Failed to load reviews from file: " + jsonFile.getPath());
                e.printStackTrace();
            }
        } else {
            System.out.println("File does not exist: " + jsonFile.getPath());
        }
        return List.of(); // Return empty list if file does not exist or error occurs
    }

    /**
     * Saves a list of reviews to a JSON file.
     *
     * @param reviews the list of {@link Review} objects to be saved.
     *                If an error occurs during saving, the operation fails silently.
     */
    @Override
    public void saveReviews(List<Review> reviews) {
        try {
            System.out.println("Saving reviews to file: " + jsonFile.getPath());
            objectMapper.writeValue(jsonFile, reviews);
            System.out.println("Successfully saved reviews to file: " + jsonFile.getPath());
        } catch (IOException e) {
            System.out.println("Failed to save reviews to file: " + jsonFile.getPath());
            e.printStackTrace();
        }
    }
}
