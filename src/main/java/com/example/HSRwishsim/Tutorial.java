package com.example.HSRwishsim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import java.io.IOException;

public class Tutorial extends SceneDisplay{

@FXML
CheckBox confirmBox;
@FXML
Button confirmationButton;

    public void initialize(){
        System.out.println("Please read through the entire info page.");
    }

    public void switchToTitleScreen(ActionEvent event) throws IOException {
        if (confirmBox.isSelected()){
            displayScene("TitleScreen.fxml", event);
        } else{
            displayAlert("Please confirm that you have read and understood everything before proceeding");
        }

    }

    private void displayAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }






}
