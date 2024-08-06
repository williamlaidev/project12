package app;

import domain.RestaurantPhotoService;
import domain.RestaurantRepository;
import domain.RestaurantSearchService;
import entity.map.MapDefaultFactory;
import entity.map.MapFactory;
import entity.restaurant.RestaurantDefaultFactory;
import entity.restaurant.RestaurantFactory;
import framework.data.SQLiteRestaurantRepository;
import framework.search.*;
import framework.EnvConfigServiceImpl;
import interface_adapter.search.*;
import interface_adapter.view.SearchController;
import interface_adapter.view.SearchViewModel;
import use_case.data.create.AddRestaurant;
import use_case.data.read.FindRestaurantById;
import use_case.data.update.UpdateRestaurant;
import use_case.search.FetchRestaurantPhotoUrl;
import use_case.search.RestaurantSearchInteractor;
import use_case.search.SearchRestaurantsByDistance;
import use_case.view.Initializer;
import use_case.view.MapImageInteractor;
import view.SearchView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Start {
    private static final Logger logger = LoggerFactory.getLogger(Start.class);

    public static void main(String[] args) {
        try {
            // Initialize configuration and services
            EnvConfigServiceImpl envConfigService = new EnvConfigServiceImpl();
            GoogleGeolocationService geolocationService = new GoogleGeolocationService(envConfigService);
            GoogleMapsImageService googleMapsImageService = new GoogleMapsImageService(envConfigService);
            MapImageInteractor mapImageInteractor = new MapImageInteractor(googleMapsImageService);
            MapFactory mapFactory = new MapDefaultFactory();
            Initializer initializer = new Initializer(geolocationService, mapImageInteractor, mapFactory);
            initializer.initializeCurrentLocation();

            // Load dish types and map image
            String[] dishTypeList = initializer.getDishTypes();
            File mapImageFile = new File("src/main/resources/map_images/map.png");
            Image mapImage = ImageIO.read(mapImageFile);

            // Set up search components
            RestaurantSearchGateways restaurantSearchGateways = new GooglePlacesRestaurantClient(envConfigService);
            RestaurantFactory restaurantFactory = new RestaurantDefaultFactory();
            GooglePlacesRestaurantSearchAdapter searchAdapter = new GooglePlacesRestaurantSearchAdapter(restaurantFactory);
            RestaurantRepository restaurantRepository = new SQLiteRestaurantRepository();
            AddRestaurant addRestaurant = new AddRestaurant(restaurantRepository);
            UpdateRestaurant updateRestaurant = new UpdateRestaurant(restaurantRepository);
            FindRestaurantById findRestaurantById = new FindRestaurantById(restaurantRepository);
            RestaurantPhotoService restaurantPhotoService = new GooglePlacesPhotoClient(envConfigService);
            FetchRestaurantPhotoUrl fetchRestaurantPhotoUrl = new FetchRestaurantPhotoUrl(restaurantPhotoService);
            RestaurantSearchService restaurantSearchService = new GooglePlacesRestaurantSearchService(
                    restaurantSearchGateways, searchAdapter, addRestaurant, updateRestaurant, findRestaurantById, fetchRestaurantPhotoUrl
            );
            SearchRestaurantsByDistance searchRestaurantsByDistance = new SearchRestaurantsByDistance(restaurantSearchService);
            RestaurantSearchInteractor searchViewInteractor = new RestaurantSearchInteractor(searchRestaurantsByDistance);

            // Set up the view and controller
            SearchViewModel searchViewModel = new SearchViewModel();
            SearchController searchController = new SearchController(searchViewInteractor, initializer.getMap(), 200, 200);
            SearchView searchView = new SearchView(searchController, searchViewModel, dishTypeList, mapImage);

            // Create and display the JFrame
            JFrame frame = new JFrame("Search Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            frame.setContentPane(searchView);
            frame.setLocationRelativeTo(null); // Center the frame on the screen
            frame.setVisible(true);

        } catch (IOException e) {
            logger.error("IO error occurred: ", e);
        } catch (RuntimeException e) {
            logger.error("Runtime error occurred: ", e);
        } catch (Exception e) {
            logger.error("An unexpected error occurred: ", e);
        }
    }
}
