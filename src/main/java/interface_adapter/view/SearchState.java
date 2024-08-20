package interface_adapter.view;

import entity.restaurant.Restaurant;

import java.awt.Point;
import java.util.List;

/**
 * Represents the state of the search operations in the view layer, including user interactions
 * such as mouse click positions, search distance, dish type, and potential errors.
 */
public class SearchState {

    private Point mouseLeftClickPosition;
    private Point mouseRightClickPosition;
    private String distance;
    private String selectedDishType;
    private String distanceError;
    private List<Restaurant> restaurants;
    private double centerLat;
    private double centerLon;
    private int mapWidth;
    private int mapHeight;
    private int zoomLevel;

    /**
     * Constructs a new SearchState by copying the values from another SearchState instance.
     * @param copy The SearchState instance to copy from.
     */
    public SearchState(SearchState copy) {
        this.mouseLeftClickPosition = copy.mouseLeftClickPosition;
        this.mouseRightClickPosition = copy.mouseRightClickPosition;
        this.distance = copy.distance;
        this.selectedDishType = copy.selectedDishType;
        this.distanceError = copy.distanceError;
        this.restaurants = copy.restaurants;
        this.centerLat = copy.centerLat;
        this.centerLon = copy.centerLon;
        this.mapWidth = copy.mapWidth;
        this.mapHeight = copy.mapHeight;
        this.zoomLevel = copy.zoomLevel;
    }

    /**
     * Constructs a new, empty SearchState.
     */
    public SearchState() {}

    /**
     * Gets the position of the left mouse click.
     * @return The Point representing the left mouse click position.
     */
    public Point getMouseLeftClickPosition() {
        return mouseLeftClickPosition;
    }

    /**
     * Sets the position of the left mouse click.
     * @param mouseLeftClickPosition The Point representing the new left mouse click position.
     */
    public void setMouseLeftClickPosition(Point mouseLeftClickPosition) {
        this.mouseLeftClickPosition = mouseLeftClickPosition;
    }

    /**
     * Gets the position of the right mouse click.
     * @return The Point representing the right mouse click position.
     */
    public Point getMouseRightClickPosition() {
        return mouseRightClickPosition;
    }

    /**
     * Sets the position of the right mouse click.
     * @param mouseRightClickPosition The Point representing the new right mouse click position.
     */
    public void setMouseRightClickPosition(Point mouseRightClickPosition) {
        this.mouseRightClickPosition = mouseRightClickPosition;
    }

    /**
     * Gets the selected search distance.
     * @return The selected search distance.
     */
    public String getDistance() {
        return distance;
    }

    /**
     * Sets the selected search distance.
     * @param distance The new search distance to be set.
     */
    public void setDistance(String distance) {
        this.distance = distance;
    }

    /**
     * Gets the selected dish type.
     * @return The selected dish type.
     */
    public String getSelectedDishType() {
        return selectedDishType;
    }

    /**
     * Sets the selected dish type.
     * @param selectedDishType The new dish type to be set.
     */
    public void setSelectedDishType(String selectedDishType) {
        this.selectedDishType = selectedDishType;
    }

    /**
     * Gets any error related to the search distance.
     * @return The error message related to the distance.
     */
    public String getDistanceError() {
        return distanceError;
    }

    /**
     * Sets an error related to the search distance.
     * @param distanceError The error message to be set.
     */
    public void setDistanceError(String distanceError) {
        this.distanceError = distanceError;
    }

    /**
     * Gets the list of restaurants for the current search state.
     * @return The list of restaurants.
     */
    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    /**
     * Sets the list of restaurants for the current search state.
     * @param restaurants The new list of restaurants to be set.
     */
    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    /**
     * Returns a string representation of the SearchState.
     * @return A string representation of the current state.
     */

    public double getCenterLat() {
        return centerLat;
    }

    public void setCenterLat(double centerLat) {
        this.centerLat = centerLat;
    }

    public double getCenterLon() {
        return centerLon;
    }

    public void setCenterLon(double centerLon) {
        this.centerLon = centerLon;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    public int getZoomLevel() {
        return zoomLevel;
    }

    public void setZoomLevel(int zoomLevel) {
        this.zoomLevel = zoomLevel;
    }

    @Override
    public String toString() {
        return "SearchState{" +
                "mouseLeftClickPosition=" + mouseLeftClickPosition +
                ", mouseRightClickPosition=" + mouseRightClickPosition +
                ", distance='" + distance + '\'' +
                ", selectedDishType='" + selectedDishType + '\'' +
                ", distanceError='" + distanceError + '\'' +
                ", restaurants=" + restaurants +
                '}';
    }
}