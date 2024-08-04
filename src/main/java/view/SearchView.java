package view;

import interface_adapter.view.SearchController;
import interface_adapter.view.SearchViewModel;
import interface_adapter.view.SearchViewState;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

/**
 * Provides a graphical user interface for the search feature, allowing user interactions for searching and map manipulation.
 */
public class SearchView extends JPanel implements ActionListener, PropertyChangeListener, MouseListener {
    private final SearchViewModel searchViewModel;
    private final JTextField distanceInputField = new JTextField(15);
    private Point mousePosition;

    private final SearchController searchController;
    private JButton searchButton;
    private JComboBox<String> dishTypeComboBox;
    private Image mapImage;

    private JLabel zoomLabel;
    private JButton zoomInButton, zoomOutButton;

    public SearchView(SearchController searchController, SearchViewModel searchViewModel, String[] dishTypeList) throws IOException {
        this.searchController = searchController;
        this.searchViewModel = searchViewModel;
        File mapImageFile = new File("src/main/resources/map_images/map.png");
        this.mapImage = ImageIO.read(mapImageFile);
        searchViewModel.addPropertyChangeListener(this);

        setLayout(null); // Use absolute positioning

        setupTitle();
        setupDistanceControls();
        setupDishTypeControls(dishTypeList);
        setupSearchButton();
        setupZoomControls();

        addMouseListener(this);
    }

    private void setupTitle() {
        JLabel title = new JLabel(searchViewModel.TITLE_LABEL);
        title.setBounds(SearchViewComponentsPosition.TITLE_X, SearchViewComponentsPosition.TITLE_Y,
                SearchViewComponentsPosition.TITLE_WIDTH, SearchViewComponentsPosition.TITLE_HEIGHT);
        add(title);
    }

    private void setupDistanceControls() {
        JLabel distanceLabel = new JLabel(searchViewModel.DISTANCE_LABEL);
        distanceLabel.setBounds(SearchViewComponentsPosition.DISTANCE_LABEL_X, SearchViewComponentsPosition.DISTANCE_LABEL_Y,
                SearchViewComponentsPosition.DISTANCE_LABEL_WIDTH, SearchViewComponentsPosition.DISTANCE_LABEL_HEIGHT);
        add(distanceLabel);

        distanceInputField.setBounds(SearchViewComponentsPosition.DISTANCE_INPUT_X, SearchViewComponentsPosition.DISTANCE_INPUT_Y,
                SearchViewComponentsPosition.DISTANCE_INPUT_WIDTH, SearchViewComponentsPosition.DISTANCE_INPUT_HEIGHT);
        add(distanceInputField);
    }

    private void setupDishTypeControls(String[] dishTypeList) {
        JLabel dishTypeLabel = new JLabel("Choose Dish Type:");
        dishTypeLabel.setBounds(SearchViewComponentsPosition.DISH_TYPE_LABEL_X, SearchViewComponentsPosition.DISH_TYPE_LABEL_Y,
                SearchViewComponentsPosition.DISH_TYPE_LABEL_WIDTH, SearchViewComponentsPosition.DISH_TYPE_LABEL_HEIGHT);
        add(dishTypeLabel);

        dishTypeComboBox = new JComboBox<>(dishTypeList);
        dishTypeComboBox.setBounds(SearchViewComponentsPosition.DISH_TYPE_COMBO_BOX_X, SearchViewComponentsPosition.DISH_TYPE_COMBO_BOX_Y,
                SearchViewComponentsPosition.DISH_TYPE_COMBO_BOX_WIDTH, SearchViewComponentsPosition.DISH_TYPE_COMBO_BOX_HEIGHT);
        add(dishTypeComboBox);
    }

