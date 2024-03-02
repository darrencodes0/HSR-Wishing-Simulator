package com.example.HSRwishsim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.IOException;
import java.util.List;

public class StandardBanner extends BannerController{

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
        StandardBannerTickets.setText("" + appInfo.getStandardTickets());
        FiveStarPityLabel.setText("5* Standard Pity: " + appInfo.getFiveStarStandardPity()  + "/90");
        FourStarPityLabel.setText("4* Standard Pity: " + appInfo.getFourStarStandardPity() + "/10");
        jadeAmount.setText("" + appInfo.getJade());
    }

    public static boolean checkingDrops = false;
    public static boolean ticketConversion = false;
    public static boolean summonedOnStandardBanner = false;
    private int FiveStarStandardPity = appInfo.getFiveStarStandardPity();
    private int FourStarStandardPity = appInfo.getFourStarStandardPity();
    double normal5StarCharacterRate = 0.006;   // 0.6%
    double normal4StarCharacterOrWeaponRate = 0.051;  // 5.1%
    double normal3StarWeapon = 0.943;   // 94.3%
    private boolean has4Star = false;
    private boolean has5Star = false;
    private final int standardTickets = appInfo.getStandardTickets();

    public void summoningMechanics(ActionEvent event) {

        FourStarStandardPity++;
        FiveStarStandardPity++;

        if (FiveStarStandardPity >= 90) {
            guaranteed5StarCharacterorWeapon();
        } else if (FourStarStandardPity >= 10) {
            guaranteed4StarOr5StarWeaponorCharacter();
        } else {
            normalSummon();
        }
    }

    private void guaranteed5StarCharacterorWeapon() {
        int characterOrWeapon = rnd.nextInt(2);
        if(characterOrWeapon == 0){
            System.out.println("You got a 5* guaranteed Character!");
            displayAndInventoryFor5StarCharacter(characters.getAllFiveStarCharacters());
        } else{
            System.out.println("You got a guaranteed 5* Lightcone!");
            displayAndInventoryFor5StarLightCone(lightcones.getAllFiveStarLightCones());
        }
    }

    private void guaranteed4StarOr5StarWeaponorCharacter() {
        double fourStarOrFiveStar = rnd.nextDouble();

        if (fourStarOrFiveStar > 0.07) {
            int weaponOrCharacter = rnd.nextInt(2);
            if(weaponOrCharacter == 0) {
                System.out.println("You got a guaranteed 4* Character!");
                displayAndInventoryFor4StarCharacter(characters.getAllFourStarCharacters());
            } else{
                System.out.println("You got a guaranteed 4* Lightcone!");
                displayAndInventoryFor4StarLightCone(lightcones.getAllFourStarLightCones());
            }
        } else {
            int characterOrWeapon = rnd.nextInt(2);
            if(characterOrWeapon == 0){
                System.out.println("You got a 5* Character from 4* pity!");
                displayAndInventoryFor5StarCharacter(characters.getAllFiveStarCharacters());
            } else{
                System.out.println("You got a 5* Lightcone from 4* pity!");
                displayAndInventoryFor5StarLightCone(lightcones.getAllFiveStarLightCones());
            }
        }
    }

    private void normalSummon() {
        double generatedNumber = rnd.nextDouble();

        if (generatedNumber < normal5StarCharacterRate) {
            int characterOrWeapon = rnd.nextInt(2);
            if(characterOrWeapon == 0){
                System.out.println("You got a 5* Character!");
                displayAndInventoryFor5StarCharacter(characters.getAllFiveStarCharacters());
            } else{
                System.out.println("You got a 5* Lightcone!");
                displayAndInventoryFor5StarLightCone(lightcones.getAllFiveStarLightCones());
            }
        } else if (generatedNumber < (normal5StarCharacterRate + normal4StarCharacterOrWeaponRate)) {
            displayAndInventoryFor4Star();
        } else {
            displayAndInventoryFor3Star(lightcones.getThreeStarLightCones());
        }
    }

    private void displayAndInventoryFor5StarCharacter(List<String> items) {
        int generatedValue = rnd.nextInt(items.size());
        appInfo.displayDrops("(5*)(C) " + items.get(generatedValue));
        appInfo.addCharacterToInventory("(5*)(C) " + items.get(generatedValue));
        resetPities();
        has5Star = true;
        has4Star = true;
    }

    private void displayAndInventoryFor5StarLightCone(List<String> items) {
        int generatedValue = rnd.nextInt(items.size());
        appInfo.displayDrops("(5*)(W) " + items.get(generatedValue));
        appInfo.addLightConeToInventory("(5*)(W) " + items.get(generatedValue));
        resetPities();
        has5Star = true;
        has4Star = true;
    }

    private void displayAndInventoryFor4Star() {
        int lightConeOrCharacter = rnd.nextInt(2);
        if (lightConeOrCharacter == 0) {
            System.out.println("You got a 4* Lightcone!");
            displayAndInventoryFor4StarLightCone(lightcones.getAllFourStarLightCones());
        } else {
            System.out.println("You got a 4* Character!");
            displayAndInventoryFor4StarCharacter(characters.getAllFourStarCharacters());
        }
        FourStarStandardPity = 0;
    }

    private void displayAndInventoryFor4StarCharacter(List<String> items) {
        int generatedValue = rnd.nextInt(items.size());
        appInfo.displayDrops("(4*)(C) " + items.get(generatedValue));
        appInfo.addCharacterToInventory("(4*)(C) " + items.get(generatedValue));
        FourStarStandardPity = 0;
        has4Star = true;
    }

    private void displayAndInventoryFor4StarLightCone(List<String> items) {
        int generatedValue = rnd.nextInt(items.size());
        appInfo.displayDrops("(4*)(W) " + items.get(generatedValue));
        appInfo.addLightConeToInventory("(4*)(W) " + items.get(generatedValue));
        FourStarStandardPity = 0;
        has4Star = true;
    }

    private void displayAndInventoryFor3Star(List<String> items) {
        int generatedValue = rnd.nextInt(items.size());
        appInfo.displayDrops("(3*)(W) " + items.get(generatedValue));
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

        appInfo.setSummonsDone(appInfo.getSummonsDone() + numberOfSummons);
        appInfo.setStandardTickets(standardTickets - numberOfSummons);
        displayWishAnimation(event);
        appInfo.setFiveStarStandardPity(FiveStarStandardPity);
        appInfo.setFourStarStandardPity(FourStarStandardPity);
    }

    private void displayWishAnimation(ActionEvent event) throws IOException {
        summonedOnStandardBanner = true;
        if(has5Star){
            sceneDisplay.displayVideo("FiveStarSummon.fxml",event,"src/main/resources/com/example/HSRwishsim/media/5STARSUMMON.mp4");
            has5Star = false;
            has4Star = false;
        } else if(has4Star){
            sceneDisplay.displayVideo("FourStarSummon.fxml",event,"src/main/resources/com/example/HSRwishsim/media/4STARSUMMON.mp4");
            has4Star = false;
        } else{
            sceneDisplay.displayVideo("ThreeStarSummon.fxml",event,"src/main/resources/com/example/HSRwishsim/media/3STARSUMMON.mp4");
        }
    }

    public void switchToEventBanner(ActionEvent event) throws IOException{
        sceneDisplay.displayScene("eventBanner.fxml",event);
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
