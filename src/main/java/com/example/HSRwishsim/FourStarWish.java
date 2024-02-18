package com.example.HSRwishsim;

import com.example.HSRwishsim.SceneDisplay;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.Parent;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class FourStarWish implements Initializable {

    @FXML
    Label Drops;
    @FXML
    MediaView mediaView;
    @FXML
    final private SceneDisplay sceneDisplay = new SceneDisplay();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        AppInfo.getInstance().setVideoPlayer("src/main/resources/com/example/HSRwishsim/media/4STARSUMMON.mp4");
        mediaView.setMediaPlayer(AppInfo.getInstance().getMediaPlayer());
        AppInfo.getInstance().getMediaPlayer().play();
        AppInfo.getInstance().getMediaPlayer().setOnEndOfMedia(() -> {
            try {
                replaceMediaPlayer("displayDrops.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }



    public void seeDrops(ActionEvent event) throws IOException {
        AppInfo.getInstance().getMediaPlayer().dispose();
        sceneDisplay.displayScene("displayDrops.fxml", event);
    }

    private void replaceMediaPlayer(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();

        // Get the MediaView instance from SceneDisplay
            MediaView newMediaView = AppInfo.getInstance().mediaView;

        // Get the current stage and set its scene with the new root
        Stage stage = (Stage) newMediaView.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }



}
