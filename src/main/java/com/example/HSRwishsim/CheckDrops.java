package com.example.HSRwishsim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;



public class CheckDrops {

    @FXML
    Label displayLightCones;
    @FXML
    Label displayCharacters;
    @FXML
    Label CharacterBoxLabel;
    @FXML
    Label LightConeBoxLabel;
    @FXML
    MediaPlayer mediaPlayer;

    private final SceneDisplay sceneDisplay = new SceneDisplay();
    final private MediaController mediaController = MediaController.getInstance();

    public void initialize(){
        displayLightCones.setText(AppInfo.getInstance().displayLightConeInventory());
        displayCharacters.setText(AppInfo.getInstance().displayCharacterInventory());
            String audioFilePath = "src/main/resources/com/example/HSRwishsim/media/SpaceWalk.mp3";

            try {
                mediaController.setSoundPlayer(new File(audioFilePath).toURI().toURL().toString());
                mediaController.getMediaPlayer().play();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
    }

    public void switchToCurrentBanner(ActionEvent event) {
        mediaController.getMediaPlayer().dispose();
        try {
            if (EventBanner.checkingDrops) {
                sceneDisplay.displayScene("eventBanner.fxml", event);
                EventBanner.checkingDrops = false;
            } else if (LightConeBanner.checkingDrops) {
                sceneDisplay.displayScene("lightConeBanner.fxml", event);
                LightConeBanner.checkingDrops = false;
            } else if (StandardBanner.checkingDrops) {
                sceneDisplay.displayScene("standardBanner.fxml", event);
                StandardBanner.checkingDrops = false;
            }
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
