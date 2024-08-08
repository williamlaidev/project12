package app;

import domain.*;
import entity.map.MapDefaultFactory;
import entity.map.MapFactory;
import entity.operation_result.OperationResultFailureFactory;
import entity.operation_result.OperationResultSuccessFactory;
import entity.restaurant.RestaurantDefaultFactory;
import entity.restaurant.RestaurantFactory;
import framework.EnvConfigService;
import framework.EnvConfigServiceImpl;
import framework.data.DatabaseConfig;
import framework.data.SQLiteRestaurantRepository;
import framework.data.SQLiteReviewRepository;
import framework.search.*;
import interface_adapter.data.SQLiteReviewDataAdapter;
import interface_adapter.search.*;
import interface_adapter.view.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import use_case.data.create.AddRestaurant;
import use_case.data.create.AddReview;
import use_case.data.read.FindRestaurantById;
import use_case.data.update.UpdateRestaurant;
import use_case.search.FetchRestaurantPhotoUrl;
import use_case.search.FetchRestaurantReviews;
import use_case.search.RestaurantSearchInteractor;
import use_case.search.SearchRestaurantsByDistance;
import use_case.view.Initializer;
import use_case.view.MapImageInteractor;
import use_case.view.SearchViewInteractor;
import view.RestaurantView;
import view.SearchView;

import javax.swing.*;
import java.io.IOException;

public class Start {

    private static final Logger logger = LoggerFactory.getLogger(Start.class);

    public static void main(String[] args) {
        try {
            // Initialize necessary services
            EnvConfigService envConfigService = new EnvConfigServiceImpl();
            GoogleGeolocationService geolocationService = new GoogleGeolocationService(envConfigService);
            GoogleMapsImageService googleMapsImageService = new GoogleMapsImageService(envConfigService);
            MapImageInteractor mapImageInteractor = new MapImageInteractor(googleMapsImageService);
            MapFactory mapFactory = new MapDefaultFactory();
            Initializer initializer = new Initializer(geolocationService, mapImageInteractor, mapFactory);
            initializer.initializeCurrentLocation();

            String[] dishTypeList = initializer.getDishTypes();
            SearchViewModel searchViewModel = new SearchViewModel();
            RestaurantViewModel restaurantViewModel = new RestaurantViewModel();
            ViewManagerModel viewManagerModel = new ViewManagerModel();
            SearchPresenter searchPresenter = new SearchPresenter(viewManagerModel, searchViewModel, restaurantViewModel);
            SearchViewInteractor searchViewInteractor = new SearchViewInteractor(initializer.getMap());

            // Set up fetch restaurant photo URL use case
            RestaurantPhotoService restaurantPhotoService = new GooglePlacesPhotoClient(envConfigService);
            FetchRestaurantPhotoUrl fetchRestaurantPhotoUrl = new FetchRestaurantPhotoUrl(restaurantPhotoService);

            // Set up search controller
            RestaurantSearchGateways restaurantSearchGateways = new GooglePlacesRestaurantClient(envConfigService);
            RestaurantFactory restaurantFactory = new RestaurantDefaultFactory();
            GooglePlacesRestaurantSearchAdapter googlePlacesRestaurantSearchAdapter = new GooglePlacesRestaurantSearchAdapter(restaurantFactory);
            RestaurantRepository restaurantRepository = new SQLiteRestaurantRepository();
            AddRestaurant addRestaurantUseCase = new AddRestaurant(restaurantRepository);
            UpdateRestaurant updateRestaurantUseCase = new UpdateRestaurant(restaurantRepository);
            FindRestaurantById findRestaurantByIdUseCase = new FindRestaurantById(restaurantRepository);
            RestaurantSearchService restaurantSearchService = new GooglePlacesRestaurantSearchService(
                    restaurantSearchGateways, googlePlacesRestaurantSearchAdapter, addRestaurantUseCase,
                    updateRestaurantUseCase, findRestaurantByIdUseCase, fetchRestaurantPhotoUrl);
            SearchRestaurantsByDistance searchRestaurantsByDistance = new SearchRestaurantsByDistance(restaurantSearchService);
            RestaurantSearchInteractor restaurantSearchInteractor = new RestaurantSearchInteractor(searchRestaurantsByDistance);
            SearchController searchController = new SearchController(
                    restaurantSearchInteractor, searchViewInteractor, searchPresenter,
                    initializer.getMap(), 400, 400);

            // Set up add review use case
            DatabaseConfig databaseConfig = new DatabaseConfig();
            SQLiteReviewDataAdapter sqLiteReviewDataAdapter = new SQLiteReviewDataAdapter();
            OperationResultSuccessFactory operationResultSuccessFactory = new OperationResultSuccessFactory();
            OperationResultFailureFactory operationResultFailureFactory = new OperationResultFailureFactory();
            ReviewRepository reviewRepository = new SQLiteReviewRepository(
                    databaseConfig, sqLiteReviewDataAdapter, operationResultSuccessFactory,
                    operationResultFailureFactory);
            AddReview addReviewUseCase = new AddReview(reviewRepository);

            // Set up fetch restaurant review use case
            GooglePlacesReviewSearchAdapter googlePlacesReviewSearchAdapter = new GooglePlacesReviewSearchAdapter();
            ReviewSearchGateways reviewSearchGateways = new GooglePlacesReviewClient(envConfigService);
            ReviewSearchService reviewSearchService = new GooglePlacesReviewSearchService(
                    googlePlacesReviewSearchAdapter, reviewSearchGateways, addReviewUseCase);
            FetchRestaurantReviews fetchRestaurantReviews = new FetchRestaurantReviews(reviewSearchService);

            // Create the SearchView and RestaurantView
            SearchView searchView = new SearchView(searchController, searchViewModel, dishTypeList);
            RestaurantView restaurantView = new RestaurantView(restaurantViewModel, fetchRestaurantReviews);

            // Set up the JFrame with a split pane to show both views
            JFrame frame = new JFrame("Search Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 650);

            JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, searchView, restaurantView);
            splitPane.setDividerLocation(450);

            frame.setContentPane(splitPane);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        } catch (IOException | RuntimeException e) {
            logger.error("Runtime exception occurred", e);
        } catch (Exception e) {
            logger.error("Exception occurred", e);
        }
    }
}
