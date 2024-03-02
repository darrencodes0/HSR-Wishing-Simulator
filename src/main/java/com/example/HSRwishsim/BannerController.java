
package com.example.HSRwishsim;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Random;

public abstract class BannerController {
    protected final Random rnd = new Random();
    protected boolean has4Star = false;
    protected boolean has5Star = false;
    Characters characters = new Characters();
    Lightcones lightcones = new Lightcones();
    SceneDisplay sceneDisplay = new SceneDisplay();
    DisplayDrops displayDrops = new DisplayDrops();
    AppInfo appInfo = AppInfo.getInstance();
    public static boolean checkingDrops = false;
    public static boolean ticketConversion = false;
    final int summonCost = 160;
    final double normal5StarCharacterRate = 0.006;   // 0.6%
    final double normal4StarCharacterOrWeaponRate = 0.051;  // 5.1%
    final double normal3StarWeapon = 0.943;   // 94.3%

    public abstract void summoningMechanics(ActionEvent event) throws IOException;
    public abstract void singleSummon(ActionEvent event) throws IOException;
    public abstract void multiSummon(ActionEvent event) throws IOException;
    public abstract void checkDrops(ActionEvent event) throws IOException;
}
