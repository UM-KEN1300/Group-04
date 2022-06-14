package com.crazyputting3d.UI;
import com.badlogic.gdx.Game;


public class MenuTest extends Game {
    public MenuTest(){
    }
    @Override
    public void create() {

        SplashScreen splashScreen = new SplashScreen();
        setScreen(splashScreen);
    }
}


