package interface_adapter.view;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RestaurantViewModel extends ViewModel {
    public final String TITLE_LABEL = "Restaurant View";

    private RestaurantState restaurantState = new RestaurantState();

    public RestaurantViewModel() {super("Restaurants");}

    public void setState(RestaurantState state) {
        this.restaurantState = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.restaurantState);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public RestaurantState getState() {
        return restaurantState;
    }
}
