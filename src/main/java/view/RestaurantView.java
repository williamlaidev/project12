package view;

import interface_adapter.view.RestaurantViewModel;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;


/**
 * A view component for displaying restaurant IDs.
 */
public class RestaurantView extends JPanel implements PropertyChangeListener {
    private RestaurantViewModel viewModel;
    private JTextArea restaurantListArea;

    public RestaurantView(RestaurantViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel(viewModel.TITLE_LABEL);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        restaurantListArea = new JTextArea(10, 30);
        restaurantListArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(restaurantListArea);
        add(scrollPane, BorderLayout.CENTER);

        updateRestaurantList();
    }

    private void updateRestaurantList() {
        List<String> restaurants = viewModel.getState().getRestaurantId();
        restaurantListArea.setText(""); // Clear previous content
        if (restaurants != null && !restaurants.isEmpty()) {
            for (String id : restaurants) {
                restaurantListArea.append(id + "\n");
            }
        } else {
            restaurantListArea.append("No restaurant IDs available.\n");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            updateRestaurantList();
        }
    }
}