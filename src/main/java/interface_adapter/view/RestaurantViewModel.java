package interface_adapter.view;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for managing the state and properties of the restaurant view.
 * Provides methods to get and set the restaurant state and to notify listeners
 * about changes in the state.
 */
public class RestaurantViewModel extends ViewModel {

    /**
     * The title label for the restaurant view.
     */
    public final String TITLE_LABEL = "Restaurant View";

    private RestaurantState restaurantState = new RestaurantState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a RestaurantViewModel with the default title.
     */
    public RestaurantViewModel() {
        super("Restaurants");
    }

    /**
     * Sets the current state of the restaurant.
     *
     * @param state The new state of the restaurant.
     */
    public void setState(RestaurantState state) {
        this.restaurantState = state;
    }

    /**
     * Notifies all registered listeners that the state has changed.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.restaurantState);
    }

    /**
     * Adds a listener to be notified of property changes.
     *
     * @param listener The listener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Returns the current state of the restaurant.
     *
     * @return The current state of the restaurant.
     */
    public RestaurantState getState() {
        return restaurantState;
    }
}
