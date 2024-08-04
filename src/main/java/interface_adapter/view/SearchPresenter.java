package interface_adapter.view;

import use_case.view.SearchOutputBoundary;

/**
 * Presenter class for the search functionality, bridging the use case output with the view model.
 */
public class SearchPresenter implements SearchOutputBoundary {
    private final SearchViewModel searchViewModel;

    public SearchPresenter(SearchViewModel searchViewModel) {
        this.searchViewModel = searchViewModel;
    }


    public void updateZoomLevel(int zoomLevel) {
        searchViewModel.setZoomLevel(zoomLevel);
        searchViewModel.firePropertyChanged();  // Notify observers of the change
    }

    @Override
    public void prepareFailView(String error) {
        SearchViewState searchViewState = searchViewModel.getState();
        searchViewState.setDistanceError(error);
        searchViewModel.firePropertyChanged();
    }
}
