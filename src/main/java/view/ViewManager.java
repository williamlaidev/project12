package view;

import interface_adapter.view.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Manages view transitions within a CardLayout based on changes from the ViewManagerModel.
 */
public class ViewManager implements PropertyChangeListener {
    private final CardLayout cardLayout;
    private final JPanel views;

    /**
     * Initializes the ViewManager with a JPanel, CardLayout, and ViewManagerModel.
     *
     * @param views the container JPanel for different views
     * @param cardLayout the CardLayout managing view transitions
     * @param viewManagerModel the model notifying view changes
     */
    public ViewManager(JPanel views, CardLayout cardLayout, ViewManagerModel viewManagerModel) {
        this.views = views;
        this.cardLayout = cardLayout;
        viewManagerModel.addPropertyChangeListener(this);
    }

    /**
     * Updates the displayed view based on property changes from the ViewManagerModel.
     *
     * @param evt the property change event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("view".equals(evt.getPropertyName())) {
            String viewName = (String) evt.getNewValue();
            cardLayout.show(views, viewName);
        }
    }
}
