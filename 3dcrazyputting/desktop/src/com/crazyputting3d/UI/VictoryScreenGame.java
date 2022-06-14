package com.crazyputting3d.UI;
import com.badlogic.gdx.Game;


public class VictoryScreenGame extends Game {
    private game3d game;
    public VictoryScreenGame(game3d game){
        this.game = game;
    }
    @Override
    public void create() {
        VictoryScreenNew victoryScreenNew = new VictoryScreenNew(game);
        setScreen(victoryScreenNew);

    }
}