package com.example.HSRwishsim;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class TitleScreen{

    @FXML
    private TextField InsertStellarJade;
    @FXML
    TextField InsertEventAndLightConeBannerTickets;
    @FXML
    TextField InsertStandardBannerTickets;

    final private SceneDisplay sceneDisplay = new SceneDisplay();
    final private MediaController mediaController = MediaController.getInstance();

    public void initialize(){
        String audioFilePath = "src/main/resources/com/example/HSRwishsim/media/SpaceWalk.mp3";

        try {
            mediaController.setSoundPlayer(new File(audioFilePath).toURI().toURL().toString());
            mediaController.getMediaPlayer().play();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void switchToEventBanner(ActionEvent event) throws IOException, NumberFormatException{
        int jades;
        int eventTickets;
        int standardTickets;
        String inputJades = InsertStellarJade.getText();
        String inputStandardTickets = InsertStandardBannerTickets.getText();
        String inputEventTickets = InsertEventAndLightConeBannerTickets.getText();

        try {
            jades = Integer.parseInt(inputJades);

            if (jades < 0) {
                throw new NumberFormatException();
            }

            AppInfo.getInstance().setJades(jades);

        } catch (NumberFormatException e) {
            sceneDisplay.displayAlert("INSERT JADE ERROR: Invalid input. Please enter a positive integer, if no jades, type in 0");
            return;
        }

        try {
            standardTickets = Integer.parseInt(inputStandardTickets);

            if (standardTickets < 0) {
                throw new NumberFormatException();
            }

            AppInfo.getInstance().setStandardTickets(standardTickets);
            }
        catch (NumberFormatException e) {
            sceneDisplay.displayAlert("INSERT STANDARD TICKETS ERROR: Invalid input. Please enter a positive integer, if NONE, type in 0");
            return;
        }

        try {
            eventTickets = Integer.parseInt(inputEventTickets);

            if (eventTickets < 0) {
                throw new NumberFormatException();
            }

            AppInfo.getInstance().setEventTickets(eventTickets);

        } catch (NumberFormatException e) {
            sceneDisplay.displayAlert("INSERT EVENT TICKETS ERROR: Invalid Input, Please enter a positive integer, if NONE, type in 0.");
            return;
        }

        mediaController.getMediaPlayer().dispose();
        sceneDisplay.displayScene("eventBanner.fxml",event);
    }

}





