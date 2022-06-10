package com.crazyputting3d.UI;
import com.badlogic.gdx.Game;
import org.lwjgl.system.CallbackI;


public class MenuTest extends Game {

    @Override
    public void create() {

        SplashScreen splashScreen = new SplashScreen();
        setScreen(splashScreen);
    }
}


