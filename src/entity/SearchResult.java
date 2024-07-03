package entity;
import java.util.Arrays;

public class SearchResult {
    // Array to store the list of restaurants
    private Restaurant[] restaurantList;

    // Constructor
    public SearchResult(Restaurant[] restaurantList) {
        this.restaurantList = restaurantList;
    }

    // Getter for restaurantList
    public Restaurant[] getRestaurantList() {
        return restaurantList;
    }

    // Setter for restaurantList
    public void setRestaurantList(Restaurant[] restaurantList) {
        this.restaurantList = restaurantList;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return "SearchResult{" +
                "restaurantList=" + Arrays.toString(restaurantList) +
                '}';
    }
