package com.example.HSRwishsim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
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

    public void initialize(){
        displayLightCones.setText(AppInfo.getInstance().displayLightConeInventory());
        displayCharacters.setText(AppInfo.getInstance().displayCharacterInventory());
    }

    public void switchToCurrentBanner(ActionEvent event) {
        mediaPlayer.dispose();
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
