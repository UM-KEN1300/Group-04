package com.crazyputting3d.UI;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Authors: Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van
 * Gelder, Trinh Le, Gabrijel Radovčić, Elza Strazda
 * version 3.0
 * since 2022-05-11
 */

public class SplashScreen implements Screen {
    private Texture texture;
    private Image splashImage;
    private Stage stage = new Stage();
    public SplashScreen(){
    }

    @Override
    public void show() {

        texture = new Texture("title6.jpeg");
        splashImage = new Image(texture);

        stage.addActor(splashImage);
        splashImage.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(1.0f),Actions.delay(0.2f),Actions.run(new Runnable() {
            @Override
            public void run() {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
            }
        })));

    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
