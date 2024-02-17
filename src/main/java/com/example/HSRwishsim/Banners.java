package com.example.HSRwishsim;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.Random;

public interface Banners {
    Random rnd = new Random();
    Characters characters = new Characters();
    Lightcones lightcones = new Lightcones();
    SceneDisplay sceneDisplay = new SceneDisplay();
    DisplayDrops displayDrops = new DisplayDrops();
    int summonCost = 160;
    void summoningMechanics(ActionEvent event) throws IOException;
    void singleSummon(ActionEvent event) throws IOException;
    void multiSummon(ActionEvent event) throws IOException;

}
