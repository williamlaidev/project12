package utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * This class is responsible for fetching and resizing images from a URL.
 * It provides a method to fetch an image and return it as an {@link Icon},
 * which can be used in a Swing interface.
 */
public class FetchImageIcon {

    /**
     * Fetches an image from a specified URL, resizes it, and returns it as an Icon.
     * If the image cannot be fetched or processed, an empty icon is returned.
     *
     * @param photoUrl the URL of the photo to fetch
     * @return an {@link Icon} containing the fetched and resized image
     */
    public static Icon fetchImageIcon(String photoUrl) {
        try {
            URL url = new URL(photoUrl);
            BufferedImage img = ImageIO.read(url);
            Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Resize image to 100x100 pixels
            return new ImageIcon(scaledImg);
        } catch (IOException e) {
            System.err.println("Error fetching image: " + e.getMessage());
            return new ImageIcon(); // Return an empty icon in case of failure
        }
    }
}
