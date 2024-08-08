package use_case.view;

/**
 * Interface for handling search operation results.
 */
public interface SearchOutputBoundary {

    void setZoomLevel(int zoomLevel);

    /**
     * Prepares the success view with search results.
     *
     * @param searchOutputData data about the search results
     * @param centerLat        latitude of the map center
     * @param centerLon        longitude of the map center
     * @param mapHeight        height of the map
     * @param mapWidth         width of the map
     */
    void prepareSuccessView(SearchOutputData searchOutputData, double centerLat, double centerLon, int mapHeight, int mapWidth);
}
