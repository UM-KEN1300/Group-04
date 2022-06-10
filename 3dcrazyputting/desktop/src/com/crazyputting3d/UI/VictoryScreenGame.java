package com.crazyputting3d.UI;
import com.badlogic.gdx.Game;
import com.crazyputting3d.UI.SplashScreen;
import com.crazyputting3d.UI.VictoryScreenNew;
import org.lwjgl.system.CallbackI;


public class VictoryScreenGame extends Game {

    @Override
    public void create() {
        VictoryScreenNew victoryScreenNew = new VictoryScreenNew();
        setScreen(victoryScreenNew);

    }
}