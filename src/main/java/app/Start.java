package app;


import api.GeolocationAPI;
import api.MapImageAPI;
import interface_adapter.SearchController;
import interface_adapter.SearchViewModel;
import use_case.Initializer;
import use_case.MapImageInteractor;
import use_case.SearchViewInteractor;
import view.SearchView;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import io.github.cdimascio.dotenv.Dotenv;

public class Start {
    public static void main(String[] args) {
        try {
            // Load API key from .env file
            Dotenv dotenv = Dotenv.load();
            String apiKey = dotenv.get("GOOGLE_MAPS_API_KEY");

            // Initialize necessary data
            GeolocationAPI geolocationAPI = new GeolocationAPI();
            MapImageAPI mapImageAPI = new MapImageAPI();
            MapImageInteractor mapImageInteractor = new MapImageInteractor(mapImageAPI, apiKey);
            Initializer initializer = new Initializer(geolocationAPI, mapImageInteractor);
            initializer.initializeCurrentLocation();

            // Get dish types and map image
            String[] dishTypeList = initializer.getDishTypes();
            File mapImageFile = new File("src/main/resources/map_images/map.png");
            Image mapImage = ImageIO.read(mapImageFile);

            // Create the SearchViewInteractor
            SearchViewInteractor searchViewInteractor = new SearchViewInteractor();

            // Create the controller and view model
            SearchController searchController = new SearchController(searchViewInteractor, initializer.getMap(), 200, 200);
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

