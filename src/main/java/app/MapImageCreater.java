package app;

import api.MapImageAPI;
import use_case.MapImageInteractor;

public class MapImageCreater {
    public static void main(String[] args) {
        MapImageAPI mapImageAPI = new MapImageAPI();
        MapImageInteractor mapImageInteractor = new MapImageInteractor(mapImageAPI);

        double latitude = 37.7749;
        double longitude = -122.4194;
        int zoom = 12;

        boolean success = mapImageInteractor.fetchAndSaveMapImage(latitude, longitude, zoom);
        if (success) {
            System.out.println("Map image saved successfully in the map_images package.");
        } else {
            System.out.println("Failed to fetch and save map image.");

        }
    }
}
