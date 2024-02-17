package com.example.HSRwishsim;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SceneDisplay{

    @FXML
    Label Drops;
    @FXML
    MediaView mediaView;
    @FXML
    File file;
    Media media;
    MediaPlayer mediaPlayer;

    public SceneDisplay(){

    }

    public void initializeVideo(String fileName){
        file = new File(fileName);
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
    }

    public void displayScene(String fxmlFile, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        root = loader.load();

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToEventBanner(ActionEvent event) throws IOException{
        try {
            displayScene("eventBanner.fxml", event);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void switchToCurrentSummonedBanner(ActionEvent event) throws IOException {
        try {
            if (EventBanner.summonedOnEventBanner) {
                displayScene("eventBanner.fxml", event);
                EventBanner.summonedOnEventBanner = false;
            } else if (StandardBanner.summonedOnStandardBanner) {
                displayScene("standardBanner.fxml", event);
                StandardBanner.summonedOnStandardBanner = false;
            } else {
                displayScene("LightConeBanner.fxml", event);
                LightConeBanner.summonedOnLightConeBanner = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Stage stage;
    private Parent root;
    private Scene scene;
}
