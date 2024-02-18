package com.example.HSRwishsim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;


public class DisplayDrops {

@FXML
Button returnButton;
@FXML
Label drops;

    final private SceneDisplay sceneDisplay = new SceneDisplay();
    private final MediaController mediaController = MediaController.getInstance();


    public void initialize(){
        drops.setText(AppInfo.getInstance().getCurrentItemDrops());
        AppInfo.getInstance().setCurrentItemDrops("");
    }

    public void switchToCurrentBanner(ActionEvent event) {
        mediaController.getMediaPlayer().dispose();
        try {
            if (EventBanner.summonedOnEventBanner) {
                sceneDisplay.displayScene("eventBanner.fxml", event);
                EventBanner.summonedOnEventBanner = false;
            } else if (LightConeBanner.summonedOnLightConeBanner) {
                sceneDisplay.displayScene("lightConeBanner.fxml", event);
                LightConeBanner.summonedOnLightConeBanner = false;
            } else if (StandardBanner.summonedOnStandardBanner) {
                sceneDisplay.displayScene("standardBanner.fxml", event);
                StandardBanner.summonedOnStandardBanner = false;
            }
        }catch(IOException e){
            e.printStackTrace();
        }

    }

}

