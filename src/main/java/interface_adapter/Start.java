package interface_adapter;

import api.GeolocationAPI;
import api.MapImageAPI;
import use_case.Initializer;
import use_case.MapImageInteractor;
import use_case.SimpleSearchInteractor;
import view.SearchView;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Start {
    public static void main(String[] args) {
        try {
            // Initialize necessary data
            Initializer initializer = new Initializer(new GeolocationAPI(), new MapImageInteractor(new MapImageAPI()));
            initializer.initializeCurrentLocation();

            // Get dish types and map image
            String[] dishTypeList = initializer.getDishTypes();
            File mapImageFile = new File("src/main/java/map_images/map.png");
            Image mapImage = ImageIO.read(mapImageFile);

            // Create the SimpleSearchInteractor
            SimpleSearchInteractor simpleSearchInteractor = new SimpleSearchInteractor();

            // Create the controller and view model
            SearchController searchController = new SearchController(simpleSearchInteractor);
            SearchViewModel searchViewModel = new SearchViewModel();

            // Create the SearchView
            SearchView searchView = new SearchView(searchController, searchViewModel, dishTypeList, mapImage);

            // Set up the JFrame
            JFrame frame = new JFrame("Search Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            frame.setContentPane(searchView);
            frame.setLocationRelativeTo(null); // Center the frame on the screen
            frame.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
