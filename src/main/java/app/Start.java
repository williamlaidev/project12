package app;

import framework.search.GoogleGeolocationService;
import framework.search.GoogleMapsImageService;
import framework.config.EnvConfigServiceImpl;
import interface_adapter.search.SearchRestaurantGateways;
import interface_adapter.view.SearchController;
import interface_adapter.view.SearchViewModel;
import use_case.view.Initializer;
import use_case.view.MapImageInteractor;
import use_case.search.SearchRestaurantsByDistanceInteractor;
import use_case.view.SearchViewInteractor;
import view.SearchView;
import interface_adapter.view.SearchPresenter;

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
            

            // Create the SearchViewModel
            SearchViewModel searchViewModel = new SearchViewModel();

            // Create the SearchRestaurantsByDistanceInteractor with the repository
            SearchRestaurantsByDistanceInteractor restaurantsInteractor = new SearchRestaurantsByDistanceInteractor(new SearchRestaurantGateways());

            // Create the SearchViewInteractor with the SearchRestaurantsByDistanceInteractor instance
            SearchViewInteractor searchViewInteractor = new SearchViewInteractor(restaurantsInteractor, initializer.getMap());

            SearchPresenter searchPresenter = new SearchPresenter(searchViewModel);
            searchPresenter.setZoomLevel(15);

            // Create the controller and view model
            SearchController searchController = new SearchController(searchViewInteractor, searchPresenter, initializer.getMap(), 400, 400);

            // Create the SearchView
            SearchView searchView = new SearchView(searchController, searchViewModel, dishTypeList);

            // Set up the JFrame
            JFrame frame = new JFrame("Search Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(450, 650);
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
