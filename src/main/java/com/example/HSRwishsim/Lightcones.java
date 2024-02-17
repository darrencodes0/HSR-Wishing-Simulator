package com.example.HSRwishsim;

import java.util.*;
public class Lightcones {

    public Lightcones(){
        addFiveStarLightCones();
        addFourStarLightCones();
        addThreeStarLightCones();
    }

    public void addFiveStarLightCones(){

    }

    public void addFourStarLightCones() {
        addInFourStarLightCones("Good Night and Sleep Well","Day One of My New Life","Only Silence Remains",
                "The Moles Welcome You","The Birth of the Self","Shared Feeling",
                "Landau's Choice","Swordplay","Planetary Rendezvous","Make the World Clamor",
                "Perfect Timing","Resolution Shines As Pearls of Sweat","Subscribe for More!",
                "Dance! Dance! Dance!", "Under the Blue Sky","Geniuses' Repose","Trend of the Universal Market",
                "A Secret Vow","Eyes of the Prey","Memories of the Past","Post-Op Conversation");
    }

    public void addThreeStarLightCones(){
        addInThreeStarLightCones("Cornucopia","Collapsing Sky", "Amber",
                "Chorus","Data Bank","Darting Arrow","Shattered Home",
                "Defense","Loop","Passkey","Adversarial","Multiplication",
                "Pioneering","Hidden Shadow","Mediation","Arrows","Void",
                "Fine Fruit","Meshing Cogs","Mutual Demise","Sagacity");
    }

    public void addInFourStarLightCones(String... lightcones){
        for(String lightcone: lightcones){
            FourStarLightCones.add(lightcone);
        }
    }

    public void addInFiveStarLightCones(String... lightcones){
        for(String lightcone: lightcones){
            FiveStarLightCones.add(lightcone);
        }
    }

    public void addInThreeStarLightCones(String... lightcones){
        for(String lightcone: lightcones){
            ThreeStarLightCones.add(lightcone);
        }
    }


    public ArrayList<String> getFiveStarLightCones(){
        return FiveStarLightCones;
    }

    public ArrayList<String> getFourStarLightCones(){
        return FourStarLightCones;
    }

    public ArrayList<String> getThreeStarLightCones(){
        return ThreeStarLightCones;
    }

    public Map<String, Integer> getYourLightCones() {
        return yourLightCones;
    }

    private final ArrayList<String> FiveStarLightCones = new ArrayList<>();
    private final ArrayList<String> FourStarLightCones = new ArrayList<>();
    private final ArrayList<String> ThreeStarLightCones = new ArrayList<>();
    private final Map<String,Integer> yourLightCones = new HashMap<>();

}
