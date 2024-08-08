package interface_adapter.view;

import java.beans.PropertyChangeListener;

/**
 * Abstract class representing a ViewModel.
 * Provides basic functionality for managing view names and property change listeners.
 */
public abstract class ViewModel {

    private final String viewName;

    public ViewModel(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return this.viewName;
    }

    /**
     * Abstract method to notify observers about property changes.
     */
    public abstract void firePropertyChanged();

    /**
     * Abstract method to add a property change listener.
     * @param listener the PropertyChangeListener to add.
     */
    public abstract void addPropertyChangeListener(PropertyChangeListener listener);
}
