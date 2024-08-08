package view;

import interface_adapter.view.SearchController;
import interface_adapter.view.SearchState;
import interface_adapter.view.SearchViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

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

    /**
     * Constructs a SearchView with the specified search controller, search view model, and list of dish types.
     *
     * @param searchController the controller to handle search logic
     * @param searchViewModel the view model to represent search state
     * @param dishTypeList the list of dish types for the combo box
     * @throws IOException if there is an error loading the map image
     */
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
        searchButton.addActionListener(event -> {
            if (event.getSource().equals(searchButton)) {
                SearchState searchState = new SearchState();
                searchState.setMouseLeftClickPosition(Objects.requireNonNullElseGet(mousePosition, () -> new Point(200, 200)));
                searchState.setDistance(distanceInputField.getText());
                searchState.setSelectedDishType((String) dishTypeComboBox.getSelectedItem());
                searchController.execute(searchState);
            }
        });
        add(searchButton);
    }

    private void setupZoomControls() {
        zoomLabel = new JLabel("Zoom Level: " + searchViewModel.getZoomLevel());
        zoomLabel.setBounds(SearchViewComponentsPosition.ZOOM_LABEL_X, SearchViewComponentsPosition.ZOOM_LABEL_Y,
                SearchViewComponentsPosition.ZOOM_LABEL_WIDTH, SearchViewComponentsPosition.ZOOM_LABEL_HEIGHT);
        add(zoomLabel);

        JButton zoomInButton = new JButton("+");
        zoomInButton.setBounds(SearchViewComponentsPosition.ZOOM_IN_BUTTON_X, SearchViewComponentsPosition.ZOOM_IN_BUTTON_Y,
                SearchViewComponentsPosition.ZOOM_BUTTON_WIDTH, SearchViewComponentsPosition.ZOOM_BUTTON_HEIGHT);
        zoomInButton.addActionListener(e -> handleZoom(1));
        add(zoomInButton);

        JButton zoomOutButton = new JButton("-");
        zoomOutButton.setBounds(SearchViewComponentsPosition.ZOOM_OUT_BUTTON_X, SearchViewComponentsPosition.ZOOM_OUT_BUTTON_Y,
                SearchViewComponentsPosition.ZOOM_BUTTON_WIDTH, SearchViewComponentsPosition.ZOOM_BUTTON_HEIGHT);
        zoomOutButton.addActionListener(e -> handleZoom(-1));
        add(zoomOutButton);
    }

    private void handleZoom(int zoomChange) {
        searchViewModel.clearMapMarkers();
        SearchState searchState = new SearchState();
        Point centerPosition = new Point(SearchViewComponentsPosition.MAP_AREA_WIDTH / 2, SearchViewComponentsPosition.MAP_AREA_HEIGHT / 2);
        searchState.setMouseRightClickPosition(centerPosition);
        searchState.setDistance(distanceInputField.getText());
        searchState.setSelectedDishType((String) dishTypeComboBox.getSelectedItem());
        searchController.changeZoomLevel(zoomChange, searchState);
        try {
            updateMapView();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void updateMapView() throws IOException {
        zoomLabel.setText("Zoom Level: " + searchViewModel.getZoomLevel());
        File mapImageFile = new File("src/main/resources/map_images/map.png");
        this.mapImage = ImageIO.read(mapImageFile);
        repaint(); // Optionally, if needed to redraw or refresh other components
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
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            if (isWithinMapArea(x, y)) {
                this.mousePosition = mouseEvent.getPoint();
            }
        } else if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
            if (isWithinMapArea(x, y)) {
                searchViewModel.clearMapMarkers();
                SearchState searchState = new SearchState();
                searchState.setMouseRightClickPosition(new Point(x, y));
                searchState.setDistance(distanceInputField.getText());
                searchState.setSelectedDishType((String) dishTypeComboBox.getSelectedItem());
                searchController.changeCenter(searchState);
                try {
                    updateMapView();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        repaint();
    }

    private boolean isWithinMapArea(int x, int y) {
        return x >= SearchViewComponentsPosition.MAP_AREA_X && x <= SearchViewComponentsPosition.MAP_AREA_X + SearchViewComponentsPosition.MAP_AREA_WIDTH
                && y >= SearchViewComponentsPosition.MAP_AREA_Y && y <= SearchViewComponentsPosition.MAP_AREA_Y + SearchViewComponentsPosition.MAP_AREA_HEIGHT;
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
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Error: this action was not declared. Your actionPerformed method should be specified within key setting.");
    }

    /**
     * Handles property change events to repaint the component when the state or map markers change.
     *
     * @param propertyChangeEvent The property change event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if ("state".equals(propertyChangeEvent.getPropertyName()) || "mapMarkers".equals(propertyChangeEvent.getPropertyName())) {
            repaint();
        }
    }

    /**
     * Paints the component, including the map area and the click position if available.
     *
     * @param graphics The graphics context to use for painting.
     */
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        drawMapImage(graphics);
        drawMousePosition(graphics);
        drawMapMarkers(graphics);
    }

    private void drawMapImage(Graphics graphics) {
        if (mapImage != null) {
            graphics.drawImage(mapImage, SearchViewComponentsPosition.MAP_AREA_X, SearchViewComponentsPosition.MAP_AREA_Y,
                    SearchViewComponentsPosition.MAP_AREA_WIDTH, SearchViewComponentsPosition.MAP_AREA_HEIGHT, this);
        } else {
            graphics.setColor(Color.LIGHT_GRAY);
            graphics.fillRect(SearchViewComponentsPosition.MAP_AREA_X, SearchViewComponentsPosition.MAP_AREA_Y,
                    SearchViewComponentsPosition.MAP_AREA_WIDTH, SearchViewComponentsPosition.MAP_AREA_HEIGHT);
            graphics.setColor(Color.BLACK);
            graphics.drawRect(SearchViewComponentsPosition.MAP_AREA_X, SearchViewComponentsPosition.MAP_AREA_Y,
                    SearchViewComponentsPosition.MAP_AREA_WIDTH, SearchViewComponentsPosition.MAP_AREA_HEIGHT);
            graphics.drawString("Error During Map Loading", SearchViewComponentsPosition.MAP_AREA_X + 10, SearchViewComponentsPosition.MAP_AREA_Y + 20);
        }
        graphics.setColor(Color.BLACK);
        graphics.drawRect(SearchViewComponentsPosition.MAP_AREA_X - 1, SearchViewComponentsPosition.MAP_AREA_Y - 1,
                SearchViewComponentsPosition.MAP_AREA_WIDTH + 1, SearchViewComponentsPosition.MAP_AREA_HEIGHT + 1);
    }

    private void drawMousePosition(Graphics graphics) {
        if (mousePosition != null) {
            graphics.setColor(Color.RED);
            graphics.drawOval(mousePosition.x - 5, mousePosition.y - 5, 10, 10);
            graphics.drawString("Search Center", mousePosition.x + 10, mousePosition.y);
        }
    }

    private void drawMapMarkers(Graphics graphics) {
        if (!searchViewModel.getMapMarkers().isEmpty()) {
            int mapX = SearchViewComponentsPosition.MAP_AREA_X;
            int mapY = SearchViewComponentsPosition.MAP_AREA_Y;
            int mapWidth = SearchViewComponentsPosition.MAP_AREA_WIDTH;
            int mapHeight = SearchViewComponentsPosition.MAP_AREA_HEIGHT;

            for (Map.Entry<Point, String> entry : searchViewModel.getMapMarkers().entrySet()) {
                Point p = entry.getKey();
                if (p.x >= mapX && p.x <= mapX + mapWidth && p.y >= mapY && p.y <= mapY + mapHeight) {
                    graphics.setColor(Color.RED);
                    graphics.fillOval(p.x - 5, p.y - 5, 10, 10);
                    graphics.drawString(entry.getValue(), p.x + 5, p.y - 5);
                }
            }
        }
    }
}
