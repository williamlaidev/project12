package use_case;

public class SearchInputData {
    private final double latitude;
    private final double longitude;
    private final String distance;
    private final String dishType;

    public SearchInputData(double latitude, double longitude, String distance, String dishType) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.dishType = dishType;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getDistance() {
        return distance;
    }

    public String getDishType() {
        return dishType;
    }
}
