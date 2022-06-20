package com.crazyputting3d.UI;
import com.badlogic.gdx.Game;

/**
 * Authors: Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van
 * Gelder, Trinh Le, Gabrijel Radovčić, Elza Strazda
 * version 3.0
 * since 2022-05-11
 */

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