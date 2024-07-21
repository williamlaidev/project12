package interface_adapter;

import domain.RestaurantRepository;
import data_access.RestaurantDataAccess;
import entity.Location;
import entity.Restaurant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * An in-memory implementation of {@link RestaurantRepository} that manages restaurants using a list.
 * This class supports CRUD operations on restaurants and can persist data to a data source using a {@link RestaurantDataAccess} object.
 */
public class InMemoryRestaurantRepository implements RestaurantRepository {
    private final List<Restaurant> restaurantStore = new ArrayList<>();
    private final RestaurantDataAccess dataAccess;
    private boolean isDirty = false;

    /**
     * Constructs an {@link InMemoryRestaurantRepository} with the specified {@link RestaurantDataAccess} for data operations.
     *
     * @param dataAccess the {@link RestaurantDataAccess} instance used for loading and saving restaurant data.
     */
    public InMemoryRestaurantRepository(RestaurantDataAccess dataAccess) {
        this.dataAccess = dataAccess;
        this.restaurantStore.addAll(dataAccess.loadRestaurants());
    }

    /**
     * Retrieves a {@link Restaurant} by its unique identifier.
     *
     * @param id the unique identifier of the restaurant to retrieve.
     * @return an {@link Optional} containing the {@link Restaurant} if found, or an empty {@link Optional} if not.
     */
    @Override
    public Optional<Restaurant> getById(String id) {
        Optional<Restaurant> restaurant = restaurantStore.stream()
                .filter(r -> r.getRestaurantId().equals(id))
                .findFirst();

        if (restaurant.isPresent()) {
            System.out.println("Restaurant found: " + restaurant.get());
        } else {
            System.out.println("Restaurant with ID " + id + " not found.");
        }

        return restaurant;
    }

    /**
     * Retrieves a {@link Restaurant} by its name.
     *
     * @param name the name of the restaurant to retrieve.
     * @return an {@link Optional} containing the {@link Restaurant} if found, or an empty {@link Optional} if not.
     */
    @Override
    public Optional<Restaurant> getByName(String name) {
        Optional<Restaurant> restaurant = restaurantStore.stream()
                .filter(r -> r.getName().equalsIgnoreCase(name))
                .findFirst();

        if (restaurant.isPresent()) {
            System.out.println("Restaurant found: " + restaurant.get());
        } else {
            System.out.println("Restaurant with name " + name + " not found.");
        }

        return restaurant;
    }

    /**
     * Retrieves a {@link Restaurant} by its location.
     *
     * @param location the location of the restaurant to retrieve.
     * @return an {@link Optional} containing the {@link Restaurant} if found, or an empty {@link Optional} if not.
     */
    @Override
    public Optional<Restaurant> getByLocation(Location location) {
        Optional<Restaurant> restaurant = restaurantStore.stream()
                .filter(r -> r.getLocation().equals(location))
                .findFirst();

        if (restaurant.isPresent()) {
            System.out.println("Restaurant found: " + restaurant.get());
        } else {
            System.out.println("Restaurant at location " + location + " not found.");
        }

        return restaurant;
    }

    /**
     * Retrieves all {@link Restaurant} instances in the repository.
     *
     * @return a {@link List} of all {@link Restaurant} instances. If no restaurants are found, the list will be empty.
     */
    @Override
    public List<Restaurant> getAll() {
        List<Restaurant> restaurants = new ArrayList<>(restaurantStore);

        if (restaurants.isEmpty()) {
            System.out.println("No restaurants found.");
        } else {
            System.out.println("Restaurants found: " + restaurants);
        }

        return restaurants;
    }

