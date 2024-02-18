package com.example.HSRwishsim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.IOException;
import java.util.List;

public class LightConeBanner implements Banners{

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
    private int FiveStarLightConePity = AppInfo.getInstance().getFiveStarLightConePity();
    private int FourStarLightConePity = AppInfo.getInstance().getFourStarLightConePity();
    double normal5StarCharacterRate = 0.007;   // 0.7%
    double normal4StarCharacterOrWeaponRate = 0.06;  // 6%
    double normal3StarWeapon = 0.933;   // 93.3%
    private boolean has4Star = false;
    private boolean has5Star = false;
    private boolean seventyFivePity = AppInfo.getInstance().getSeventyFiveLightCone();
    private final int LightConeTickets = AppInfo.getInstance().getLightConeTickets();
    final private SceneDisplay sceneDisplay = new SceneDisplay();

    public void initialize(){
        EventBannerTickets.setText("" + AppInfo.getInstance().getEventTickets());
        FiveStarPityLabel.setText("5* Lightcone Pity: " + AppInfo.getInstance().getFiveStarLightConePity()  + "/90");
        FourStarPityLabel.setText("4* Lightcone Pity: " + AppInfo.getInstance().getFourStarLightConePity() + "/10");
        if(seventyFivePity) {
            PityActive.setText("Guaranteed Featured Lightcone: NO");
        } else{
            PityActive.setText("Guaranteed Featured Lightcone: YES");
        }
        jadeAmount.setText("" + AppInfo.getInstance().getJade());
    }

    public void summoningMechanics(ActionEvent event) {

        FourStarLightConePity++;
        FiveStarLightConePity++;

        if (FiveStarLightConePity >= 90) {
            Guaranteed5StarWeapon();
        } else if (FourStarLightConePity >= 10) {
            Guaranteed4StarOr5StarCharacterOrWeapon();
        } else {
            NormalSummon();
        }
    }

    private void Guaranteed5StarWeapon() {
        if (seventyFivePity) {
            int winOrLost = rnd.nextInt(2);
            if (winOrLost == 0) {
                AppInfo.getInstance().setSeventyFiveLightCone(true);
                System.out.println("YOU WON 75/25! 5* FEATURED LIGHTCONE!");
                System.out.println("72/25 Pity: " + seventyFivePity);
                DisplayAndInventoryFor5StarLightCone(lightcones.getFeaturedFiveStarLightCones());
            } else {
                AppInfo.getInstance().setSeventyFiveLightCone(false);
                System.out.println("You LOST 75/25 ;-;");
                // lost 72/25
                // next time guaranteed
                System.out.println("75/25 Pity: " + seventyFivePity);
                DisplayAndInventoryFor5StarLightCone(lightcones.getFiveStarLightCones());
            }
        } else {
            AppInfo.getInstance().setSeventyFiveLightCone(true);
            System.out.println("(HIT PITY) Guaranteed 5* featured lightcone");
            DisplayAndInventoryFor5StarLightCone(lightcones.getFeaturedFiveStarLightCones());
            System.out.println("72/25 Pity: " + seventyFivePity);
        }
    }

    private void Guaranteed4StarOr5StarCharacterOrWeapon() {
        double fourStarOrFiveStar = rnd.nextDouble();

        if (fourStarOrFiveStar > 0.07) {
            int characterOrWeapon = rnd.nextInt(2);
            if(characterOrWeapon == 0){
                System.out.println("You got a guaranteed 4* Character!");
                DisplayAndInventoryFor4StarCharacter(characters.getFourStarCharacters());
            } else{
                System.out.println("You got a guaranteed 4* Lightcone!");
                DisplayAndInventoryFor4StarLightCone(lightcones.getFourStarLightCones());
            }
        } else {
            if (seventyFivePity) {
                int winOrLost = rnd.nextInt(2);
                if (winOrLost == 0) {
                    AppInfo.getInstance().setSeventyFiveLightCone(true);
                    System.out.println("YOU WON 75/25! 4* PITY WITH A 5* FEATURED LIGHTCONE!");
                    System.out.println("72/25 Pity: " + seventyFivePity);
                    DisplayAndInventoryFor5StarLightCone(lightcones.getFeaturedFiveStarLightCones());
                } else {
                    AppInfo.getInstance().setSeventyFiveLightCone(false);
                    System.out.println("You LOST 75/25 ;-;");
                    // lost 72/25
                    // next time guaranteed
                    System.out.println("75/25 Pity: " + seventyFivePity);
                    DisplayAndInventoryFor5StarLightCone(lightcones.getFiveStarLightCones());
                }
            } else {
                AppInfo.getInstance().setSeventyFiveLightCone(true);
                System.out.println("(4* PITY HIT) Guaranteed 5* featured lightcone");
                DisplayAndInventoryFor5StarLightCone(lightcones.getFeaturedFiveStarLightCones());
                System.out.println("72/25 Pity: " + seventyFivePity);
            }
        }
    }

