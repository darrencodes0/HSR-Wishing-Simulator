package com.example.HSRwishsim;

import java.lang.reflect.Array;
import java.util.*;
public class Lightcones {

    public Lightcones(){
        addFeaturedFiveStarLightCones();
        addFeaturedFourStarLightCones();
        addFiveStarLightCones();
        addFourStarLightCones();
        addThreeStarLightCones();
    }

    public void addFeaturedFiveStarLightCones(){
        addInFeaturedFiveStarLightCones("Brighter Than The Sun");
    }

    public void addFeaturedFourStarLightCones(){
        addInFeaturedFourStarLightCones("Planetary Rendezvous",
                "Dance! Dance! Dance!",
                "Landau's Choice");
    }

    public void addFiveStarLightCones(){
        addInFiveStarLightCones("Sleep Like The Dead","Night on the Milky Way",
                "Battle isn't over", "Something Irreplacable");
    }

    public void addFourStarLightCones() {
        addInFourStarLightCones("Good Night and Sleep Well","Day One of My New Life","Only Silence Remains",
                "The Moles Welcome You","The Birth of the Self","Shared Feeling","Swordplay","Make the World Clamor",
                "Perfect Timing","Resolution Shines As Pearls of Sweat","Subscribe for More!", "Under the Blue Sky","Geniuses' Repose","Trend of the Universal Market",
                "A Secret Vow","Eyes of the Prey","Memories of the Past","Post-Op Conversation","Planetary Rendezvous",
                "Dance! Dance! Dance!",
                "Landau's Choice");
    }

    public void addThreeStarLightCones(){
        addInThreeStarLightCones("Cornucopia","Collapsing Sky", "Amber",
                "Chorus","Data Bank","Darting Arrow","Shattered Home",
                "Defense","Loop","Passkey","Adversarial","Multiplication",
                "Pioneering","Hidden Shadow","Mediation","Arrows","Void",
                "Fine Fruit","Meshing Cogs","Mutual Demise","Sagacity");
    }

    public void addInFeaturedFourStarLightCones(String... lightcones){
        for(String lightcone: lightcones){
            FeaturedFourStarLightCones.add(lightcone);
        }
    }

    public void addInFourStarLightCones(String... lightcones){
        for(String lightcone: lightcones){
            FourStarLightCones.add(lightcone);
        }
    }

    public void addInFeaturedFiveStarLightCones(String... lightcones){
        for(String lightcone: lightcones){
            FeaturedFiveStarLightCones.add(lightcone);
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

    public ArrayList<String> getFeaturedFiveStarLightCones() {
        return FeaturedFiveStarLightCones;
    }

    public ArrayList<String> getFeaturedFourStarLightCones() {
        return FeaturedFourStarLightCones;
    }

    public Map<String, Integer> getYourLightCones() {
        return yourLightCones;
    }

    private final ArrayList<String> FiveStarLightCones = new ArrayList<>();
    private final ArrayList<String> FourStarLightCones = new ArrayList<>();
    private final ArrayList<String> ThreeStarLightCones = new ArrayList<>();
    private final ArrayList<String> FeaturedFourStarLightCones = new ArrayList<>();
    private final ArrayList<String> FeaturedFiveStarLightCones = new ArrayList<>();

    private final Map<String,Integer> yourLightCones = new HashMap<>();

}
