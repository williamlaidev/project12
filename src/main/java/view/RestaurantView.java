package view;

import interface_adapter.view.RestaurantViewModel;
import use_case.search.FetchRestaurantReviews;
import entity.review.Review;
import use_case.summarize.SummarizeReviews;
import utils.FetchImageIcon;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * A view component for displaying a list of restaurants and their reviews.
 */
public class RestaurantView extends JPanel implements PropertyChangeListener {
    private final RestaurantViewModel viewModel;
    private final FetchRestaurantReviews fetchRestaurantReviews;
    private final SummarizeReviews summarizeReviews;
    private JPanel buttonsPanel;

    /**
     * Constructs a RestaurantView with the specified view model and fetch reviews use case.
     *
     * @param viewModel the view model containing restaurant data
     * @param fetchRestaurantReviews the use case to fetch restaurant reviews
     */
    public RestaurantView(RestaurantViewModel viewModel, FetchRestaurantReviews fetchRestaurantReviews, SummarizeReviews summarizeReviews) {
        this.viewModel = viewModel;
        this.fetchRestaurantReviews = fetchRestaurantReviews;
        this.summarizeReviews = summarizeReviews;
        this.viewModel.addPropertyChangeListener(this);

        initializeUI();
    }

    /**
     * Initializes the user interface components.
     */
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

    /**
     * Updates the buttons representing restaurants.
     */
    private void updateRestaurantButtons() {

        List<String> restaurantInfos = viewModel.getState().getRestaurants();

        // List<String> restaurantInfos = viewModel.getState().getRestaurantsInfo();
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
                    button.setIcon(icon);
                    button.setHorizontalAlignment(SwingConstants.LEFT);
                    button.setHorizontalTextPosition(SwingConstants.RIGHT);
                    button.setVerticalTextPosition(SwingConstants.CENTER);
                }
                buttonsPanel.add(button);
            }
        } else {
            buttonsPanel.add(new JLabel("No restaurants available."));
        }
        buttonsPanel.revalidate();
        buttonsPanel.repaint();
    }

    /**
     * Displays the reviews of the selected restaurant in a new window.
     *
     * @param restaurantId the ID of the selected restaurant
     * @param restaurantName the name of the selected restaurant
     */
    private void displayReviews(String restaurantId, String restaurantName) {
        try {
            List<Review> reviews = fetchRestaurantReviews.execute(restaurantId, 10);
            Review summarizedReview = summarizeReviews.execute(reviews);
            reviews.add(0, summarizedReview);

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

    /**
     * Handles property change events to update the restaurant buttons when the state changes.
     *
     * @param evt the property change event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            updateRestaurantButtons();
        }
    }
}
