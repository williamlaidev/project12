package interface_adapter.view;

import java.awt.Point;

/**
 * Holds the state information for search operations in the view layer, including
 * user interactions such as selected location, search distance, dish type, and zoom level.
 */
public class SearchViewState {
    private Point mousePosition;
    private String distance;
    private String selectedDishType;
    private String distanceError;
    private int zoomLevel;  // Adding zoom level to the state

    // Copy constructor
    public SearchViewState(SearchViewState copy) {
        this.mousePosition = copy.mousePosition;
        this.distance = copy.distance;
        this.selectedDishType = copy.selectedDishType;
        this.distanceError = copy.distanceError;
        this.zoomLevel = copy.zoomLevel;
    }

    // Default constructor
    public SearchViewState() {}

    public Point getMousePosition() {
        return mousePosition;
    }

    public void setMousePosition(Point mousePosition) {
        this.mousePosition = mousePosition;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getSelectedDishType() {
        return selectedDishType;
    }

    public void setSelectedDishType(String selectedDishType) {
        this.selectedDishType = selectedDishType;
    }

    public String getDistanceError() {
        return distanceError;
    }

    public void setDistanceError(String distanceError) {
        this.distanceError = distanceError;
    }

    public int getZoomLevel() {
        return zoomLevel;
    }

    public void setZoomLevel(int zoomLevel) {
        this.zoomLevel = zoomLevel;
    }

    @Override
    public String toString() {
        return "SearchViewState{" +
                "mousePosition=" + mousePosition +
                ", distance='" + distance + '\'' +
                ", selectedDishType='" + selectedDishType + '\'' +
                ", distanceError='" + distanceError + '\'' +
                ", zoomLevel=" + zoomLevel +
                '}';
    }
}