    /**
     * Adds a new {@link Restaurant} to the repository.
     *
     * @param restaurant the {@link Restaurant} to add.
     * @return {@code true} if the restaurant was added successfully, or {@code false} if a restaurant with the same ID already exists.
     */
    @Override
    public boolean add(Restaurant restaurant) {
        String restaurantId = restaurant.getRestaurantId();
        System.out.println("Attempting to add a new restaurant with ID: " + restaurantId);

        // Check if a restaurant with the same ID already exists
        if (getById(restaurantId).isPresent()) {
            System.out.println("Restaurant with ID " + restaurantId + " already exists.");
            return false; // Restaurant with the same ID already exists
        }

        // Add the new restaurant to the store
        restaurantStore.add(restaurant);
        System.out.println("Restaurant with ID " + restaurantId + " successfully added.");
        isDirty = true;
        return true; // Successfully added the new restaurant
    }

    /**
     * Updates an existing {@link Restaurant} in the repository.
     *
     * @param restaurant the {@link Restaurant} with updated details.
     * @return {@code true} if the restaurant was updated successfully, or {@code false} if the restaurant does not exist.
     */
    @Override
    public boolean update(Restaurant restaurant) {
        Optional<Restaurant> existingRestaurantOpt = getById(restaurant.getRestaurantId());

        if (existingRestaurantOpt.isPresent()) {
            Restaurant existingRestaurant = existingRestaurantOpt.get();
            // Update existing restaurant details
            existingRestaurant.setName(restaurant.getName());
            existingRestaurant.setLocation(restaurant.getLocation());
            existingRestaurant.setAddress(restaurant.getAddress());
            existingRestaurant.setDishType(restaurant.getDishType());
            existingRestaurant.setAverageRating(restaurant.getAverageRating());
            existingRestaurant.setPhotoUrl(restaurant.getPhotoUrl());
            existingRestaurant.setSummarizedReview(restaurant.getSummarizedReview());
            isDirty = true;
            System.out.println("Restaurant updated successfully: " + existingRestaurant);
            return true;
        } else {
            System.out.println("Restaurant with ID " + restaurant.getRestaurantId() + " does not exist.");
            return false;
        }
    }

    /**
     * Deletes a {@link Restaurant} from the repository by its unique identifier.
     *
     * @param id the unique identifier of the restaurant to delete.
     * @return {@code true} if the restaurant was deleted successfully, or {@code false} if the restaurant does not exist.
     */
    @Override
    public boolean deleteById(String id) {
        Optional<Restaurant> restaurantOpt = getById(id);

        if (restaurantOpt.isPresent()) {
            boolean removed = restaurantStore.removeIf(restaurant -> restaurant.getRestaurantId().equals(id));

            if (removed) {
                isDirty = true;
                System.out.println("Restaurant with ID " + id + " was removed successfully.");
            } else {
                System.out.println("Failed to remove the restaurant with ID " + id + ".");
            }

            return removed;
        } else {
            System.out.println("Failed to remove the restaurant: Restaurant with ID " + id + " does not exist.");
            return false;
        }
    }

    /**
     * Deletes a {@link Restaurant} from the repository by its name.
     *
     * @param name the name of the restaurant to delete.
     * @return {@code true} if the restaurant was deleted successfully, or {@code false} if the restaurant does not exist.
     */
    @Override
    public boolean deleteByName(String name) {
        Optional<Restaurant> restaurantOpt = getByName(name);

        if (restaurantOpt.isPresent()) {
            boolean removed = restaurantStore.removeIf(r -> r.getName().equalsIgnoreCase(name));

            if (removed) {
                isDirty = true;
                System.out.println("Restaurant with name '" + name + "' was removed successfully.");
            } else {
                System.out.println("Failed to remove the restaurant with name '" + name + "'.");
            }

            return removed;
        } else {
            System.out.println("Failed to remove the restaurant: Restaurant with name '" + name + "' does not exist.");
            return false;
        }
    }

    /**
     * Saves the current state of the repository to the data source if any modifications have been made.
     * This method should be called to persist changes to the in-memory data store.
     */
    public void saveIfDirty() {
        if (isDirty) {
            dataAccess.saveRestaurants(restaurantStore);
            isDirty = false;
        }
    }
}
