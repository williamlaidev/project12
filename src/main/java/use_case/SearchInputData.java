package main.java.use_case;

import java.awt.*;

public class SearchInputData {
    final private Point mapPosition;
    final private String distance;
    final private String dishType;

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
