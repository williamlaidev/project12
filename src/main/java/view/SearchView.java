package view;

import interface_adapter.SearchController;
import interface_adapter.SearchViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SearchView extends JPanel implements ActionListener, PropertyChangeListener, MouseListener {
    public final String viewName = "search";

    private final SearchViewModel searchViewModel;
    private final JTextField distanceInputField = new JTextField(15);
    private Point mousePosition;

    private final SearchController searchController;

    private JButton searchButton = new JButton("Search");

    private static final int MAP_AREA_X = 10;
    private static final int MAP_AREA_Y = 30;
    private static final int MAP_AREA_WIDTH = 200;
    private static final int MAP_AREA_HEIGHT = 200;

    private JComboBox<String> dishTypeComboBox;
    private static final String[] dishTypeList = {"dish1", "dish2", "dish3", "dish4", "dish5"};

    public SearchView(SearchController controller, SearchViewModel searchViewModel) {
        this.searchController = controller;
        this.searchViewModel = searchViewModel;
        searchViewModel.addPropertyChangeListener(this);

        setLayout(null); // Disable layout manager for absolute positioning

        JLabel title = new JLabel(searchViewModel.TITLE_LABEL);
        title.setBounds(10, 0, 200, 20); // Manually set position and size
        add(title);

        JLabel distanceLabel = new JLabel(searchViewModel.DISTANCE_LABEL);
        distanceLabel.setBounds(10, 250, 100, 20); // Manually set position and size
        add(distanceLabel);

        distanceInputField.setBounds(120, 250, 150, 20); // Manually set position and size
        add(distanceInputField);

        JLabel dishTypeLabel = new JLabel("Choose Dish Type:");
        dishTypeLabel.setBounds(10, 280, 200, 20); // Manually set position and size
        add(dishTypeLabel);

        dishTypeComboBox = new JComboBox<>(dishTypeList);
        dishTypeComboBox.setBounds(120, 280, 150, 20); // Manually set position and size
        add(dishTypeComboBox);

        searchButton = new JButton(searchViewModel.SEARCH_BUTTON_LABEL);
        searchButton.setBounds(10, 310, 100, 20); // Manually set position and size
        searchButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(searchButton)) {
                            searchController.execute(mousePosition,
                                    distanceInputField.getText(),
                                    (String) dishTypeComboBox.getSelectedItem()
                            );
                        }
                    }
                }
        );
        add(searchButton);

        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int x = mouseEvent.getX();
        int y = mouseEvent.getY();
        if (x >= MAP_AREA_X && x <= MAP_AREA_X + MAP_AREA_WIDTH && y >= MAP_AREA_Y && y <= MAP_AREA_Y + MAP_AREA_HEIGHT) {
            this.mousePosition = mouseEvent.getPoint();
            System.out.println("Mouse clicked at: " + mousePosition);
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the map area
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(MAP_AREA_X, MAP_AREA_Y, MAP_AREA_WIDTH, MAP_AREA_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect(MAP_AREA_X, MAP_AREA_Y, MAP_AREA_WIDTH, MAP_AREA_HEIGHT);
        g.drawString("Map Area", MAP_AREA_X + 10, MAP_AREA_Y + 20);

        // Draw the mouse position if available
        if (mousePosition != null) {
            g.setColor(Color.RED);
            g.drawOval(mousePosition.x - 5, mousePosition.y - 5, 10, 10);
            g.drawString("Click Position", mousePosition.x + 10, mousePosition.y);
        }
    }
}
