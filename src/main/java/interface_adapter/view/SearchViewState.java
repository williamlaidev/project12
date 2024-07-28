package interface_adapter.view;

import java.awt.Point;

public class SearchViewState {
    private Point mousePosition;
    private String distance;
    private String selectedDishType;
    private String distanceError;

    // Copy constructor
    public SearchViewState(SearchViewState copy) {
        this.mousePosition = copy.mousePosition;
        this.distance = copy.distance;
        this.selectedDishType = copy.selectedDishType;
        this.distanceError = copy.distanceError;
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

    @Override
    public String toString() {
        return "SearchViewState{" +
                "mousePosition=" + mousePosition +
                ", distance='" + distance + '\'' +
                ", selectedDishType='" + selectedDishType + '\'' +
                ", distanceError='" + distanceError + '\'' +
                '}';
    }
}
