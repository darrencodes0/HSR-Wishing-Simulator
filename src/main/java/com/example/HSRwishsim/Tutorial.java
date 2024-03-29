package com.example.HSRwishsim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import java.io.IOException;

public class Tutorial{

@FXML
CheckBox confirmBox;
@FXML
Button confirmationButton;

final private SceneDisplay sceneDisplay = new SceneDisplay();

    public void initialize(){
        System.out.println("Please read through the entire info page.");
    }

    public void switchToTitleScreen(ActionEvent event) throws IOException {
        if (confirmBox.isSelected()){
            sceneDisplay.displayScene("TitleScreen.fxml", event);
        } else{
            sceneDisplay.displayAlert("Please confirm that you have read and understood everything before proceeding");
        }

    }







}
