package interface_adapter;

import use_case.SearchOutputBoundary;

public class SearchViewPresenter implements SearchOutputBoundary {

    private final SearchViewModel searchViewModel;

    public SearchViewPresenter(SearchViewModel searchViewModel) {
        this.searchViewModel = searchViewModel;
    }

    @Override
    public void prepareFailView(String error) {
        SearchViewState searchViewState = searchViewModel.getState();
        searchViewState.setDistanceError(error);
        searchViewModel.firePropertyChanged();
    }
}

