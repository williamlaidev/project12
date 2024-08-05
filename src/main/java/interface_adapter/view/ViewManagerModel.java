package interface_adapter.view;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Manages the active view within the application.
 * This model supports notifying observers when the active view changes,
 * facilitating the dynamic update of the UI based on user actions or system events.
 */
public class ViewManagerModel {
    private String activeViewName;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Retrieves the currently active view's name.
     * @return the name of the currently active view.
     */
    public String getActiveView() {
        return activeViewName;
    }

    /**
     * Sets the active view by its name and notifies observers about the change.
     * @param activeView the name of the view to be made active.
     */
    public void setActiveView(String activeView) {
        String oldView = this.activeViewName;
        this.activeViewName = activeView;
        support.firePropertyChange("activeView", oldView, activeView);  // Notify observers of the change
    }

    /**
     * Adds a property change listener to this model.
     * @param listener the PropertyChangeListener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Triggers an update to all registered listeners to inform them of a property change.
     * This method is primarily used to notify about the change in the active view.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("view", null, this.activeViewName);
    }
}



