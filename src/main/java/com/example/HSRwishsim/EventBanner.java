package com.example.HSRwishsim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class EventBanner implements Banners{

    @FXML
    Label StellarJadeAmount;
    @FXML
    Label EventBannerTickets;
    @FXML
    Label FourStarPityLabel;
    @FXML
    Label FiveStarPityLabel;
    @FXML
    Label PityActive;
    @FXML
    Button singleSummon;
    @FXML
    Button multiSummon;
    @FXML
    Button switchToStandard;
    @FXML
    Button switchToLightCone;
    @FXML
    Button convertButton;

    private int FiveStarPity = AppInfo.getInstance().getFiveStarEventPity();
    private int FourStarPity = AppInfo.getInstance().getFourStarEventPity();
    double normal5StarCharacterRate = 0.006;   // 0.6%
    double normal4StarCharacterOrWeaponRate = 0.051;  // 5.1%
    double normal3StarWeapon = 0.943;   // 94.3%
    public static boolean summonedOnEventBanner = false;
    private final boolean fiftyFiftyPity = AppInfo.getInstance().getFiftyFiftyEvent();
    private boolean has4Star = false;
    private boolean has5Star = false;
    public static boolean checkingDrops = false;
    public static boolean ticketConversion = false;
    private final int eventTickets = AppInfo.getInstance().getEventTickets();
    final private SceneDisplay sceneDisplay = new SceneDisplay();

    public void initialize(){
        StellarJadeAmount.setText(""+AppInfo.getInstance().getJade());
        FiveStarPityLabel.setText("5* Event Pity: " + AppInfo.getInstance().getFiveStarEventPity()  + "/90");
        FourStarPityLabel.setText("4* Event Pity: " + AppInfo.getInstance().getFourStarEventPity() + "/10");
        EventBannerTickets.setText(""+AppInfo.getInstance().getEventTickets());
        if(fiftyFiftyPity) {
            PityActive.setText("Guaranteed Featured Character: NO");
        } else{
            PityActive.setText("Guaranteed Featured Character: YES");
        }
    }

    public void summoningMechanics(ActionEvent event) {

        FourStarPity++;
        FiveStarPity++;

        if (FiveStarPity >= 90) {
            Guaranteed5StarCharacter();
        } else if (FourStarPity >= 10) {
            Guaranteed4or5Star();
        } else {
            normalSummon();
        }
    }

    private void Guaranteed5StarCharacter() {
        if (fiftyFiftyPity) {
            int winOrLost = rnd.nextInt(2);
            if (winOrLost == 0) {
                AppInfo.getInstance().setFiftyFiftyEvent(true);
                System.out.println("YOU WON 50/50! 5* FEATURED CHARACTER");
                System.out.println("Fifty Fifty Pity: " + fiftyFiftyPity);
                DisplayAndInventoryFor5Star(characters.getFeaturedFiveStarCharacters());
            } else {
                AppInfo.getInstance().setFiftyFiftyEvent(false);
                System.out.println("You lost 50/50 :C");
                // lost 50/50
                // next time guaranteed
                System.out.println("Fifty Fifty Pity: " + fiftyFiftyPity);
                DisplayAndInventoryFor5Star(characters.getFiveStarCharacters());
            }
        } else {
            AppInfo.getInstance().setFiftyFiftyEvent(true);
            System.out.println("(HIT PITY) Guaranteed 5* featured character");
            DisplayAndInventoryFor5Star(characters.getFeaturedFiveStarCharacters());
            System.out.println("Fifty Fifty Pity: " + fiftyFiftyPity);
        }
    }


    private void Guaranteed4or5Star() {
        double fourStarOrFiveStar = rnd.nextDouble();

        if (fourStarOrFiveStar > 0.07) {
            System.out.println("You got a guaranteed 4* Character!");
            DisplayAndInventoryFor4StarCharacter(characters.getFourStarCharacters());
        } else {
            if (fiftyFiftyPity) {
                int winOrLost = rnd.nextInt(2);
                if (winOrLost == 0) {
                    AppInfo.getInstance().setFiftyFiftyEvent(true);
                    System.out.println("YOU WON 50/50! 5* FEATURED CHARACTER");
                    System.out.println("Fifty Fifty Pity: " + fiftyFiftyPity);
                    DisplayAndInventoryFor5Star(characters.getFeaturedFiveStarCharacters());
                } else {
                    AppInfo.getInstance().setFiftyFiftyEvent(false);
                    System.out.println("You lost 50/50 :C");
                    // lost 50/50
                    // next time guaranteed
                    System.out.println("Fifty Fifty Pity: " + fiftyFiftyPity);
                    DisplayAndInventoryFor5Star(characters.getFiveStarCharacters());
                }
            } else {
                AppInfo.getInstance().setFiftyFiftyEvent(true);
                System.out.println("(HIT PITY) Guaranteed 5* featured character");
                DisplayAndInventoryFor5Star(characters.getFeaturedFiveStarCharacters());
                System.out.println("Fifty Fifty Pity: " + fiftyFiftyPity);
            }
        }
    }

    private void normalSummon() {
        double generatedNumber = rnd.nextDouble();

        if (generatedNumber < normal5StarCharacterRate) {
            if (fiftyFiftyPity) {
                int winOrLost = rnd.nextInt(2);
                if (winOrLost == 0) {
                    AppInfo.getInstance().setFiftyFiftyEvent(true);
                    System.out.println("YOU WON 50/50! 5* FEATURED CHARACTER");
                    System.out.println("Fifty Fifty Pity: " + fiftyFiftyPity);
                    DisplayAndInventoryFor5Star(characters.getFeaturedFiveStarCharacters());
                } else {
                    AppInfo.getInstance().setFiftyFiftyEvent(false);
                    System.out.println("You lost 50/50 :C");
                    // lost 50/50
                    // next time guaranteed
                    System.out.println("Fifty Fifty Pity: " + fiftyFiftyPity);
                    DisplayAndInventoryFor5Star(characters.getFiveStarCharacters());
                }
            } else {
                AppInfo.getInstance().setFiftyFiftyEvent(true);
                System.out.println("(HIT PITY) Guaranteed 5* featured character");
                DisplayAndInventoryFor5Star(characters.getFeaturedFiveStarCharacters());
                System.out.println("Fifty Fifty Pity: " + fiftyFiftyPity);
            }
        } else if (generatedNumber < (normal5StarCharacterRate + normal4StarCharacterOrWeaponRate)) {
            DisplayFor4Star();
        } else {
            DisplayFor3Star(lightcones.getThreeStarLightCones());
        }
    }

    private void DisplayAndInventoryFor5Star(List<String> items) {
            int generatedValue = rnd.nextInt(items.size());
            AppInfo.getInstance().displayDrops("(5*)(C) " + items.get(generatedValue));
            AppInfo.getInstance().addCharacterToInventory("(5*)(C) " + items.get(generatedValue));
        has5Star = true;
        has4Star = true;
        resetPities();
    }

    private void DisplayAndInventoryFor4StarLightCone(List<String> items) {
        int generatedValue = rnd.nextInt(items.size());
        AppInfo.getInstance().displayDrops("(4*)(W) " + items.get(generatedValue));
        AppInfo.getInstance().addLightConeToInventory("(4*)(W) " + items.get(generatedValue));
        has4Star = true;
        FourStarPity = 0;
    }

    private void DisplayAndInventoryFor4StarCharacter(List<String> items) {
        int generatedValue = rnd.nextInt(items.size());
        AppInfo.getInstance().displayDrops("(4*)(C) " + items.get(generatedValue));
        AppInfo.getInstance().addCharacterToInventory("(4*)(C) " + items.get(generatedValue));
        has4Star = true;
        FourStarPity = 0;
    }

    private void DisplayFor3Star(List<String> items) {
        int generatedValue = rnd.nextInt(items.size());
        AppInfo.getInstance().displayDrops("(3*)(W) " + items.get(generatedValue));
    }

    private void DisplayFor4Star() {
        int lightConeOrCharacter = rnd.nextInt(2);
        if (lightConeOrCharacter == 0) {
            System.out.println("You got a 4* Lightcone!");
            DisplayAndInventoryFor4StarLightCone(lightcones.getFourStarLightCones());
        } else {
            System.out.println("You got a 4* Character!");
            DisplayAndInventoryFor4StarCharacter(characters.getFourStarCharacters());
        }
        FourStarPity = 0;
    }

    private void resetPities() {
        FourStarPity = 0;
        FiveStarPity = 0;
    }

    public void singleSummon(ActionEvent event) throws IOException {
        summonXTimes(event, 1);
    }

    public void multiSummon(ActionEvent event) throws IOException {
        summonXTimes(event, 10);
    }

    private void summonXTimes(ActionEvent event, int numberOfSummons) throws IOException {

        if (eventTickets < numberOfSummons) {
            System.out.println("Require at least " + numberOfSummons + " event tickets");
            return;
        }

        for (int summon = 0; summon < numberOfSummons; summon++) {
            summoningMechanics(event);
        }

        AppInfo appInfo = AppInfo.getInstance();
        appInfo.setSummonsDone(appInfo.getSummonsDone() + numberOfSummons);
        appInfo.setEventTickets(eventTickets - numberOfSummons);
        displayWishAnimation(event);
        appInfo.setFiveStarEventPity(FiveStarPity);
        appInfo.setFourStarEventPity(FourStarPity);
    }

    private void displayWishAnimation(ActionEvent event) throws IOException {
        summonedOnEventBanner = true;
        if(has5Star){
            sceneDisplay.displayScene("FiveStarSummon.fxml",event);
            has5Star = false;
            has4Star = false;
        } else if(has4Star){
            sceneDisplay.displayScene("FourStarSummon.fxml",event);
            has4Star = false;
        } else{
            sceneDisplay.displayScene("ThreeStarSummon.fxml",event);
        }
    }

    public void switchToLightConeBanner(ActionEvent event) throws IOException {
        sceneDisplay.displayScene("lightConeBanner.fxml",event);
    }

    public void switchToStandardBanner(ActionEvent event) throws IOException {
        sceneDisplay.displayScene("standardBanner.fxml",event);
    }

    public void switchToConversion(ActionEvent event) throws IOException{
        sceneDisplay.displayScene("ticketConversion.fxml", event);
        ticketConversion = true;
    }

    public void checkDrops(ActionEvent event) throws IOException{
       sceneDisplay.displayScene("checkDrops.fxml",event);
       checkingDrops = true;
    }

}