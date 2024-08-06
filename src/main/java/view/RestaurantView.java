package view;

import interface_adapter.view.RestaurantViewModel;
import use_case.search.GetReviewsForRestaurant;
import entity.Review;
import utils.FetchImageIcon;
import view.ReviewView;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class RestaurantView extends JPanel implements PropertyChangeListener {
    private RestaurantViewModel viewModel;
    private JPanel buttonsPanel;
    private GetReviewsForRestaurant getReviewsForRestaurant;  // Use case for fetching reviews

    public RestaurantView(RestaurantViewModel viewModel, GetReviewsForRestaurant getReviewsForRestaurant) {
        this.viewModel = viewModel;
        this.getReviewsForRestaurant = getReviewsForRestaurant;
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
                String restaurantId = parts[0];
                String restaurantName = parts[1];
                JButton button = new JButton("<html>" + parts[1] + "<br/>Address: " + parts[2] + "<br/>Dish Type: " + parts[4] + "<br/>Rating: " + parts[5] + "</html>");
                button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
                button.setAlignmentX(Component.LEFT_ALIGNMENT);
                button.addActionListener(e -> displayReviews(restaurantId, restaurantName));
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

    private void displayReviews(String restaurantId, String restaurantName) {
        try {
            List<Review> reviews = getReviewsForRestaurant.execute(restaurantId);
            ReviewView reviewView = new ReviewView(reviews);
            JFrame frame = new JFrame("Reviews for " + restaurantName);
            frame.setContentPane(reviewView);
            frame.setSize(300, 650);

            // Get the location of RestaurantView on the screen
            Point location = this.getLocationOnScreen();

            // Calculate the new x position as the x position of RestaurantView plus its width
            int newX = location.x + this.getWidth();

            // Set the location of the new frame to be immediately to the right of RestaurantView
            frame.setLocation(newX, location.y);

            frame.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to load reviews: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            updateRestaurantButtons();
        }
    }
}

