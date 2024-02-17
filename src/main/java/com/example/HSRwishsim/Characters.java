package com.example.HSRwishsim;

import java.util.*;

public class Characters{
    private final ArrayList<String> FourStarCharacters = new ArrayList<>();
    private final ArrayList<String> FiveStarCharacters = new ArrayList<>();
    private final ArrayList<String> FeaturedFourStarCharacters = new ArrayList<>();
    private final ArrayList<String> FeaturedFiveStarCharacters = new ArrayList<>();
    private final Map<String,Integer> yourCharacters = new HashMap<>();

    public Characters(){
        addInFeatureFiveStarCharacters("Dan Heng - Imbibtor Lunae");
        addInFeatureFourStarCharacters("Yukong","Asta","March 7th");
        addInFiveStarCharacters("Dan Heng - Imbibtor Lunae","Himeko","Welt",
                "Bronya","Clara","Yanqing","Bailu","Gepard");
        addInFourStarCharacters("Yukong","Asta","March 7th","Pela","Natasha",
                "Serval","Arlan","Hook","Sushang","Qingque","Dan Heng"
                ,"Luka","Herta","Sampo","Tingyun");
    }

    private void addInFeatureFiveStarCharacters(String... characters){
        for(String character: characters){
            FeaturedFiveStarCharacters.add(character);
        }
    }

    private void addInFeatureFourStarCharacters(String... characters){
        for(String character: characters){
            FeaturedFiveStarCharacters.add(character);
        }
    }

    private void addInFiveStarCharacters(String... characters){
        for(String character: characters){
            FiveStarCharacters.add(character);
        }
    }

    private void addInFourStarCharacters(String... characters){
        for(String character: characters){
            FourStarCharacters.add(character);
        }
    }

    public List<String> getFeaturedFiveStarCharacters(){
        return FeaturedFiveStarCharacters;
    }

    public List<String> getFeaturedFourStarCharacters(){
        return FeaturedFourStarCharacters;
    }

    public List<String> getFourStarCharacters(){
        return FourStarCharacters;
    }

    public List<String> getFiveStarCharacters(){
        return FiveStarCharacters;
    }

    public Map<String, Integer> getYourCharacters() {
        return yourCharacters;
    }
}
