package com.crazyputting3d.UI;
import com.badlogic.gdx.Game;

/**
 * Authors: Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van
 * Gelder, Trinh Le, Gabrijel Radovčić, Elza Strazda
 * version 3.0
 * since 2022-05-11
 */

public class MenuTest extends Game {
    public MenuTest(){
    }
    @Override
    public void create() {

        SplashScreen splashScreen = new SplashScreen();
        setScreen(splashScreen);
    }
}


