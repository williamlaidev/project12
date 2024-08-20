package use_case.view;

import entity.DishType;
import entity.restaurant.Restaurant;
import interface_adapter.view.SearchPresenter;
import interface_adapter.view.SearchState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import use_case.search.RestaurantSearchInteractor;
import use_case.search.SearchRestaurantInput;

import java.util.List;

/**
 * Interactor class for handling restaurant search operations.
 * This class is responsible for executing search queries and communicating the results
 * back to the presenter.
 */
public class SearchRestaurantInteractor {

    private SearchPresenter presenter;
    private static final Logger logger = LoggerFactory.getLogger(RestaurantSearchInteractor.class);

    /**
     * Constructs a SearchRestaurantInteractor with a given SearchPresenter.
     *
     * @param presenter The presenter associated with this interactor, used for callback.
     */
    public SearchRestaurantInteractor(SearchPresenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Executes the search for restaurants based on the provided search criteria.
     * This method uses another interactor to fetch the actual restaurant data.
     *
     * @param searchInput The input criteria for searching restaurants.
     * @param maxRestaurantsToSearch The maximum number of restaurant entries to search.
     * @param maxResults The maximum number of results to return.
     * @param restaurantSearchInteractor The interactor responsible for fetching restaurant data.
     */
    public void execute(SearchRestaurantInput searchInput, int maxRestaurantsToSearch, int maxResults, RestaurantSearchInteractor restaurantSearchInteractor){
        List<Restaurant> results = restaurantSearchInteractor.fetchNearbyRestaurants(searchInput, maxRestaurantsToSearch, maxResults);

        if (results != null && !results.isEmpty()) {
            SearchOutputData searchOutputData = new SearchOutputData(results);
            presenter.prepareSuccessView(searchOutputData);
        } else {
            presenter.prepareFailureView("Didn't find any restaurant with current search center, distance, and dish type.");
        }
    }
}
