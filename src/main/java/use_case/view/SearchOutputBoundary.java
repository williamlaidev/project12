package use_case.view;

/**
 * Interface that defines what to do when a search operation fails.
 * This interface is used to show error messages when a search doesn't work.
 */
public interface SearchOutputBoundary {

    /**
     * Prepares the failure view with the given error message.
     *
     * @param error the error message to be presented
     */
    void prepareFailView(String error);


    void updateZoomLevel(int zoomLevel);
}
