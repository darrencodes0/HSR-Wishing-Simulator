package com.example.HSRwishsim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.io.IOException;


public class CheckDrops extends SceneDisplay {

    @FXML
    Label displayLightCones;
    @FXML
    Label displayCharacters;
    @FXML
    Label CharacterBoxLabel;
    @FXML
    Label LightConeBoxLabel;

    public void initialize(){
        displayLightCones.setText(AppInfo.getInstance().displayLightConeInventory());
        displayCharacters.setText(AppInfo.getInstance().displayCharacterInventory());
    }

    public void switchToCurrentBanner(ActionEvent event) {
        try {
            if (EventBanner.checkingDrops) {
                super.displayScene("eventBanner.fxml", event);
                EventBanner.checkingDrops = false;
            } else if (LightConeBanner.checkingDrops) {
                super.displayScene("lightConeBanner.fxml", event);
                LightConeBanner.checkingDrops = false;
            } else if (StandardBanner.checkingDrops) {
                super.displayScene("standardBanner.fxml", event);
                StandardBanner.checkingDrops = false;
            }
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
