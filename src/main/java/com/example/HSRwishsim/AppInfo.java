package com.example.HSRwishsim;

import java.util.*;

public class AppInfo {

    private int jades;
    private int summonsAvailable;
    private int summonsDone;
    private int FiveStarEventPity;
    private int FourStarEventPity;
    private int FiveStarStandardPity;
    private int FourStarStandardPity;
    private int FiveStarLightConePity;
    private int FourStarLightConePity;
    private int standardTickets;
    private int EventTickets;
    private int LightConeTickets;
    private boolean fiftyFiftyEvent = false;
    private boolean SeventyFiveLightCone = false;
    private String currentItemDrops = "";
    private static AppInfo instance;
    private Map<String, Integer> lightCones = new HashMap<>();
    private Map<String, Integer> characters = new HashMap<>();

    private AppInfo() {

    }

    public static synchronized AppInfo getInstance() {
        if (instance == null) {
            instance = new AppInfo();
        }
        return instance;
    }

    public void displayDrops(String itemName){
        currentItemDrops+=itemName+"\n";
    }

    public void addCharacterToInventory(String characterTag) {
        if(!characters.containsKey(characterTag)) {
            characters.put(characterTag, 1);
        } else{
            int currentValue = characters.get(characterTag);
            characters.put(characterTag, currentValue + 1);
        }
    }

    public void addLightConeToInventory(String weaponTag) {
        if(!lightCones.containsKey(weaponTag)) {
            lightCones.put(weaponTag, 1);
        } else{
            int currentValue = lightCones.get(weaponTag);
            lightCones.put(weaponTag, currentValue + 1);
        }
    }

    public String displayCharacterInventory(){
        String characterInv = "";
        for (Map.Entry<String, Integer> entry : this.characters.entrySet()) {
            String characterTag = entry.getKey();
            int value = entry.getValue();
            characterInv+=value + "x - " + characterTag + "\n";
        }
        return characterInv;
    }

    public String displayLightConeInventory(){
        String lightConeInv = "";
        for (Map.Entry<String, Integer> entry : this.lightCones.entrySet()) {
            String lightConesTag = entry.getKey();
            int value = entry.getValue();
            lightConeInv+=value + "x - " + lightConesTag + "\n";
        }
        return lightConeInv;
    }

    public void setJades(int jades) {
        this.jades = jades;
    }

    public void setSummonsDone(int summonsDone) {
        this.summonsDone = summonsDone;
    }

    public void setFiveStarEventPity(int fiveStarEventPity) {
        FiveStarEventPity = fiveStarEventPity;
    }

    public void setFourStarEventPity(int fourStarEventPity) {
        FourStarEventPity = fourStarEventPity;
    }

    public void setFiveStarLightConePity(int fiveStarLightConePity) {
        FiveStarLightConePity = fiveStarLightConePity;
    }

    public void setFourStarLightConePity(int fourStarLightConePity) {
        FourStarLightConePity = fourStarLightConePity;
    }

    public void setFiveStarStandardPity(int fiveStarStandardPity) {
        FiveStarStandardPity = fiveStarStandardPity;
    }

    public void setFourStarStandardPity(int fourStarStandardPity) {
        FourStarStandardPity = fourStarStandardPity;
    }

    public int getJade() {
        return jades;
    }

    public int getSummonsAvailable() {
        setSummonsAvailable();
        return summonsAvailable;
    }

    public int getSummonsDone() {
        return summonsDone;
    }
    public int getFiveStarEventPity() {
        return FiveStarEventPity;
    }

    public int getFourStarEventPity() {
        return FourStarEventPity;
    }

    public int getFiveStarStandardPity() {
        return FiveStarStandardPity;
    }

    public int getFourStarStandardPity() {
        return FourStarStandardPity;
    }

    public int getFourStarLightConePity() {
        return FourStarLightConePity;
    }

    public int getFiveStarLightConePity() {
        return FiveStarLightConePity;
    }

    private void setSummonsAvailable() {
        this.summonsAvailable = getJade() / 160;
    }

    public void setEventTickets(int eventTickets) {
        this.EventTickets = eventTickets;
    }

    public int getEventTickets(){
        return EventTickets;
    }

    public void setLightConeTickets(int lightConeTickets) {
        LightConeTickets = lightConeTickets;
    }

    public int getLightConeTickets() {
        return LightConeTickets;
    }

    public void setStandardTickets(int standardTickets){
        this.standardTickets = standardTickets;
    }

    public int getStandardTickets() {
        return standardTickets;
    }

    public String getCurrentItemDrops() {
        return currentItemDrops;
    }

    public void setCurrentItemDrops(String currentItemDrops) {
        this.currentItemDrops = currentItemDrops;
    }

    public boolean getFiftyFiftyEvent() {
        return fiftyFiftyEvent;
    }

    public void setFiftyFiftyEvent(boolean fiftyFiftyEvent) {
        this.fiftyFiftyEvent = fiftyFiftyEvent;
    }

    public boolean getSeventyFiveLightCone() {
        return SeventyFiveLightCone;
    }

    public void setSeventyFiveLightCone(boolean seventyFiveLightCone) {
        SeventyFiveLightCone = seventyFiveLightCone;
    }

   }
