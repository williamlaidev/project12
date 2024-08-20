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
     */
    void prepareSuccessView(SearchOutputData searchOutputData);
}
