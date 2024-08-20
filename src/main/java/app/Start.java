package app;

import domain.*;
import entity.map.MapDefaultFactory;
import entity.map.MapFactory;
import entity.operation_result.OperationResultFailureFactory;
import entity.operation_result.OperationResultSuccessFactory;
import entity.restaurant.RestaurantDefaultFactory;
import entity.restaurant.RestaurantFactory;
import entity.review.ReviewGeminiFactory;
import framework.EnvConfigService;
import framework.EnvConfigServiceImpl;
import framework.data.*;
import framework.search.*;
import framework.summarize.GeminiSummarizeClient;
import interface_adapter.data.SQLiteReviewDataAdapter;
import interface_adapter.search.*;
import interface_adapter.summarize.*;
import interface_adapter.view.*;
import use_case.data.create.AddRestaurant;
import use_case.data.create.AddReview;
import use_case.data.read.FindRestaurantById;
import use_case.data.read.FindSummarizedReview;
import use_case.data.update.UpdateRestaurant;
import use_case.data.update.UpdateReview;
import use_case.search.*;
import use_case.summarize.SummarizeReviews;
import use_case.view.*;
import view.RestaurantView;
import view.SearchView;

import javax.swing.*;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main application entry point for the Search Application.
 * Initializes services, use cases, and views, then sets up the main frame.
 */
public class Start {

    private static final Logger logger = LoggerFactory.getLogger(Start.class);

    /**
     * Main method to start the Search Application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        try {
            // Initialize necessary services
            EnvConfigService envConfigService = initializeServices();
            GoogleGeolocationService geolocationService = new GoogleGeolocationService(envConfigService);
            GoogleMapsImageService googleMapsImageService = new GoogleMapsImageService(envConfigService);
            MapFactory mapFactory = new MapDefaultFactory();
            MapImageInteractor mapImageInteractor = new MapImageInteractor(googleMapsImageService);

            // Initialize view models
            ViewModels viewModels = initializeViewModels();

            // Set up fetch restaurant photo URL use case
            FetchRestaurantPhotoUrl fetchRestaurantPhotoUrl = initializeFetchRestaurantPhotoUrl(envConfigService);

            // Initialize Initializer
            SearchState searchState = new SearchState();
            Initializer initializer = initializeInitializer(geolocationService, mapImageInteractor, mapFactory, searchState);
            String[] dishTypeList = initializer.getDishTypes();

            // Set up search interactor and presenter
            SearchInteractorAndPresenter searchInteractorAndPresenter = initializeSearchInteractorAndPresenter(initializer, viewModels);

            // Set up restaurant search services
            RestaurantSearchServices restaurantSearchServices = initializeRestaurantSearchServices(envConfigService, searchInteractorAndPresenter.searchPresenter, fetchRestaurantPhotoUrl);

            // Set up search controller
            SearchController searchController = initializeSearchController(initializer, searchInteractorAndPresenter, restaurantSearchServices, searchInteractorAndPresenter.searchPresenter);


            // Initialize review repository
            ReviewRepository reviewRepository = initializeReviewRepository();

            // Set up add and update review use cases
            AddReview addReviewUseCase = new AddReview(reviewRepository);
            UpdateReview updateReviewUseCase = new UpdateReview(reviewRepository);

            // Set up fetch restaurant review use case
            FetchRestaurantReviews fetchRestaurantReviews = initializeFetchRestaurantReviewUseCase(envConfigService, addReviewUseCase);

            // Initialize review summarization
            SummarizeReviews summarizeReviews = initializeSummarizeReviews(restaurantSearchServices.findRestaurantByIdUseCase, addReviewUseCase, updateReviewUseCase, reviewRepository);

            // Create the SearchView and RestaurantView
            SearchView searchView = new SearchView(searchController, searchInteractorAndPresenter.searchViewModel, dishTypeList);
            RestaurantView restaurantView = new RestaurantView(viewModels.restaurantViewModel, fetchRestaurantReviews, summarizeReviews);

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

    /**
     * Initializes necessary services.
     *
     * @return The environment configuration service.
     */
    private static EnvConfigService initializeServices() {
        return new EnvConfigServiceImpl();
    }

    /**
     * Initializes view models.
     *
     * @return An instance of ViewModels containing the view models.
     */
    private static ViewModels initializeViewModels() {
        SearchViewModel searchViewModel = new SearchViewModel();
        RestaurantViewModel restaurantViewModel = new RestaurantViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        return new ViewModels(searchViewModel, restaurantViewModel, viewManagerModel);
    }

