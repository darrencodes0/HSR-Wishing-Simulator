package com.example.HSRwishsim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.IOException;
import java.util.List;

public class StandardBanner implements Banners{

    @FXML
    Button switchToLightCone;
    @FXML
    Button switchToEvent;
    @FXML
    Button checkDrops;
    @FXML
    Button singleSummon;
    @FXML
    Button multiSummon;
    @FXML
    Label StandardBannerTickets;
    @FXML
    Label FiveStarPityLabel;
    @FXML
    Label FourStarPityLabel;
    @FXML
    Button convertButton;
    @FXML
    Label jadeAmount;

    public void initialize(){
        StandardBannerTickets.setText("" + AppInfo.getInstance().getStandardTickets());
        FiveStarPityLabel.setText("5* Standard Pity: " + AppInfo.getInstance().getFiveStarStandardPity()  + "/90");
        FourStarPityLabel.setText("4* Standard Pity: " + AppInfo.getInstance().getFourStarStandardPity() + "/10");
        jadeAmount.setText("" + AppInfo.getInstance().getJade());
    }

    public static boolean checkingDrops = false;
    public static boolean ticketConversion = false;
    public static boolean summonedOnStandardBanner = false;
    private int FiveStarStandardPity = AppInfo.getInstance().getFiveStarStandardPity();
    private int FourStarStandardPity = AppInfo.getInstance().getFourStarStandardPity();
    double normal5StarCharacterRate = 0.006;   // 0.6%
    double normal4StarCharacterOrWeaponRate = 0.051;  // 5.1%
    double normal3StarWeapon = 0.943;   // 94.3%
    private boolean has4Star = false;
    private boolean has5Star = false;
    private final int standardTickets = AppInfo.getInstance().getStandardTickets();


    public void summoningMechanics(ActionEvent event) {

        FourStarStandardPity++;
        FiveStarStandardPity++;

        if (FiveStarStandardPity >= 90) {
            Guaranteed5StarWeapon();
        } else if (FourStarStandardPity >= 10) {
            Guaranteed4StarOr5StarCharacter();
        } else {
            NormalSummon();
        }
    }

    private void Guaranteed5StarWeapon() {
        System.out.println("You got a guaranteed 5* Weapon!");
        DisplayAndInventoryFor5StarLightCone(lightcones.getFiveStarLightCones());
    }

    private void Guaranteed4StarOr5StarCharacter() {
        double fourStarOrFiveStar = rnd.nextDouble();

        if (fourStarOrFiveStar > 0.07) {
            System.out.println("You got a guaranteed 4* Character!");
            DisplayAndInventoryFor4StarCharacter(characters.getFourStarCharacters());
        } else {
            System.out.println("You got a 5* Character from 4* pity!");
            DisplayAndInventoryFor5StarCharacter(characters.getFiveStarCharacters());
        }
    }

    private void NormalSummon() {
        double generatedNumber = rnd.nextDouble();

        if (generatedNumber < normal5StarCharacterRate) {
            System.out.println("You got a 5* Lightcone!");
            DisplayAndInventoryFor5StarLightCone(lightcones.getFiveStarLightCones());
        } else if (generatedNumber < (normal5StarCharacterRate + normal4StarCharacterOrWeaponRate)) {
            DisplayAndInventoryFor4Star();
        } else {
            DisplayAndInventoryFor3Star(lightcones.getThreeStarLightCones());
        }
    }

    private void DisplayAndInventoryFor5StarCharacter(List<String> items) {
        int generatedValue = rnd.nextInt(items.size());
        AppInfo.getInstance().displayDrops("(5*)(c) " + items.get(generatedValue));
        AppInfo.getInstance().addCharacterToInventory("(5*)(c) " + items.get(generatedValue));
        resetPities();
        has5Star = true;
        has4Star = true;
    }

    private void DisplayAndInventoryFor5StarLightCone(List<String> items) {
        int generatedValue = rnd.nextInt(items.size());
        AppInfo.getInstance().displayDrops("(5*)(W) " + items.get(generatedValue));
        AppInfo.getInstance().addLightConeToInventory("(5*)(W) " + items.get(generatedValue));
        resetPities();
        has5Star = true;
        has4Star = true;
    }

    private void DisplayAndInventoryFor4StarCharacter(List<String> items) {
        int generatedValue = rnd.nextInt(items.size());
        AppInfo.getInstance().displayDrops("(4*)(C) " + items.get(generatedValue));
        AppInfo.getInstance().addCharacterToInventory("(4*)(C) " + items.get(generatedValue));
        FourStarStandardPity = 0;
        has4Star = true;
    }

    private void DisplayAndInventoryFor4StarLightCone(List<String> items) {
        int generatedValue = rnd.nextInt(items.size());
        AppInfo.getInstance().displayDrops("(4*)(W) " + items.get(generatedValue));
        AppInfo.getInstance().addLightConeToInventory("(4*)(W) " + items.get(generatedValue));
        FourStarStandardPity = 0;
        has4Star = true;
    }

    private void DisplayAndInventoryFor3Star(List<String> items) {
        int generatedValue = rnd.nextInt(items.size());
        AppInfo.getInstance().displayDrops("(3*)(W) " + items.get(generatedValue));
    }

    private void DisplayAndInventoryFor4Star() {
        int lightConeOrCharacter = rnd.nextInt(2);
        if (lightConeOrCharacter == 0) {
            System.out.println("You got a 4* Lightcone!");
            DisplayAndInventoryFor4StarLightCone(lightcones.getFourStarLightCones());
        } else {
            System.out.println("You got a 4* Character!");
            DisplayAndInventoryFor4StarCharacter(characters.getFourStarCharacters());
        }
        FourStarStandardPity = 0;
    }


    private void resetPities() {
        FourStarStandardPity = 0;
        FiveStarStandardPity = 0;
    }

    public void singleSummon(ActionEvent event) throws IOException {
        summonXTimes(event, 1);
    }

    public void multiSummon(ActionEvent event) throws IOException {
        summonXTimes(event, 10);
    }

    private void summonXTimes(ActionEvent event, int numberOfSummons) throws IOException {

        if (standardTickets < numberOfSummons) {
            System.out.println("Require at least " + numberOfSummons + " event tickets");
            return;
        }

        for (int summon = 0; summon < numberOfSummons; summon++) {
            summoningMechanics(event);
        }

        AppInfo appInfo = AppInfo.getInstance();
        appInfo.setSummonsDone(appInfo.getSummonsDone() + numberOfSummons);
        appInfo.setStandardTickets(standardTickets - numberOfSummons);
        displayWishAnimation(event);
        appInfo.setFiveStarStandardPity(FiveStarStandardPity);
        appInfo.setFourStarStandardPity(FourStarStandardPity);
    }

    private void displayWishAnimation(ActionEvent event) throws IOException {
        summonedOnStandardBanner = true;
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

    public void switchToEventBanner(ActionEvent event) throws IOException{
        sceneDisplay.switchToEventBanner(event);
    }

    public void switchToLightConeBanner(ActionEvent event) throws IOException {
        sceneDisplay.displayScene("lightConeBanner.fxml",event);
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
