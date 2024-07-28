package app;

import data_access.RestaurantDataAccess;
import framework.search.GoogleGeolocationService;
import framework.search.GoogleMapsImageService;
import framework.config.EnvConfigServiceImpl;
import framework.data.JsonRestaurantDataAccess;
import interface_adapter.search.SearchRestaurantGateways;
import interface_adapter.view.SearchController;
import interface_adapter.view.SearchViewModel;
import use_case.view.Initializer;
import use_case.view.MapImageInteractor;
import use_case.search.SearchRestaurantsByDistanceInteractor;
import use_case.view.SearchViewInteractor;
import view.SearchView;
import interface_adapter.data.InMemoryRestaurantRepository;
import domain.RestaurantRepository;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Start {
    public static void main(String[] args) {
        try {
            // Initialize necessary services
            EnvConfigServiceImpl envConfigService = new EnvConfigServiceImpl();
            GoogleGeolocationService geolocationService = new GoogleGeolocationService(envConfigService);
            GoogleMapsImageService googleMapsImageService = new GoogleMapsImageService(envConfigService);
            MapImageInteractor mapImageInteractor = new MapImageInteractor(googleMapsImageService);
            Initializer initializer = new Initializer(geolocationService, mapImageInteractor);
            initializer.initializeCurrentLocation();

            // Get dish types and map image
            String[] dishTypeList = initializer.getDishTypes();
            File mapImageFile = new File("src/main/resources/map_images/map.png");
            Image mapImage = ImageIO.read(mapImageFile);

            // Create the SearchViewModel
            SearchViewModel searchViewModel = new SearchViewModel();

            // Initialize the data access and repository
            RestaurantDataAccess dataAccess = new JsonRestaurantDataAccess();
            RestaurantRepository restaurantRepository = new InMemoryRestaurantRepository(dataAccess);

            // Create the SearchRestaurantsByDistanceInteractor with the repository
            SearchRestaurantsByDistanceInteractor restaurantsInteractor = new SearchRestaurantsByDistanceInteractor(new SearchRestaurantGateways());

            // Create the SearchViewInteractor with the SearchRestaurantsByDistanceInteractor instance
            SearchViewInteractor searchViewInteractor = new SearchViewInteractor(restaurantsInteractor);

            // Create the controller and view model
            SearchController searchController = new SearchController(searchViewInteractor, initializer.getMap(), 200, 200);

            // Create the SearchView
            SearchView searchView = new SearchView(searchController, searchViewModel, dishTypeList, mapImage);

            // Set up the JFrame
            JFrame frame = new JFrame("Search Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            frame.setContentPane(searchView);
            frame.setLocationRelativeTo(null); // Center the frame on the screen
            frame.setVisible(true);
        } catch (IOException | RuntimeException e) {
            // Handle IOException and RuntimeException similarly
            e.printStackTrace();
        } catch (Exception e) {
            // Handle any other exceptions
            e.printStackTrace();
        }
    }
}
