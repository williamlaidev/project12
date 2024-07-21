package use_case;

public class SearchViewInteractor implements SearchInputBoundary {
    @Override
    public void execute(SearchInputData searchInputData) {
        double latitude = searchInputData.getLatitude();
        double longitude = searchInputData.getLongitude();
        String distance = searchInputData.getDistance();
        String dishType = searchInputData.getDishType();

        // Print the received information
        System.out.println("Latitude: " + latitude);
        System.out.println("Longitude: " + longitude);
        System.out.println("Distance: " + distance);
        System.out.println("Dish Type: " + dishType);
    }
}
