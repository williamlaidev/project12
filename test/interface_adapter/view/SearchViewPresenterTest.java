package interface_adapter.view;

import domain.SearchOutputBoundary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SearchViewPresenterTest {

    private SearchViewPresenter presenter;
    private SearchViewModel searchViewModel;

    @BeforeEach
    public void setUp() {
        searchViewModel = mock(SearchViewModel.class);
        presenter = new SearchViewPresenter(searchViewModel);
    }

    @Test
    public void testPrepareFailView() {
        String errorMessage = "An error occurred";
        SearchViewState searchViewState = new SearchViewState();
        when(searchViewModel.getState()).thenReturn(searchViewState);

        presenter.prepareFailView(errorMessage);

        verify(searchViewModel, times(1)).getState();
        assertEquals(errorMessage, searchViewState.getDistanceError());
        verify(searchViewModel, times(1)).firePropertyChanged();
    }
}
