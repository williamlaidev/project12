package view;

import entity.Review;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * A view component for displaying reviews of a restaurant.
 */
public class ReviewView extends JPanel {
    private List<Review> reviews;

    public ReviewView(List<Review> reviews) {
        this.reviews = reviews;
        initializeUI();
    }

    private void initializeUI() {
        // Using a temporary panel to hold review panels
        JPanel reviewsPanel = new JPanel();
        reviewsPanel.setLayout(new BoxLayout(reviewsPanel, BoxLayout.Y_AXIS));

        if (reviews == null || reviews.isEmpty()) {
            reviewsPanel.add(new JLabel("No reviews available."));
        } else {
            for (Review review : reviews) {
                reviewsPanel.add(createReviewPanel(review));
            }
        }

        // Adding the reviews panel to a JScrollPane for scrollability
        JScrollPane scrollPane = new JScrollPane(reviewsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(300, 650)); // Set preferred size as needed

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Creates a JPanel for a single review.
     *
     * @param review The review to display.
     * @return A JPanel representing the review.
     */
    private JPanel createReviewPanel(Review review) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel authorLabel = new JLabel("Author: " + review.getAuthor());
        authorLabel.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(authorLabel, BorderLayout.NORTH);

        JTextArea contentArea = new JTextArea(review.getContent());
        contentArea.setWrapStyleWord(true);
        contentArea.setLineWrap(true);
        contentArea.setEditable(false);
        JScrollPane contentScrollPane = new JScrollPane(contentArea);
        panel.add(contentScrollPane, BorderLayout.CENTER);

        JLabel summaryLabel = new JLabel("Summarized: " + (review.isSummarized() ? "Yes" : "No"));
        summaryLabel.setFont(new Font("Arial", Font.ITALIC, 10));
        panel.add(summaryLabel, BorderLayout.SOUTH);

        return panel;
    }
}

