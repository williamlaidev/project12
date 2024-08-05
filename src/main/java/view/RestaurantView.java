package view;

import interface_adapter.view.RestaurantViewModel;
import utils.FetchImageIcon;

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
                String[] parts = info.split(" - ");
                JButton button = new JButton("<html>" + parts[1] + "<br/>" + parts[2] + "<br/>" + parts[3] + "<br/>" + parts[4] + "<br/>Rating: " + parts[5] + "</html>");
                button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
                button.setAlignmentX(Component.LEFT_ALIGNMENT);
                if (parts.length > 6) { // Ensure there is a photo URL
                    Icon icon = FetchImageIcon.fetchImageIcon(parts[6]);
                    if (icon != null) {
                        button.setIcon(icon);
                        button.setHorizontalAlignment(SwingConstants.LEFT);
                        button.setHorizontalTextPosition(SwingConstants.RIGHT);
                        button.setVerticalTextPosition(SwingConstants.CENTER);
                    }
                }
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
