package app;

import framework.search.GoogleGeolocationService;
import framework.search.GoogleMapsImageService;
import framework.config.EnvConfigServiceImpl;
import interface_adapter.search.SearchRestaurantGateways;
import interface_adapter.view.*;
import use_case.view.Initializer;
import use_case.view.MapImageInteractor;
import use_case.search.SearchRestaurantsByDistanceInteractor;
import use_case.view.SearchViewInteractor;
import view.RestaurantView;
import view.SearchView;
import view.ViewManager;

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

            // Create ViewModels
            SearchViewModel searchViewModel = new SearchViewModel();
            RestaurantViewModel restaurantViewModel = new RestaurantViewModel();

            // Create ViewManagerModel
            ViewManagerModel viewManagerModel = new ViewManagerModel();

            // Create Interactors and Presenters
            SearchRestaurantsByDistanceInteractor restaurantsInteractor = new SearchRestaurantsByDistanceInteractor(new SearchRestaurantGateways());
            SearchPresenter searchPresenter = new SearchPresenter(viewManagerModel, searchViewModel, restaurantViewModel);
            SearchViewInteractor searchViewInteractor = new SearchViewInteractor(restaurantsInteractor, initializer.getMap(), searchPresenter);

            // Create the controller and view model
            SearchController searchController = new SearchController(searchViewInteractor, searchPresenter, initializer.getMap(), 400, 400);

            // Create the SearchView and RestaurantView
            SearchView searchView = new SearchView(searchController, searchViewModel, dishTypeList);
            RestaurantView restaurantView = new RestaurantView(restaurantViewModel);  // Assume it's properly initialized

            // Set up the JFrame with a split pane to show both views
            JFrame frame = new JFrame("Search Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 650); // Updated size to fit both views

            JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, searchView, restaurantView);
            splitPane.setDividerLocation(450); // Adjust this value as needed for better balance

            frame.setContentPane(splitPane);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}