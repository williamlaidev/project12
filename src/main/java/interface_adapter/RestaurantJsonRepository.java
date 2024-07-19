package interface_adapter;

import entity.Restaurant;
import framework.JsonFileHandler;

import java.util.List;

public class RestaurantJsonRepository implements RestaurantRepository {
    private final JsonFileHandler jsonFileHandler;
    private List<Restaurant> restaurantList;

    public RestaurantJsonRepository(String filePath) {
        this.jsonFileHandler = new JsonFileHandler(filePath);
        this.restaurantList = jsonFileHandler.readFromFile();
    }

    private void saveToFile() {
        jsonFileHandler.writeToFile(restaurantList);
    }

    @Override
    public Restaurant getRestaurantById(String restaurantId) {
        System.out.println("Searching for restaurant with ID: " + restaurantId);
        Restaurant restaurant = restaurantList.stream()
                .filter(r -> r.getRestaurantId().equals(restaurantId))
                .findFirst()
                .orElse(null);
        if (restaurant != null) {
            System.out.println("Found restaurant with ID: " + restaurantId);
        } else {
            System.out.println("No restaurant found with ID: " + restaurantId);
        }
        return restaurant;
    }

    @Override
    public Restaurant getRestaurantByName(String name) {
        System.out.println("Searching for restaurant with name: " + name);
        Restaurant restaurant = restaurantList.stream()
                .filter(r -> r.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
        if (restaurant != null) {
            System.out.println("Found restaurant with name: " + name);
        } else {
            System.out.println("No restaurant found with name: " + name);
        }
        return restaurant;
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        System.out.println("Retrieving all restaurants. Total count: " + restaurantList.size());
        return restaurantList;
    }

    @Override
    public boolean saveRestaurant(Restaurant restaurant) {
        if (getRestaurantById(restaurant.getRestaurantId()) != null) {
            System.out.println("Restaurant with ID " + restaurant.getRestaurantId() + " already exists.");
            return false; // Restaurant with the same ID already exists
        }
        restaurantList.add(restaurant);
        saveToFile();
        System.out.println("Saved new restaurant with ID: " + restaurant.getRestaurantId());
        return true;
    }

    @Override
    public boolean updateRestaurant(Restaurant restaurant) {
        for (int i = 0; i < restaurantList.size(); i++) {
            if (restaurantList.get(i).getRestaurantId().equals(restaurant.getRestaurantId())) {
                restaurantList.set(i, restaurant);
                saveToFile();
                System.out.println("Updated restaurant with ID: " + restaurant.getRestaurantId());
                return true;
            }
        }
        System.out.println("No restaurant found with ID: " + restaurant.getRestaurantId() + " to update.");
        return false; // Restaurant with the given ID not found
    }

    @Override
    public boolean deleteRestaurantById(String restaurantId) {
        boolean removed = restaurantList.removeIf(restaurant -> restaurant.getRestaurantId().equals(restaurantId));
        if (removed) {
            saveToFile();
            System.out.println("Deleted restaurant with ID: " + restaurantId);
        } else {
            System.out.println("No restaurant found with ID: " + restaurantId + " to delete.");
        }
        return removed;
    }

    @Override
    public boolean deleteRestaurantByName(String name) {
        boolean removed = restaurantList.removeIf(restaurant -> restaurant.getName().equalsIgnoreCase(name));
        if (removed) {
            saveToFile();
            System.out.println("Deleted restaurant with name: " + name);
        } else {
            System.out.println("No restaurant found with name: " + name + " to delete.");
        }
        return removed;
    }
}
