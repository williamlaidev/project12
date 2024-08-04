package interface_adapter.view;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for managing the state and interactions for the SearchView component.
 */
public class SearchViewModel extends ViewModel {
    public final String TITLE_LABEL = "Search View";
    public final String DISTANCE_LABEL = "Choose distance";
    public final String DISH_TYPE_LABEL = "Choose dish type";
    public final String SEARCH_BUTTON_LABEL = "Search";
    public final String ZOOM_LABEL = "Zoom Level";
    private SearchViewState state;
    private int zoomLevel;
    private final PropertyChangeSupport changeSupport;

    public SearchViewModel() {
        super("search");
        this.state = new SearchViewState();
        this.changeSupport = new PropertyChangeSupport(this);
    }

    public void setState(SearchViewState state) {
        this.state = state;
        firePropertyChanged(); // Notify observers about state change
    }

    public void setZoomLevel(int zoomLevel) {
        this.zoomLevel = zoomLevel;
        firePropertyChanged(); // Notify observers about zoom level change
    }

    public int getZoomLevel(){
        return zoomLevel;
    }

    public SearchViewState getState() {
        return state;
    }

    @Override
    public void firePropertyChanged() {
        // This method triggers updates in the view layer
        changeSupport.firePropertyChange("state", null, this.state);
        changeSupport.firePropertyChange("zoomLevel", null, this.zoomLevel);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }
}

