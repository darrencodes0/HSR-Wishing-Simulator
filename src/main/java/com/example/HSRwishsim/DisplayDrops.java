package com.example.HSRwishsim;

import com.example.HSRwishsim.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

public class DisplayDrops extends SceneDisplay {

@FXML
Button returnButton;
@FXML
Label drops;

    public void initialize(){
        drops.setText(AppInfo.getInstance().getCurrentItemDrops());
        AppInfo.getInstance().setCurrentItemDrops("");
    }

    public void switchToCurrentBanner(ActionEvent event) {
        try {
            if (EventBanner.summonedOnEventBanner) {
                displayScene("eventBanner.fxml", event);
                EventBanner.summonedOnEventBanner = false;
            } else if (LightConeBanner.summonedOnLightConeBanner) {
                displayScene("lightConeBanner.fxml", event);
                LightConeBanner.summonedOnLightConeBanner = false;
            } else if (StandardBanner.summonedOnStandardBanner) {
                displayScene("standardBanner.fxml", event);
                StandardBanner.summonedOnStandardBanner = false;
            }
        }catch(IOException e){
            e.printStackTrace();
        }

    }

}

