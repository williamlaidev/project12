package interface_adapter.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SearchViewModelTest {

    private SearchViewModel searchViewModel;
    private PropertyChangeListener listener;

    @BeforeEach
    public void setUp() {
        searchViewModel = new SearchViewModel();
        listener = mock(PropertyChangeListener.class);
    }

    @Test
    public void testViewName() {
        assertEquals("framework/config/search", searchViewModel.getViewName());
    }

    @Test
    public void testSetState() {
        SearchViewState state = new SearchViewState();
        state.setDistance("500");
        state.setSelectedDishType("CHINESE");

        searchViewModel.setState(state);

        assertEquals("500", searchViewModel.getState().getDistance());
        assertEquals("CHINESE", searchViewModel.getState().getSelectedDishType());
    }

    @Test
    public void testFirePropertyChanged() {
        searchViewModel.addPropertyChangeListener(listener);
        searchViewModel.firePropertyChanged();

        verify(listener, times(1)).propertyChange(any());
    }

    @Test
    public void testAddPropertyChangeListener() {
        searchViewModel.addPropertyChangeListener(listener);

        PropertyChangeSupport changeSupport = new PropertyChangeSupport(searchViewModel);
        changeSupport.addPropertyChangeListener(listener);

        assertEquals(1, changeSupport.getPropertyChangeListeners().length);
    }

    @Test
    public void testGetState() {
        SearchViewState state = new SearchViewState();
        searchViewModel.setState(state);

        assertEquals(state, searchViewModel.getState());
    }
}
