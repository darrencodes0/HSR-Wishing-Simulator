package com.example.HSRwishsim;

import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class MediaController{

    private static MediaController instance;
    private MediaPlayer mediaPlayer;

    @FXML
    File file;
    Media media;

    private MediaController() {

    }


public static synchronized MediaController getInstance() {
        if (instance == null) {
        instance = new MediaController();
        }
        return instance;
        }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public Media getMedia() {
        return media;
    }

    public void setSoundPlayer(String mediaPath){
        if(mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }
        Media media = new Media(mediaPath);
        mediaPlayer = new MediaPlayer(media);
    }

    public void setVideoPlayer(String fileName){
        if(mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }
        file = new File(fileName);
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
    }

}