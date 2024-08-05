package view;

import interface_adapter.view.RestaurantViewModel;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class RestaurantView extends JPanel implements PropertyChangeListener {
    private RestaurantViewModel viewModel;
    private JPanel buttonsPanel;

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

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(buttonsPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(300, 650));
        add(scrollPane, BorderLayout.CENTER);

        updateRestaurantButtons();
    }

    private void updateRestaurantButtons() {
        List<String> restaurantInfos = viewModel.getState().getRestaurantsInfo();
        buttonsPanel.removeAll();
        if (restaurantInfos != null) {
            for (String info : restaurantInfos) {
                JButton button = new JButton("<html>" + info.replaceAll(" - ", "<br/>") + "</html>"); // Using HTML for multiline text
                button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
                button.setAlignmentX(Component.LEFT_ALIGNMENT);
                buttonsPanel.add(button);
            }
        } else {
            buttonsPanel.add(new JLabel("No restaurants available."));
        }
        buttonsPanel.revalidate();
        buttonsPanel.repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            updateRestaurantButtons();
        }
    }
}
