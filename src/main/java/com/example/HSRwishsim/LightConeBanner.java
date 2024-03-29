package com.example.HSRwishsim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.IOException;
import java.util.List;

public class LightConeBanner extends BannerController{

    @FXML
    Button switchToStandard;
    @FXML
    Button switchToEvent;
    @FXML
    Button checkDrops;
    @FXML
    Button singleSummon;
    @FXML
    Button multiSummon;
    @FXML
    Label EventBannerTickets;
    @FXML
    Label FiveStarPityLabel;
    @FXML
    Label FourStarPityLabel;
    @FXML
    Label PityActive;
    @FXML
    Button convertButton;
    @FXML
    Label jadeAmount;

    public static boolean summonedOnLightConeBanner = false;
    public static boolean checkingDrops = false;
    public static boolean ticketConversion = false;
    final double normal5StarLightConeRate = 0.008;   // 0.8%
    final double normal4StarCharacterOrWeaponRate = 0.066;  // 6.6%
    private int FiveStarLightConePity = appInfo.getFiveStarLightConePity();
    private int FourStarLightConePity = appInfo.getFourStarLightConePity();
    private final boolean seventyFivePity = appInfo.getSeventyFiveLightCone();

    public void initialize(){
        EventBannerTickets.setText("" + appInfo.getEventTickets());
        FiveStarPityLabel.setText("5* Lightcone Pity: " + appInfo.getFiveStarLightConePity()  + "/90");
        FourStarPityLabel.setText("4* Lightcone Pity: " + appInfo.getFourStarLightConePity() + "/10");
        if(seventyFivePity) {
            PityActive.setText("guaranteed Featured Lightcone: NO");
        } else{
            PityActive.setText("guaranteed Featured Lightcone: YES");
        }
        jadeAmount.setText("" + appInfo.getJade());
    }

    public void summoningMechanics(ActionEvent event) {

        FourStarLightConePity++;
        FiveStarLightConePity++;

        if (FiveStarLightConePity >= 90) {
            guaranteed5StarWeapon();
        } else if (FourStarLightConePity >= 10) {
            guaranteed4StarOr5StarCharacterOrWeapon();
        } else {
            normalSummon();
        }
    }

    private void guaranteed5StarWeapon() {
        if (seventyFivePity) {
            int winOrLost = rnd.nextInt(2);
            if (winOrLost == 0) {
                appInfo.setSeventyFiveLightCone(true);
                System.out.println("YOU WON 75/25! 5* FEATURED LIGHTCONE!");
                System.out.println("72/25 Pity: " + seventyFivePity);
                displayAndInventoryFor5StarLightCone(lightcones.getFeaturedFiveStarLightCones());
            } else {
                appInfo.setSeventyFiveLightCone(false);
                System.out.println("You LOST 75/25 ;-;");
                // lost 72/25
                // next time guaranteed
                System.out.println("75/25 Pity: " + seventyFivePity);
                displayAndInventoryFor5StarLightCone(lightcones.getFiveStarLightCones());
            }
        } else {
            appInfo.setSeventyFiveLightCone(true);
            System.out.println("(HIT PITY) guaranteed 5* featured lightcone");
            displayAndInventoryFor5StarLightCone(lightcones.getFeaturedFiveStarLightCones());
            System.out.println("72/25 Pity: " + seventyFivePity);
        }
    }

    private void guaranteed4StarOr5StarCharacterOrWeapon() {
        double fourStarOrFiveStar = rnd.nextDouble();

        if (fourStarOrFiveStar > 0.07) {
            int characterOrWeapon = rnd.nextInt(2);
            if(characterOrWeapon == 0){
                System.out.println("You got a guaranteed 4* Character!");
                displayAndInventoryFor4StarCharacter(characters.getFourStarCharacters());
            } else{
                int featuredOrNot = rnd.nextInt();
                if(featuredOrNot > 0.25){
                    System.out.println("You got a guaranteed featured 4* Lightcone!");
                    displayAndInventoryFor4StarLightCone(lightcones.getFeaturedFourStarLightCones());
                } else{
                    System.out.println("You got a guaranteed non-featured 4* Lightcone!");
                    displayAndInventoryFor4StarLightCone(lightcones.getFourStarLightCones());
                }
            }
        } else {
            if (seventyFivePity) {
                int winOrLost = rnd.nextInt(2);
                if (winOrLost == 0) {
                    appInfo.setSeventyFiveLightCone(true);
                    System.out.println("YOU WON 75/25! 4* PITY WITH A 5* FEATURED LIGHTCONE!");
                    System.out.println("72/25 Pity: " + seventyFivePity);
                    displayAndInventoryFor5StarLightCone(lightcones.getFeaturedFiveStarLightCones());
                } else {
                    appInfo.setSeventyFiveLightCone(false);
                    System.out.println("You LOST 75/25 ;-;");
                    // lost 72/25
                    // next time guaranteed
                    System.out.println("75/25 Pity: " + seventyFivePity);
                    displayAndInventoryFor5StarLightCone(lightcones.getFiveStarLightCones());
                }
            } else {
                appInfo.setSeventyFiveLightCone(true);
                System.out.println("(4* PITY HIT) guaranteed 5* featured lightcone");
                displayAndInventoryFor5StarLightCone(lightcones.getFeaturedFiveStarLightCones());
                System.out.println("72/25 Pity: " + seventyFivePity);
            }
        }
    }

