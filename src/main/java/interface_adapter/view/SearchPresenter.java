package interface_adapter.view;

import use_case.view.SearchOutputBoundary;
import use_case.view.SearchOutputData;

/**
 * Presenter class for the search functionality, bridging the use case output with the view model.
 */
public class SearchPresenter implements SearchOutputBoundary {
    private final SearchViewModel searchViewModel;
    private final RestaurantViewModel restaurantViewModel;
    private ViewManagerModel viewManagerModel;

    public SearchPresenter(ViewManagerModel viewManagerModel,SearchViewModel searchViewModel, RestaurantViewModel restaurantViewModel) {

        this.searchViewModel = searchViewModel;
        this.restaurantViewModel = restaurantViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void setZoomLevel(int zoomLevel) {
        searchViewModel.setZoomLevel(zoomLevel);
    }

    @Override
    public void prepareFailView(String error) {
        SearchState state = searchViewModel.getState();
        state.setDistanceError(error);
        searchViewModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(SearchOutputData result) {
        // Update RestaurantViewModel with results
        RestaurantState restaurantState = restaurantViewModel.getState();
        restaurantState.setRestaurantId(result.getRestaurantsId());
        restaurantViewModel.setState(restaurantState);
        restaurantViewModel.firePropertyChanged();

        // Switch to RestaurantView
        viewManagerModel.setActiveView(restaurantViewModel.getViewName());
    }
}
