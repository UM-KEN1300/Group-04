package com.crazyputting3d.UI;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Authors: Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van
 * Gelder, Trinh Le, Gabrijel Radovčić, Elza Strazda
 * version 3.0
 * since 2022-05-11
 */

public class MultiplayerScreen implements Screen {
    private Stage stage;
    private TextButton backButton;
    private MenuScreen screen;
    private Skin skin;
    private Label label;

    public MultiplayerScreen(MenuScreen screen){
        this.screen = screen;
    }
    @Override
    public void show() {

        stage = new Stage();
        skin = new Skin(Gdx.files.internal("skin default/uiskin.json"));
        backButton = new TextButton("Back to MainMenu", skin);
        stage.addActor(backButton);

        label = new Label("Server is not Online....", skin);
        label.setPosition(220,200);
        stage.addActor(label);

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(screen);
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        Gdx.input.setInputProcessor(stage);
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
