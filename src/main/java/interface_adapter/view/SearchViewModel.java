package interface_adapter.view;

import entity.restaurant.Restaurant;

import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ViewModel for managing the state and interactions for the SearchView component.
 */
public class SearchViewModel extends ViewModel {

    // UI component labels
    public final String TITLE_LABEL = "Search View";
    public final String DISTANCE_LABEL = "Choose distance";
    public final String DISH_TYPE_LABEL = "Choose dish type";
    public final String SEARCH_BUTTON_LABEL = "Search";
    public final String ZOOM_LABEL = "Zoom Level";

    private SearchState state;
    private int zoomLevel;
    private boolean resultViewVisible; // Flag to control the visibility of the ResultView
    private final PropertyChangeSupport changeSupport;
    private final Map<Point, String> mapMarkers; // To hold map markers for restaurants

    /**
     * Constructs a SearchViewModel with initial values.
     */
    public SearchViewModel() {
        super("search");
        this.state = new SearchState();
        this.zoomLevel = 15; // Initial zoom level
        this.resultViewVisible = false; // Initially, the ResultView is not visible
        this.mapMarkers = new HashMap<>();
        this.changeSupport = new PropertyChangeSupport(this);
    }

    /**
     * Sets the current search state.
     * @param state The new SearchState to be set.
     */
    public void setState(SearchState state) {
        this.state = state;
        firePropertyChanged(); // Notify observers about state change
    }

    /**
     * Sets the zoom level for the map view.
     * @param zoomLevel The new zoom level.
     */
    public void setZoomLevel(int zoomLevel) {
        this.zoomLevel = zoomLevel;
        firePropertyChanged(); // Notify observers about zoom level change
    }

    /**
     * Sets the visibility of the result view.
     * @param visible True if the result view should be visible, false otherwise.
     */
    public void setResultViewVisible(boolean visible) {
        boolean oldVisible = this.resultViewVisible;
        this.resultViewVisible = visible;
        changeSupport.firePropertyChange("resultViewVisible", oldVisible, visible); // Notify about visibility change
    }

    /**
     * Adds a map marker at the specified location.
     * @param location The location on the map where the marker will be placed.
     * @param label The label for the marker.
     */
    public void addMapMarker(Point location, String label) {
        mapMarkers.put(location, label);
        firePropertyChanged(); // Notify observers about the new map marker
    }

    /**
     * Returns the current map markers.
     * @return A map of markers with their locations and labels.
     */
    public Map<Point, String> getMapMarkers() {
        return mapMarkers;
    }

    /**
     * Returns the current zoom level.
     * @return The zoom level.
     */
    public int getZoomLevel() {
        return zoomLevel;
    }

    /**
     * Checks if the result view is currently visible.
     * @return True if the result view is visible, false otherwise.
     */
    public boolean isResultViewVisible() {
        return resultViewVisible;
    }

    /**
     * Returns the current search state.
     * @return The current SearchState.
     */
    public SearchState getState() {
        return state;
    }

    /**
     * Clears all map markers.
     */
    public void clearMapMarkers() {
        this.mapMarkers.clear();
        changeSupport.firePropertyChange("mapMarkers", null, null); // Notify observers about the change
    }

    /**
     * Fires property change events to notify observers of changes in state.
     */
    @Override
    public void firePropertyChanged() {
        changeSupport.firePropertyChange("state", null, this.state);
        changeSupport.firePropertyChange("zoomLevel", null, this.zoomLevel);
        changeSupport.firePropertyChange("resultViewVisible", null, this.resultViewVisible);
        changeSupport.firePropertyChange("mapMarkers", null, this.mapMarkers);
    }

    /**
     * Adds a property change listener to receive property change events.
     * @param listener The PropertyChangeListener to be added.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Sets the list of restaurants in the search state.
     * @param restaurants The list of restaurants to be set.
     */
    public void setRestaurants(List<Restaurant> restaurants) {
        this.state.setRestaurants(restaurants); // Assuming you have a method to set the list of restaurants in `SearchState`
        firePropertyChanged(); // Notify observers about the change
    }
}
