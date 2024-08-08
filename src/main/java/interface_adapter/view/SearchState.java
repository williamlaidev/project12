package interface_adapter.view;

import entity.restaurant.Restaurant;

import java.awt.Point;
import java.util.List;

/**
 * Holds the state information for search operations in the view layer, including
 * user interactions such as selected location, search distance, dish type, and zoom level.
 */
public class SearchState {
    private Point mouseLeftClickPosition;
    private Point mouseRightClickPosition;
    private String distance;
    private String selectedDishType;
    private String distanceError;
    private List<Restaurant> restaurants;

    // Copy constructor
    public SearchState(SearchState copy) {
        this.mouseLeftClickPosition = copy.mouseLeftClickPosition;
        this.mouseRightClickPosition = copy.mouseRightClickPosition;
        this.distance = copy.distance;
        this.selectedDishType = copy.selectedDishType;
        this.distanceError = copy.distanceError;
        this.restaurants = copy.restaurants;
    }

    // Default constructor
    public SearchState() {}

    public Point getMouseLeftClickPosition() {
        return mouseLeftClickPosition;
    }

    public void setMouseLeftClickPosition(Point mouseLeftClickPosition) {this.mouseLeftClickPosition = mouseLeftClickPosition;}

    public Point getMouseRightClickPosition() {
        return mouseRightClickPosition;
    }
    public void setMouseRightClickPosition(Point mouseRightClickPosition) {
        this.mouseRightClickPosition = mouseRightClickPosition;
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

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public String toString() {
        return "SearchState{" +
                "mouseLeftClickPosition=" + mouseLeftClickPosition +
                ", distance='" + distance + '\'' +
                ", selectedDishType='" + selectedDishType + '\'' +
                ", distanceError='" + distanceError + '\'' +
                '}';
    }
}
