package interface_adapter;

import domain.RestaurantRepository;
import data_access.RestaurantDataAccess;
import entity.Location;
import entity.Restaurant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * In-memory implementation of {@link RestaurantRepository} that stores restaurants in a list.
 * This class supports CRUD operations on restaurants and can persist data to a data source if needed.
 */
public class InMemoryRestaurantRepository implements RestaurantRepository {
    private final List<Restaurant> restaurantStore = new ArrayList<>();
    private final RestaurantDataAccess dataAccess;
    private boolean isDirty = false;

    /**
     * Constructs an {@link InMemoryRestaurantRepository} with the specified data access object.
     *
     * @param dataAccess the {@link RestaurantDataAccess} used for loading and saving restaurant data.
     */
    public InMemoryRestaurantRepository(RestaurantDataAccess dataAccess) {
        this.dataAccess = dataAccess;
        this.restaurantStore.addAll(dataAccess.loadRestaurants());
    }

    @Override
    public Optional<Restaurant> getById(String id) {
        return restaurantStore.stream()
                .filter(restaurant -> restaurant.getRestaurantId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Restaurant> getByName(String name) {
        return restaurantStore.stream()
                .filter(restaurant -> restaurant.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    @Override
    public Optional<Restaurant> getByLocation(Location location) {
        return restaurantStore.stream()
                .filter(restaurant -> restaurant.getLocation().equals(location))
                .findFirst();
    }

    @Override
    public List<Restaurant> getAll() {
        return new ArrayList<>(restaurantStore);
    }

    @Override
    public boolean add(Restaurant restaurant) {
        if (getById(restaurant.getRestaurantId()).isPresent()) {
            return false; // Restaurant with the same ID already exists
        }
        boolean added = restaurantStore.add(restaurant);
        isDirty = true;
        return true;
    }

    @Override
    public boolean update(Restaurant restaurant) {
        Optional<Restaurant> existingRestaurantOpt = getById(restaurant.getRestaurantId());
        if (existingRestaurantOpt.isPresent()) {
            Restaurant existingRestaurant = existingRestaurantOpt.get();
            // Updating existing restaurant details
            existingRestaurant.setName(restaurant.getName());
            existingRestaurant.setLocation(restaurant.getLocation());
            existingRestaurant.setAddress(restaurant.getAddress());
            existingRestaurant.setDishType(restaurant.getDishType());
            existingRestaurant.setAverageRating(restaurant.getAverageRating());
            existingRestaurant.setPhotoUrl(restaurant.getPhotoUrl());
            existingRestaurant.setSummarizedReview(restaurant.getSummarizedReview());
            isDirty = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        boolean removed = restaurantStore.removeIf(restaurant -> restaurant.getRestaurantId().equals(id));
        if (removed) {
            isDirty = true;
        }
        return removed;
    }

    @Override
    public boolean deleteByName(Restaurant restaurant) {
        boolean removed = restaurantStore.removeIf(r -> r.getName().equalsIgnoreCase(restaurant.getName()));
        if (removed) {
            isDirty = true;
        }
        return removed;
    }

    /**
     * Saves the current state of the repository to the data source if changes have been made.
     * This method is called to persist any updates made to the in-memory data store.
     */
    public void saveIfDirty() {
        if (isDirty) {
            dataAccess.saveRestaurants(restaurantStore);
            isDirty = false;
        }
    }
}
