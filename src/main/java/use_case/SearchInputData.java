package use_case;

import java.awt.Point;

public class SearchInputData {
    private final Point mapPosition;
    private final String distance;
    private final String dishType;

    public SearchInputData(Point mapPosition, String distance, String dishType) {
        this.mapPosition = mapPosition;
        this.distance = distance;
        this.dishType = dishType;
    }

    public Point getMapPosition() {
        return mapPosition;
    }

    public String getDistance() {
        return distance;
    }

    public String getDishType() {
        return dishType;
    }
}
