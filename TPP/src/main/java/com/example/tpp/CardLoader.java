package com.example.tpp;
import javafx.scene.image.Image;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * CardLoader is a utility class that loads card images from specified directories.
 */
public class CardLoader {

    /**
     * Loads card images from the given list of folder paths.
     *
     * @param folderPaths List of folder paths to load images from.
     * @return List of Image objects representing the card images.
     */
    public List<Image> loadCardImages(List<String> folderPaths) {
        List<Image> cardImages = new ArrayList<>();

        for (String folderPath : folderPaths) {
            File folder = new File(folderPath);
            File[] listOfFiles = folder.listFiles();

            if (listOfFiles != null) {
                for (File file : listOfFiles) {
                    if (file.isFile() && file.getName().endsWith(".jpg")) {
                        try {
                            cardImages.add(new Image(file.toURI().toURL().toString()));
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        return cardImages;
    }
}
