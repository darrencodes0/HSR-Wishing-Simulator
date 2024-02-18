package com.example.HSRwishsim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FiveStarWish implements Initializable {

    @FXML
    Label Drops;
    @FXML
    MediaView mediaView;

    final private MediaController mediaController = MediaController.getInstance();
    final private SceneDisplay sceneDisplay = new SceneDisplay();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        mediaController.setVideoPlayer("src/main/resources/com/example/HSRwishsim/media/5STARSUMMON.mp4");
        mediaView.setMediaPlayer(MediaController.getInstance().getMediaPlayer());
        mediaController.getMediaPlayer().play();
        mediaController.getMediaPlayer().setOnEndOfMedia(() -> {
            try {
                replaceMediaPlayer("displayDrops.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void seeDrops(ActionEvent event) throws IOException {
        mediaController.getMediaPlayer().dispose();
        sceneDisplay.displayScene("displayDrops.fxml", event);
    }

    private void replaceMediaPlayer(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        Stage stage = (Stage) mediaView.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

}
