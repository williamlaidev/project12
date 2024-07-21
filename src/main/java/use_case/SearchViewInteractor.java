package use_case;

import java.awt.Point;

public class SearchViewInteractor implements SearchInputBoundary {
    @Override
    public void execute(SearchInputData searchInputData) {
        Point mapPosition = searchInputData.getMapPosition();
        String distance = searchInputData.getDistance();
        String dishType = searchInputData.getDishType();

        // Print the received information
        System.out.println("Map Position: " + mapPosition);
        System.out.println("Distance: " + distance);
        System.out.println("Dish Type: " + dishType);
    }
}