    private void setupSearchButton() {
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

    private void setupZoomControls() {
        // Setting up the zoom level label with predefined positions
        zoomLabel = new JLabel("Zoom Level: " + searchViewModel.getZoomLevel());
        zoomLabel.setBounds(SearchViewComponentsPosition.ZOOM_LABEL_X, SearchViewComponentsPosition.ZOOM_LABEL_Y,
                SearchViewComponentsPosition.ZOOM_LABEL_WIDTH, SearchViewComponentsPosition.ZOOM_LABEL_HEIGHT);
        add(zoomLabel);

        // Setting up the zoom in button with predefined positions
        zoomInButton = new JButton("+");
        zoomInButton.setBounds(SearchViewComponentsPosition.ZOOM_IN_BUTTON_X, SearchViewComponentsPosition.ZOOM_IN_BUTTON_Y,
                SearchViewComponentsPosition.ZOOM_BUTTON_WIDTH, SearchViewComponentsPosition.ZOOM_BUTTON_HEIGHT);
        zoomInButton.addActionListener(e -> {
            SearchViewState searchViewState = new SearchViewState();
            Point centerPosition = new Point(SearchViewComponentsPosition.MAP_AREA_WIDTH / 2, SearchViewComponentsPosition.MAP_AREA_HEIGHT / 2);
            searchViewState.setMousePosition(centerPosition);
            searchViewState.setDistance(distanceInputField.getText());
            searchViewState.setSelectedDishType((String) dishTypeComboBox.getSelectedItem());
            searchController.changeZoomLevel(1, searchViewState);
            try {
                updateMapViewAfterZoom();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        add(zoomInButton);

        // Setting up the zoom out button with predefined positions
        zoomOutButton = new JButton("-");
        zoomOutButton.setBounds(SearchViewComponentsPosition.ZOOM_OUT_BUTTON_X, SearchViewComponentsPosition.ZOOM_OUT_BUTTON_Y,
                SearchViewComponentsPosition.ZOOM_BUTTON_WIDTH, SearchViewComponentsPosition.ZOOM_BUTTON_HEIGHT);
        zoomOutButton.addActionListener(e -> {
            SearchViewState searchViewState = new SearchViewState();
            Point centerPosition = new Point(SearchViewComponentsPosition.MAP_AREA_WIDTH / 2, SearchViewComponentsPosition.MAP_AREA_HEIGHT / 2);
            searchViewState.setMousePosition(centerPosition);
            searchViewState.setDistance(distanceInputField.getText());
            searchViewState.setSelectedDishType((String) dishTypeComboBox.getSelectedItem());
            searchController.changeZoomLevel(-1, searchViewState);
            try {
                updateMapViewAfterZoom();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        add(zoomOutButton);
    }

    // This method is used to update the zoom level label and refresh the view after a zoom change
    private void updateMapViewAfterZoom() throws IOException {
        zoomLabel.setText("Zoom Level: " + searchViewModel.getZoomLevel());
        File mapImageFile = new File("src/main/resources/map_images/map.png");
        Image newmapImage = ImageIO.read(mapImageFile);
        this.mapImage = newmapImage;
        repaint(); // Optionally, if needed to redraw or refresh other components
    }

    private void updateMapViewAfterRightClick() throws IOException {
        File mapImageFile = new File("src/main/resources/map_images/map.png");
        Image newmapImage = ImageIO.read(mapImageFile);
        this.mapImage = newmapImage;
        repaint(); // Optionally, if needed to redraw or refresh other components
    }


    /**
     * Handles mouse click events to set the mouse position if within the map area.
     *
     * @param mouseEvent The mouse event.
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            int x = mouseEvent.getX();
            int y = mouseEvent.getY();
            if (x >= SearchViewComponentsPosition.MAP_AREA_X && x <= SearchViewComponentsPosition.MAP_AREA_X + SearchViewComponentsPosition.MAP_AREA_WIDTH
                    && y >= SearchViewComponentsPosition.MAP_AREA_Y && y <= SearchViewComponentsPosition.MAP_AREA_Y + SearchViewComponentsPosition.MAP_AREA_HEIGHT) {
                this.mousePosition = mouseEvent.getPoint();
            }
            repaint();
        }
        else if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
            int x = mouseEvent.getX();
            int y = mouseEvent.getY();
            if (x >= SearchViewComponentsPosition.MAP_AREA_X && x <= SearchViewComponentsPosition.MAP_AREA_X + SearchViewComponentsPosition.MAP_AREA_WIDTH
                    && y >= SearchViewComponentsPosition.MAP_AREA_Y && y <= SearchViewComponentsPosition.MAP_AREA_Y + SearchViewComponentsPosition.MAP_AREA_HEIGHT) {
                SearchViewState searchViewState = new SearchViewState();
                searchViewState.setMousePosition(new Point(x, y));
                searchViewState.setDistance(distanceInputField.getText());
                searchViewState.setSelectedDishType((String) dishTypeComboBox.getSelectedItem());
                searchController.changeCenter(new Point(x, y), searchViewState);
                try {
                    updateMapViewAfterZoom();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
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
        System.out.println("Your actionPerformed method should be specified within key setting.");
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
            graphics.drawString("Search Center", mousePosition.x + 10, mousePosition.y);
        }
    }
}
