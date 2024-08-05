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

    private SearchState state;
    private int zoomLevel;
    private boolean resultViewVisible; // Flag to control the visibility of the ResultView
    private final PropertyChangeSupport changeSupport;

    public SearchViewModel() {
        super("search");
        this.state = new SearchState();
        this.zoomLevel = 15; // Initial zoom level
        this.resultViewVisible = false; // Initially, the ResultView is not visible
        this.changeSupport = new PropertyChangeSupport(this);
    }

    public void setState(SearchState state) {
        this.state = state;
        firePropertyChanged(); // Notify observers about state change
    }

    public void setZoomLevel(int zoomLevel) {
        this.zoomLevel = zoomLevel;
        firePropertyChanged(); // Notify observers about zoom level change
    }

    public void setResultViewVisible(boolean visible) {
        boolean oldVisible = this.resultViewVisible;
        this.resultViewVisible = visible;
        changeSupport.firePropertyChange("resultViewVisible", oldVisible, visible); // Notify about visibility change
    }

    public int getZoomLevel() {
        return zoomLevel;
    }

    public boolean isResultViewVisible() {
        return resultViewVisible;
    }

    public SearchState getState() {
        return state;
    }

    @Override
    public void firePropertyChanged() {
        // This method triggers updates in the view layer
        changeSupport.firePropertyChange("state", null, this.state);
        changeSupport.firePropertyChange("zoomLevel", null, this.zoomLevel);
        changeSupport.firePropertyChange("resultViewVisible", null, this.resultViewVisible);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }
}
