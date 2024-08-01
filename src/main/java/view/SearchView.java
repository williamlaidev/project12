package view;

import interface_adapter.view.SearchController;
import interface_adapter.view.SearchViewModel;
import interface_adapter.view.SearchViewState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

// TODO: Fix the IntelliJ warnings in this class and reduce their number as much as possible.

/**
 * SearchView class is responsible for presenting the components of the search interface.
 * It includes input fields, buttons, and a map area where users can click to set a location.
 */
public class SearchView extends JPanel implements ActionListener, PropertyChangeListener, MouseListener {
    public final String viewName = "search";

    private final SearchViewModel searchViewModel;
    private final JTextField distanceInputField = new JTextField(15);
    private Point mousePosition;

    private final SearchController searchController;

    private JButton searchButton = new JButton("Search");

    private final JComboBox<String> dishTypeComboBox;


    private Image mapImage;

    /**
     * Constructs a SearchView with the specified controller and view model.
     *
     * @param controller      The controller to handle actions.
     * @param searchViewModel The view model to manage data and state.
     * @param dishTypeList    The list of dish types.
     * @param mapImage        The map image to display.
     */
    public SearchView(SearchController controller, SearchViewModel searchViewModel, String[] dishTypeList, Image mapImage) {
        this.searchController = controller;
        this.searchViewModel = searchViewModel;
        this.mapImage = mapImage;
        searchViewModel.addPropertyChangeListener(this);

        setLayout(null); // Disable layout manager for absolute positioning

        JLabel title = new JLabel(searchViewModel.TITLE_LABEL);
        title.setBounds(SearchViewComponentsPosition.TITLE_X, SearchViewComponentsPosition.TITLE_Y,
                SearchViewComponentsPosition.TITLE_WIDTH, SearchViewComponentsPosition.TITLE_HEIGHT);
        add(title);

        JLabel distanceLabel = new JLabel(searchViewModel.DISTANCE_LABEL);
        distanceLabel.setBounds(SearchViewComponentsPosition.DISTANCE_LABEL_X, SearchViewComponentsPosition.DISTANCE_LABEL_Y,
                SearchViewComponentsPosition.DISTANCE_LABEL_WIDTH, SearchViewComponentsPosition.DISTANCE_LABEL_HEIGHT);
        add(distanceLabel);

        distanceInputField.setBounds(SearchViewComponentsPosition.DISTANCE_INPUT_X, SearchViewComponentsPosition.DISTANCE_INPUT_Y,
                SearchViewComponentsPosition.DISTANCE_INPUT_WIDTH, SearchViewComponentsPosition.DISTANCE_INPUT_HEIGHT);
        add(distanceInputField);

        JLabel dishTypeLabel = new JLabel("Choose Dish Type:");
        dishTypeLabel.setBounds(SearchViewComponentsPosition.DISH_TYPE_LABEL_X, SearchViewComponentsPosition.DISH_TYPE_LABEL_Y,
                SearchViewComponentsPosition.DISH_TYPE_LABEL_WIDTH, SearchViewComponentsPosition.DISH_TYPE_LABEL_HEIGHT);
        add(dishTypeLabel);

        // Add "ALL" option to the dish type list
        String[] extendedDishTypeList = new String[dishTypeList.length + 1];
        extendedDishTypeList[0] = "ALL";
        System.arraycopy(dishTypeList, 0, extendedDishTypeList, 1, dishTypeList.length);

        dishTypeComboBox = new JComboBox<>(extendedDishTypeList);
        dishTypeComboBox.setSelectedIndex(0); // Set default selection to "ALL"
        dishTypeComboBox.setBounds(SearchViewComponentsPosition.DISH_TYPE_COMBO_BOX_X, SearchViewComponentsPosition.DISH_TYPE_COMBO_BOX_Y,
                SearchViewComponentsPosition.DISH_TYPE_COMBO_BOX_WIDTH, SearchViewComponentsPosition.DISH_TYPE_COMBO_BOX_HEIGHT);
        add(dishTypeComboBox);

        searchButton = new JButton(searchViewModel.SEARCH_BUTTON_LABEL);
        searchButton.setBounds(SearchViewComponentsPosition.SEARCH_BUTTON_X, SearchViewComponentsPosition.SEARCH_BUTTON_Y,
                SearchViewComponentsPosition.SEARCH_BUTTON_WIDTH, SearchViewComponentsPosition.SEARCH_BUTTON_HEIGHT);
        searchButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        if (event.getSource().equals(searchButton)) {
                            SearchViewState searchViewState = new SearchViewState();
                            searchViewState.setMousePosition(mousePosition);
                            searchViewState.setDistance(distanceInputField.getText());
                            searchViewState.setSelectedDishType((String) dishTypeComboBox.getSelectedItem());
                            searchController.execute(searchViewState);
                        }
                    }
                }
        );
        add(searchButton);

        addMouseListener(this);
    }

    /**
     * Handles mouse click events to set the mouse position if within the map area.
     *
     * @param mouseEvent The mouse event.
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int x = mouseEvent.getX();
        int y = mouseEvent.getY();
        if (x >= SearchViewComponentsPosition.MAP_AREA_X && x <= SearchViewComponentsPosition.MAP_AREA_X + SearchViewComponentsPosition.MAP_AREA_WIDTH
                && y >= SearchViewComponentsPosition.MAP_AREA_Y && y <= SearchViewComponentsPosition.MAP_AREA_Y + SearchViewComponentsPosition.MAP_AREA_HEIGHT) {
            this.mousePosition = mouseEvent.getPoint();
        }
        repaint();
    }

    /**
     * Placeholder for mouse pressed event.
     *
     * @param mouseEvent The mouse event.
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }

    /**
     * Placeholder for mouse released event.
     *
     * @param mouseEvent The mouse event.
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }

    /**
     * Placeholder for mouse entered event.
     *
     * @param mouseEvent The mouse event.
     */
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    /**
     * Placeholder for mouse exited event.
     *
     * @param mouseEvent The mouse event.
     */
    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }

    /**
     * Placeholder for action performed event.
     *
     * @param actionEvent The action event.
     */
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Not implemented yet.");
    }

    /**
     * Placeholder for property change event.
     *
     * @param propertyChangeEvent The property change event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
    }

    /**
     * Paints the component, including the map area and the click position if available.
     *
     * @param graphics The graphics context to use for painting.
     */
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        // Draw the map image if available
        if (mapImage != null) {
            graphics.drawImage(mapImage, SearchViewComponentsPosition.MAP_AREA_X, SearchViewComponentsPosition.MAP_AREA_Y,
                    SearchViewComponentsPosition.MAP_AREA_WIDTH, SearchViewComponentsPosition.MAP_AREA_HEIGHT, this);
        } else {
            // Draw the map area if image is not available
            graphics.setColor(Color.LIGHT_GRAY);
            graphics.fillRect(SearchViewComponentsPosition.MAP_AREA_X, SearchViewComponentsPosition.MAP_AREA_Y,
                    SearchViewComponentsPosition.MAP_AREA_WIDTH, SearchViewComponentsPosition.MAP_AREA_HEIGHT);
            graphics.setColor(Color.BLACK);
            graphics.drawRect(SearchViewComponentsPosition.MAP_AREA_X, SearchViewComponentsPosition.MAP_AREA_Y,
                    SearchViewComponentsPosition.MAP_AREA_WIDTH, SearchViewComponentsPosition.MAP_AREA_HEIGHT);
            graphics.drawString("Map Area", SearchViewComponentsPosition.MAP_AREA_X + 10, SearchViewComponentsPosition.MAP_AREA_Y + 20);
        }

        // Draw the map area border
        graphics.setColor(Color.BLACK);
        graphics.drawRect(SearchViewComponentsPosition.MAP_AREA_X - 1, SearchViewComponentsPosition.MAP_AREA_Y - 1,
                SearchViewComponentsPosition.MAP_AREA_WIDTH + 1, SearchViewComponentsPosition.MAP_AREA_HEIGHT + 1);

        // Draw the mouse position if available
        if (mousePosition != null) {
            graphics.setColor(Color.RED);
            graphics.drawOval(mousePosition.x - 5, mousePosition.y - 5, 10, 10);
            graphics.drawString("Click Position", mousePosition.x + 10, mousePosition.y);
        }
    }
}
