package interface_adapter;

import java.awt.Point;

public class SearchViewState {
    private String distance = "";
    private String distanceError = null;
    private String selectedDishType = "";
    private String dishTypeError = null;
    private Point mousePosition = null;
    private String mousePositionError = null;

    public SearchViewState(SearchViewState copy) {
        distance = copy.distance;
        distanceError = copy.distanceError;
        selectedDishType = copy.selectedDishType;
        dishTypeError = copy.dishTypeError;
        mousePosition = copy.mousePosition;
        mousePositionError = copy.mousePositionError;
    }

    public SearchViewState() {}

    public String getDistance() {
        return distance;
    }

    public String getDistanceError() {
        return distanceError;
    }

    public String getSelectedDishType() {
        return selectedDishType;
    }

    public String getDishTypeError() {
        return dishTypeError;
    }

    public Point getMousePosition() {
        return mousePosition;
    }

    public String getMousePositionError() {
        return mousePositionError;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setDistanceError(String distanceError) {
        this.distanceError = distanceError;
    }

    public void setSelectedDishType(String selectedDishType) {
        this.selectedDishType = selectedDishType;
    }

    public void setDishTypeError(String dishTypeError) {
        this.dishTypeError = dishTypeError;
    }

    public void setMousePosition(Point mousePosition) {
        this.mousePosition = mousePosition;
    }

    public void setMousePositionError(String mousePositionError) {
        this.mousePositionError = mousePositionError;
    }
}
