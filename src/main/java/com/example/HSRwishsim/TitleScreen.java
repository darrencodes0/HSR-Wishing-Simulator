package com.example.HSRwishsim;
import com.example.HSRwishsim.AppInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class TitleScreen extends SceneDisplay{

    @FXML
    private TextField InsertStellarJade;
    @FXML
    TextField InsertEventAndLightConeBannerTickets;
    @FXML
    TextField InsertStandardBannerTickets;

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
            displayAlert("INSERT JADE ERROR: Invalid input. Please enter a positive integer, if no jades, type in 0");
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
            displayAlert("INSERT STANDARD TICKETS ERROR: Invalid input. Please enter a positive integer, if NONE, type in 0");
            return;
        }

        try {
            eventTickets = Integer.parseInt(inputEventTickets);

            if (eventTickets < 0) {
                throw new NumberFormatException();
            }

            AppInfo.getInstance().setEventTickets(eventTickets);

        } catch (NumberFormatException e) {
            displayAlert("INSERT EVENT TICKETS ERROR: Invalid Input, Please enter a positive integer, if NONE, type in 0.");
            return;
        }

        super.switchToEventBanner(event);
    }


    private void displayAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}





