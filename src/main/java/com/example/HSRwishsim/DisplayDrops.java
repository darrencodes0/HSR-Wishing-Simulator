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

    public void initialize(){
        drops.setText(AppInfo.getInstance().getCurrentItemDrops());
        AppInfo.getInstance().setCurrentItemDrops("");
        String audioFilePath = "src/main/resources/com/example/HSRwishsim/media/elevatormusic.mp3";

        try {
            Media media = new Media(new File(audioFilePath).toURI().toURL().toString());
            sceneDisplay.mediaPlayer = new MediaPlayer(media);
            sceneDisplay.mediaPlayer.play();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void switchToCurrentBanner(ActionEvent event) {
        sceneDisplay.mediaPlayer.dispose();
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

