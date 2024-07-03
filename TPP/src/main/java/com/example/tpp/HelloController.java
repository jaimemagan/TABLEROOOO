package com.example.tpp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * HelloController is the main controller for the JavaFX application.
 * It handles the loading and displaying of card images, character selection,
 * and game logic.
 */
public class HelloController {

    @FXML
    private HBox player1Hand, player2Hand, player3Hand, player4Hand;

    @FXML
    private VBox characterSelection;

    @FXML
    private Button drawCardButton, playCardButton;

    private CardLoader cardLoader;
    private String selectedCharacter;
    private List<String> cpuCharacters;

    /**
     * Instantiates a new Hello controller.
     */
    public HelloController() {
        cardLoader = new CardLoader();
        cpuCharacters = new ArrayList<>();
    }

    /**
     * Initializes the controller by setting up initial game state.
     */
    @FXML
    public void initialize() {
        setupCPUCharacters();
        characterSelection.setVisible(true);
    }

    /**
     * Handles character selection.
     *
     * @param event ActionEvent from the character selection button.
     */
    @FXML
    public void selectCharacter(javafx.event.ActionEvent event) {
        Button button = (Button) event.getSource();
        selectedCharacter = button.getText();
        characterSelection.setVisible(false);
        updateHand(player1Hand, selectedCharacter);
    }

    /**
     * Sets up CPU characters by randomly selecting characters for them.
     */
    private void setupCPUCharacters() {
        String[] characters = {"Azzan", "Blorp", "Delilah", "Lia", "Minsc and Boo", "Sutha"};
        Random rand = new Random();
        while (cpuCharacters.size() < 3) {
            String character = characters[rand.nextInt(characters.length)];
            if (!cpuCharacters.contains(character) && !character.equals(selectedCharacter)) {
                cpuCharacters.add(character);
            }
        }

        updateHand(player2Hand, cpuCharacters.get(0));
        updateHand(player3Hand, cpuCharacters.get(1));
        updateHand(player4Hand, cpuCharacters.get(2));
    }

    /**
     * Updates the hand container with the card images from the specified character.
     *
     * @param handContainer The HBox to update with card images.
     * @param character The character whose cards to load.
     */
    private void updateHand(HBox handContainer, String character) {
        List<String> folderPaths = new ArrayList<>();
        folderPaths.add("src/main/resources/ImagenesProyecto/" + character);
        List<Image> cardImages = cardLoader.loadCardImages(folderPaths);
        handContainer.getChildren().clear();

        for (int i = 0; i < 3 && i < cardImages.size(); i++) {
            ImageView imageView = new ImageView(cardImages.get(i));
            imageView.setFitHeight(150);
            imageView.setFitWidth(100);
            handContainer.getChildren().add(imageView);
        }
    }

    /**
     * Handles drawing a new card.
     */
    @FXML
    public void drawCard() {
        // Logic to draw a card and add it to the player's hand
        List<String> folderPaths = new ArrayList<>();
        folderPaths.add("src/main/resources/ImagenesProyecto/" + selectedCharacter);
        List<Image> cardImages = cardLoader.loadCardImages(folderPaths);

        if (player1Hand.getChildren().size() < 5 && cardImages.size() > player1Hand.getChildren().size()) {
            ImageView imageView = new ImageView(cardImages.get(player1Hand.getChildren().size()));
            imageView.setFitHeight(150);
            imageView.setFitWidth(100);
            player1Hand.getChildren().add(imageView);
        }
    }

    /**
     * Handles playing a card.
     */
    @FXML
    public void playCard() {
        // Logic to play a card from the player's hand
        if (!player1Hand.getChildren().isEmpty()) {
            player1Hand.getChildren().remove(0);
        }
    }
}