    /**
     * Initializes the FetchRestaurantPhotoUrl use case.
     *
     * @param envConfigService The environment configuration service.
     * @return The FetchRestaurantPhotoUrl use case.
     */
    private static FetchRestaurantPhotoUrl initializeFetchRestaurantPhotoUrl(EnvConfigService envConfigService) {
        RestaurantPhotoService restaurantPhotoService = new GooglePlacesPhotoClient(envConfigService);
        return new FetchRestaurantPhotoUrl(restaurantPhotoService);
    }

    /**
     * Initializes the Initializer.
     *
     * @param geolocationService The geolocation service.
     * @param mapImageInteractor The map image interactor.
     * @param mapFactory The map factory.
     * @return The Initializer.
     */
    private static Initializer initializeInitializer(GoogleGeolocationService geolocationService, MapImageInteractor mapImageInteractor, MapFactory mapFactory, SearchState searchState) {
        Initializer initializer = new Initializer(geolocationService, mapImageInteractor, mapFactory, searchState);
        try {
            initializer.initializeCurrentLocation();
        } catch (Exception e) {
            logger.error("Failed to initialize current location", e);
        }
        return initializer;
    }

    /**
     * Initializes the search interactor and presenter.
     *
     * @param initializer The initializer.
     * @param viewModels The view models.
     * @return An instance of SearchInteractorAndPresenter containing the search interactor and presenter.
     */
    private static SearchInteractorAndPresenter initializeSearchInteractorAndPresenter(Initializer initializer, ViewModels viewModels) {
        SearchViewInteractor searchViewInteractor = new SearchViewInteractor(initializer.getMap());
        SearchPresenter searchPresenter = new SearchPresenter(viewModels.viewManagerModel, viewModels.searchViewModel, viewModels.restaurantViewModel);
        return new SearchInteractorAndPresenter(searchViewInteractor, searchPresenter, viewModels.searchViewModel);
    }

    /**
     * Initializes the restaurant search services.
     *
     * @param envConfigService The environment configuration service.
     * @param searchPresenter The search presenter.
     * @param fetchRestaurantPhotoUrl The fetch restaurant photo URL use case.
     * @return An instance of RestaurantSearchServices containing the restaurant search services.
     */
    private static RestaurantSearchServices initializeRestaurantSearchServices(EnvConfigService envConfigService, SearchPresenter searchPresenter, FetchRestaurantPhotoUrl fetchRestaurantPhotoUrl) {
        RestaurantFactory restaurantFactory = new RestaurantDefaultFactory();
        RestaurantSearchGateways restaurantSearchGateways = new GooglePlacesRestaurantClient(envConfigService);
        GooglePlacesRestaurantSearchAdapter googlePlacesRestaurantSearchAdapter = new GooglePlacesRestaurantSearchAdapter(restaurantFactory);
        RestaurantRepository restaurantRepository = new SQLiteRestaurantRepository();
        AddRestaurant addRestaurantUseCase = new AddRestaurant(restaurantRepository);
        UpdateRestaurant updateRestaurantUseCase = new UpdateRestaurant(restaurantRepository);
        FindRestaurantById findRestaurantByIdUseCase = new FindRestaurantById(restaurantRepository);
        RestaurantSearchService restaurantSearchService = new GooglePlacesRestaurantSearchService(
                restaurantSearchGateways, googlePlacesRestaurantSearchAdapter, addRestaurantUseCase,
                updateRestaurantUseCase, findRestaurantByIdUseCase, fetchRestaurantPhotoUrl, searchPresenter);
        return new RestaurantSearchServices(restaurantSearchService, findRestaurantByIdUseCase, updateRestaurantUseCase, restaurantRepository);
    }

    /**
     * Initializes the search controller.
     *
     * @param initializer The initializer.
     * @param searchInteractorAndPresenter The search interactor and presenter.
     * @param restaurantSearchServices The restaurant search services.
     * @return The SearchController.
     */
    private static SearchController initializeSearchController(Initializer initializer, SearchInteractorAndPresenter searchInteractorAndPresenter, RestaurantSearchServices restaurantSearchServices, SearchPresenter searchPresenter) {
        SearchRestaurantsByDistance searchRestaurantsByDistance = new SearchRestaurantsByDistance(restaurantSearchServices.restaurantSearchService);
        RestaurantSearchInteractor restaurantSearchInteractor = new RestaurantSearchInteractor(searchRestaurantsByDistance);
        SearchRestaurantInteractor searchRestaurantInteractor = new SearchRestaurantInteractor(searchPresenter);
        return new SearchController(
                restaurantSearchInteractor, searchInteractorAndPresenter.searchViewInteractor, searchInteractorAndPresenter.searchPresenter,
                searchInteractorAndPresenter.searchViewModel, initializer.getMap().getCurrentLatitude(), initializer.getMap().getCurrentLongitude(),
                15, 400, 400,
                searchRestaurantInteractor);
    }

