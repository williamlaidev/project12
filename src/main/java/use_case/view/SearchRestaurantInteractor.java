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

public class SearchRestaurantInteractor {

    private SearchPresenter presenter;
    private static final Logger logger = LoggerFactory.getLogger(RestaurantSearchInteractor.class);

    public SearchRestaurantInteractor(SearchPresenter presenter) {
        this.presenter = presenter;
    }

    public void execute(SearchRestaurantInput searchInput, int maxRestaurantsToSearch, int maxResults, RestaurantSearchInteractor restaurantSearchInteractor){
        List<Restaurant> results = restaurantSearchInteractor.fetchNearbyRestaurants(searchInput, maxRestaurantsToSearch, maxResults);

        if (results != null) {
            SearchOutputData searchOutputData = new SearchOutputData(results);
            presenter.prepareSuccessView(searchOutputData);
        }
        else {
            presenter.prepareFailureView("Didn't find any restaurant with current search center, distance, and dish type.");
        }



    }
}
