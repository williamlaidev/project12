package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Provides utility methods for fetching and resizing images from URLs.
 */
public class FetchImageIcon {
    private static final Logger logger = LoggerFactory.getLogger(FetchImageIcon.class);

    /**
     * Fetches an image from a URL, resizes it to 100x100 pixels, and returns it as an Icon.
     * Returns an empty icon if the image cannot be fetched or processed.
     *
     * @param photoUrl the URL of the image
     * @return an {@link Icon} with the resized image, or an empty icon if an error occurs
     */
    public static Icon fetchImageIcon(String photoUrl) {
        try {
            URL url = new URL(photoUrl);
            BufferedImage img = ImageIO.read(url);
            Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImg);
        } catch (IOException e) {
            logger.error("Error fetching image from URL {}: {}", photoUrl, e.getMessage());
            return new ImageIcon();
        }
    }
}