    private void normalSummon() {
        double generatedNumber = rnd.nextDouble();

        if (generatedNumber < normal5StarLightConeRate) {
            if (seventyFivePity) {
                int winOrLost = rnd.nextInt(2);
                if (winOrLost == 0) {
                    appInfo.setSeventyFiveLightCone(true);
                    System.out.println("YOU WON 75/25! 5* FEATURED LIGHTCONE!");
                    System.out.println("72/25 Pity: " + seventyFivePity);
                    displayAndInventoryFor5StarLightCone(lightcones.getFeaturedFiveStarLightCones());
                } else {
                    appInfo.setSeventyFiveLightCone(false);
                    System.out.println("You LOST 75/25 ;-;");
                    // lost 72/25
                    // next time guaranteed
                    System.out.println("75/25 Pity: " + seventyFivePity);
                    displayAndInventoryFor5StarLightCone(lightcones.getFiveStarLightCones());
                }
            } else {
                appInfo.setSeventyFiveLightCone(true);
                System.out.println("(BEFORE PITY) guaranteed 5* featured lightcone");
                displayAndInventoryFor5StarLightCone(lightcones.getFeaturedFiveStarLightCones());
                System.out.println("72/25 Pity: " + seventyFivePity);
            }
        } else if (generatedNumber < (normal5StarLightConeRate + normal4StarCharacterOrWeaponRate)) {
            displayAndInventoryFor4Star();
        } else {
            displayAndInventoryFor3Star(lightcones.getThreeStarLightCones());
        }
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
            int featuredOrNot = rnd.nextInt();
            if(featuredOrNot > 0.25){
                System.out.println("You got a featured 4* Lightcone!");
                displayAndInventoryFor4StarLightCone(lightcones.getFeaturedFourStarLightCones());
            } else{
                System.out.println("You got a non-featured 4* Lightcone!");
                displayAndInventoryFor4StarLightCone(lightcones.getFourStarLightCones());
            }
        } else {
            System.out.println("You got a 4* Character!");
            displayAndInventoryFor4StarCharacter(characters.getFourStarCharacters());
        }
        FourStarLightConePity = 0;
    }

    private void displayAndInventoryFor4StarCharacter(List<String> items) {
        int generatedValue = rnd.nextInt(items.size());
        appInfo.displayDrops("(4*)(C) " + items.get(generatedValue));
        appInfo.addCharacterToInventory("(4*)(C) " + items.get(generatedValue));
        has4Star = true;
        FourStarLightConePity = 0;
    }

    private void displayAndInventoryFor4StarLightCone(List<String> items) {
        int generatedValue = rnd.nextInt(items.size());
        appInfo.displayDrops("(4*)(W) " + items.get(generatedValue));
        appInfo.addLightConeToInventory("(4*)(W) " + items.get(generatedValue));
        has4Star = true;
        FourStarLightConePity = 0;
    }

    private void displayAndInventoryFor3Star(List<String> items) {
        int generatedValue = rnd.nextInt(items.size());
        appInfo.displayDrops("(3*)(W) " + items.get(generatedValue));
    }

    private void resetPities() {
        FourStarLightConePity = 0;
        FiveStarLightConePity = 0;
    }


    public void singleSummon(ActionEvent event) throws IOException {
        summonXTimes(event, 1);
    }

    public void multiSummon(ActionEvent event) throws IOException {
        summonXTimes(event, 10);
    }

    private void summonXTimes(ActionEvent event, int numberOfSummons) throws IOException {

        if (appInfo.getEventTickets() < numberOfSummons) {
            System.out.println("Require at least " + numberOfSummons + " event tickets");
            return;
        }

        for (int summon = 0; summon < numberOfSummons; summon++) {
            summoningMechanics(event);
        }

        appInfo.setSummonsDone(appInfo.getSummonsDone() + numberOfSummons);
        appInfo.setEventTickets(appInfo.getEventTickets() - numberOfSummons);
        displayWishAnimation(event);
        appInfo.setFiveStarLightConePity(FiveStarLightConePity);
        appInfo.setFourStarLightConePity(FourStarLightConePity);
    }


    private void displayWishAnimation(ActionEvent event) throws IOException {
        summonedOnLightConeBanner = true;
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

    public void checkDrops(ActionEvent event) throws IOException{
        sceneDisplay.displayScene("checkDrops.fxml",event);
        checkingDrops = true;
    }

    public void switchToStandardBanner(ActionEvent event) throws IOException {
        sceneDisplay.displayScene("standardBanner.fxml",event);
    }

    public void switchToEventBanner(ActionEvent event) throws IOException{
        sceneDisplay.displayScene("eventBanner.fxml",event);
    }

    public void switchToConversion(ActionEvent event) throws IOException{
        sceneDisplay.displayScene("ticketConversion.fxml", event);
        ticketConversion = true;
    }

}
