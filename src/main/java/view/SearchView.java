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
 * GUI for the search feature, including controls for searching and map interaction.
 */
public class SearchView extends JPanel implements ActionListener, PropertyChangeListener, MouseListener {

    private final SearchViewModel searchViewModel;
    private final JTextField distanceInputField = new JTextField(15);
    private final SearchController searchController;
    private JComboBox<String> dishTypeComboBox;
    private Image mapImage;
    private JLabel zoomLabel;
    private Point mousePosition;

    /**
     * Creates a SearchView with the given controller, view model, and dish types.
     *
     * @param searchController the controller for search actions
     * @param searchViewModel the view model for search state
     * @param dishTypeList list of dish types for the combo box
     * @throws IOException if loading the map image fails
     */
    public SearchView(SearchController searchController, SearchViewModel searchViewModel, String[] dishTypeList) throws IOException {
        this.searchController = searchController;
        this.searchViewModel = searchViewModel;
        this.mapImage = ImageIO.read(new File("src/main/resources/map_images/map.png"));
        searchViewModel.addPropertyChangeListener(this);

        setLayout(null); // Absolute positioning

        initializeComponents(dishTypeList);
        addMouseListener(this);
    }

    private void initializeComponents(String[] dishTypeList) {
        setupTitle();
        setupDistanceControls();
        setupDishTypeControls(dishTypeList);
        setupSearchButton();
        setupZoomControls();
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
        JButton searchButton = new JButton(searchViewModel.SEARCH_BUTTON_LABEL);
        searchButton.setBounds(SearchViewComponentsPosition.SEARCH_BUTTON_X, SearchViewComponentsPosition.SEARCH_BUTTON_Y,
                SearchViewComponentsPosition.SEARCH_BUTTON_WIDTH, SearchViewComponentsPosition.SEARCH_BUTTON_HEIGHT);
        searchButton.addActionListener(this::performSearch);
        add(searchButton);
    }

    private void performSearch(ActionEvent event) {
        SearchState searchState = new SearchState();
        searchState.setMouseLeftClickPosition(Objects.requireNonNullElseGet(mousePosition, () -> new Point(200, 200)));
        searchState.setDistance(distanceInputField.getText());
        searchState.setSelectedDishType((String) dishTypeComboBox.getSelectedItem());
        searchController.execute(searchState);
    }

    private void setupZoomControls() {
        zoomLabel = new JLabel("Zoom Level: " + searchViewModel.getZoomLevel());
        zoomLabel.setBounds(SearchViewComponentsPosition.ZOOM_LABEL_X, SearchViewComponentsPosition.ZOOM_LABEL_Y,
                SearchViewComponentsPosition.ZOOM_LABEL_WIDTH, SearchViewComponentsPosition.ZOOM_LABEL_HEIGHT);
        add(zoomLabel);

        JButton zoomInButton = createZoomButton("+", 1);
        JButton zoomOutButton = createZoomButton("-", -1);

        add(zoomInButton);
        add(zoomOutButton);
    }

    private JButton createZoomButton(String label, int zoomChange) {
        JButton button = new JButton(label);
        button.setBounds(zoomChange > 0 ? SearchViewComponentsPosition.ZOOM_IN_BUTTON_X : SearchViewComponentsPosition.ZOOM_OUT_BUTTON_X,
                zoomChange > 0 ? SearchViewComponentsPosition.ZOOM_IN_BUTTON_Y : SearchViewComponentsPosition.ZOOM_OUT_BUTTON_Y,
                SearchViewComponentsPosition.ZOOM_BUTTON_WIDTH, SearchViewComponentsPosition.ZOOM_BUTTON_HEIGHT);
        button.addActionListener(e -> handleZoom(zoomChange));
        return button;
    }

    private void handleZoom(int zoomChange) {
        searchViewModel.clearMapMarkers();
        searchController.changeZoomLevel(zoomChange);
        try {
            updateMapView();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void updateMapView() throws IOException {
        zoomLabel.setText("Zoom Level: " + searchViewModel.getZoomLevel());
        this.mapImage = ImageIO.read(new File("src/main/resources/map_images/map.png"));
        repaint(); // Refresh the component
    }

    /**
     * Updates mouse position on left click and handles map center change on right click.
     *
     * @param mouseEvent the mouse event
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int x = mouseEvent.getX();
        int y = mouseEvent.getY();
        if (mouseEvent.getButton() == MouseEvent.BUTTON1 && isWithinMapArea(x, y)) {
            this.mousePosition = mouseEvent.getPoint();
        } else if (mouseEvent.getButton() == MouseEvent.BUTTON3 && isWithinMapArea(x, y)) {
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
        repaint();
    }

    private boolean isWithinMapArea(int x, int y) {
        return x >= SearchViewComponentsPosition.MAP_AREA_X && x <= SearchViewComponentsPosition.MAP_AREA_X + SearchViewComponentsPosition.MAP_AREA_WIDTH
                && y >= SearchViewComponentsPosition.MAP_AREA_Y && y <= SearchViewComponentsPosition.MAP_AREA_Y + SearchViewComponentsPosition.MAP_AREA_HEIGHT;
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {}
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {}
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {}
    @Override
    public void mouseExited(MouseEvent mouseEvent) {}

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Error: Action not declared.");
    }

    /**
     * Repaints the component when the state or map markers change.
     *
     * @param evt the property change event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName()) || "mapMarkers".equals(evt.getPropertyName())) {
            repaint();
        }
    }

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
            graphics.drawString("Error Loading Map", SearchViewComponentsPosition.MAP_AREA_X + 10, SearchViewComponentsPosition.MAP_AREA_Y + 20);
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