    private void NormalSummon() {
        double generatedNumber = rnd.nextDouble();

        if (generatedNumber < normal5StarCharacterRate) {
            if (seventyFivePity) {
                int winOrLost = rnd.nextInt(2);
                if (winOrLost == 0) {
                    AppInfo.getInstance().setSeventyFiveLightCone(true);
                    System.out.println("YOU WON 75/25! 5* FEATURED LIGHTCONE!");
                    System.out.println("72/25 Pity: " + seventyFivePity);
                    DisplayAndInventoryFor5StarLightCone(lightcones.getFeaturedFiveStarLightCones());
                } else {
                    AppInfo.getInstance().setSeventyFiveLightCone(false);
                    System.out.println("You LOST 75/25 ;-;");
                    // lost 72/25
                    // next time guaranteed
                    System.out.println("75/25 Pity: " + seventyFivePity);
                    DisplayAndInventoryFor5StarLightCone(lightcones.getFiveStarLightCones());
                }
            } else {
                AppInfo.getInstance().setSeventyFiveLightCone(true);
                System.out.println("(BEFORE PITY) Guaranteed 5* featured lightcone");
                DisplayAndInventoryFor5StarLightCone(lightcones.getFeaturedFiveStarLightCones());
                System.out.println("72/25 Pity: " + seventyFivePity);
            }
        } else if (generatedNumber < (normal5StarCharacterRate + normal4StarCharacterOrWeaponRate)) {
            DisplayAndInventoryFor4Star();
        } else {
            DisplayAndInventoryFor3Star(lightcones.getThreeStarLightCones());
        }
    }

    private void DisplayAndInventoryFor5StarCharacter(List<String> items) {
        int generatedValue = rnd.nextInt(items.size());
        AppInfo.getInstance().displayDrops("(5*)(C) " + items.get(generatedValue));
        AppInfo.getInstance().addCharacterToInventory("(5*)(C) " + items.get(generatedValue));
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
        has4Star = true;
        FourStarLightConePity = 0;
    }

    private void DisplayAndInventoryFor4StarLightCone(List<String> items) {
        int generatedValue = rnd.nextInt(items.size());
        AppInfo.getInstance().displayDrops("(4*)(W) " + items.get(generatedValue));
        AppInfo.getInstance().addLightConeToInventory("(4*)(W) " + items.get(generatedValue));
        has4Star = true;
        FourStarLightConePity = 0;
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
        FourStarLightConePity = 0;
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

        if (AppInfo.getInstance().getEventTickets() < numberOfSummons) {
            System.out.println("Require at least " + numberOfSummons + " event tickets");
            return;
        }

        for (int summon = 0; summon < numberOfSummons; summon++) {
            summoningMechanics(event);
        }

        AppInfo appInfo = AppInfo.getInstance();
        appInfo.setSummonsDone(appInfo.getSummonsDone() + numberOfSummons);
        appInfo.setEventTickets(appInfo.getEventTickets() - numberOfSummons);
        displayWishAnimation(event);
        appInfo.setFiveStarLightConePity(FiveStarLightConePity);
        appInfo.setFourStarLightConePity(FourStarLightConePity);
    }


    private void displayWishAnimation(ActionEvent event) throws IOException {
        summonedOnLightConeBanner = true;
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

    public void checkDrops(ActionEvent event) throws IOException{
        sceneDisplay.displayScene("checkDrops.fxml",event);
        checkingDrops = true;
    }

    public void switchToStandardBanner(ActionEvent event) throws IOException {
        sceneDisplay.displayScene("standardBanner.fxml",event);
    }

    public void switchToEventBanner(ActionEvent event) throws IOException{
        sceneDisplay.switchToEventBanner(event);
    }

    public void switchToConversion(ActionEvent event) throws IOException{
        sceneDisplay.displayScene("ticketConversion.fxml", event);
        ticketConversion = true;
    }

}