    /**
     * Initializes the review repository.
     *
     * @return The ReviewRepository.
     */
    private static ReviewRepository initializeReviewRepository() {
        DatabaseConfig databaseConfig = new DatabaseConfig();
        SQLiteReviewDataAdapter sqLiteReviewDataAdapter = new SQLiteReviewDataAdapter();
        OperationResultSuccessFactory operationResultSuccessFactory = new OperationResultSuccessFactory();
        OperationResultFailureFactory operationResultFailureFactory = new OperationResultFailureFactory();
        return new SQLiteReviewRepository(databaseConfig, sqLiteReviewDataAdapter, operationResultSuccessFactory, operationResultFailureFactory);
    }

    /**
     * Initializes the FetchRestaurantReviews use case.
     *
     * @param envConfigService The environment configuration service.
     * @param addReviewUseCase The AddReview use case.
     * @return The FetchRestaurantReviews use case.
     */
    private static FetchRestaurantReviews initializeFetchRestaurantReviewUseCase(EnvConfigService envConfigService, AddReview addReviewUseCase) {
        GooglePlacesReviewSearchAdapter googlePlacesReviewSearchAdapter = new GooglePlacesReviewSearchAdapter();
        ReviewSearchGateways reviewSearchGateways = new GooglePlacesReviewClient(envConfigService);
        ReviewSearchService reviewSearchService = new GooglePlacesReviewSearchService(
                googlePlacesReviewSearchAdapter, reviewSearchGateways, addReviewUseCase);
        return new FetchRestaurantReviews(reviewSearchService);
    }

    /**
     * Initializes the SummarizeReviews use case.
     *
     * @param findRestaurantByIdUseCase The FindRestaurantById use case.
     * @param addReviewUseCase The AddReview use case.
     * @param updateReviewUseCase The UpdateReview use case.
     * @param reviewRepository The review repository.
     * @return The SummarizeReviews use case.
     */
    private static SummarizeReviews initializeSummarizeReviews(FindRestaurantById findRestaurantByIdUseCase,
                                                               AddReview addReviewUseCase,
                                                               UpdateReview updateReviewUseCase,
                                                               ReviewRepository reviewRepository) {
        ReviewSummarizeGateways reviewSummarizeGateways = new GeminiSummarizeClient();
        ReviewGeminiFactory reviewGeminiFactory = new ReviewGeminiFactory();
        ReviewSummarizeAdapter reviewSummarizeAdapter = new GeminiReviewSummarizeAdapter(findRestaurantByIdUseCase, reviewGeminiFactory);
        FindSummarizedReview findSummarizedReviewUseCase = new FindSummarizedReview(reviewRepository);
        ReviewSummarizeService reviewSummarizeService = new GeminiReviewSummarizeService(
                reviewSummarizeGateways, reviewSummarizeAdapter, addReviewUseCase, updateReviewUseCase,
                findSummarizedReviewUseCase);
        return new SummarizeReviews(reviewSummarizeService);
    }

    /**
     * Class to hold view models.
     */
    private static class ViewModels {
        final SearchViewModel searchViewModel;
        final RestaurantViewModel restaurantViewModel;
        final ViewManagerModel viewManagerModel;

        ViewModels(SearchViewModel searchViewModel, RestaurantViewModel restaurantViewModel, ViewManagerModel viewManagerModel) {
            this.searchViewModel = searchViewModel;
            this.restaurantViewModel = restaurantViewModel;
            this.viewManagerModel = viewManagerModel;
        }
    }

    /**
     * Class to hold search interactor and presenter.
     */
    private static class SearchInteractorAndPresenter {
        final SearchViewInteractor searchViewInteractor;
        final SearchPresenter searchPresenter;
        final SearchViewModel searchViewModel;

        SearchInteractorAndPresenter(SearchViewInteractor searchViewInteractor, SearchPresenter searchPresenter, SearchViewModel searchViewModel) {
            this.searchViewInteractor = searchViewInteractor;
            this.searchPresenter = searchPresenter;
            this.searchViewModel = searchViewModel;
        }
    }

    /**
     * Class to hold restaurant search services.
     */
    private static class RestaurantSearchServices {
        final RestaurantSearchService restaurantSearchService;
        final FindRestaurantById findRestaurantByIdUseCase;
        final UpdateRestaurant updateRestaurantUseCase;
        final RestaurantRepository restaurantRepository;

        RestaurantSearchServices(RestaurantSearchService restaurantSearchService,
                                 FindRestaurantById findRestaurantByIdUseCase,
                                 UpdateRestaurant updateRestaurantUseCase,
                                 RestaurantRepository restaurantRepository) {
            this.restaurantSearchService = restaurantSearchService;
            this.findRestaurantByIdUseCase = findRestaurantByIdUseCase;
            this.updateRestaurantUseCase = updateRestaurantUseCase;
            this.restaurantRepository = restaurantRepository;
        }
    }
}
