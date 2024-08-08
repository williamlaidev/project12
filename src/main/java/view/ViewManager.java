package view;

import interface_adapter.view.ViewManagerModel;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * ViewManager is responsible for managing different views within a CardLayout.
 * It listens for property changes from the ViewManagerModel to switch between views.
 */
public class ViewManager implements PropertyChangeListener {
    private final CardLayout cardLayout;
    private final JPanel views;

    /**
     * Constructs a ViewManager with the specified JPanel, CardLayout, and ViewManagerModel.
     *
     * @param views the JPanel that contains the different views
     * @param cardLayout the CardLayout used to manage the views
     * @param viewManagerModel the model that notifies of view changes
     */
    public ViewManager(JPanel views, CardLayout cardLayout, ViewManagerModel viewManagerModel) {
        this.views = views;
        this.cardLayout = cardLayout;
        viewManagerModel.addPropertyChangeListener(this);
    }

    /**
     * Responds to property changes from the ViewManagerModel. When the "view" property changes,
     * it updates the CardLayout to show the appropriate view.
     *
     * @param evt the property change event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("view".equals(evt.getPropertyName())) {
            String viewModelName = (String) evt.getNewValue();
            cardLayout.show(views, viewModelName);
        }
    }
}
