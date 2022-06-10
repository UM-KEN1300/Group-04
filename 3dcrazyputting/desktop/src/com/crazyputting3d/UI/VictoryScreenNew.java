package com.crazyputting3d.UI;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.crazyputting3d.Sounds.Sounds;

public class VictoryScreenNew implements Screen {
    private Stage stage;
    private Texture texture;
    private Image splashImage;
    private TextArea textField;
    private int numshots;
    private Skin skin;
    private TextButton quitButton;
    Sounds sound;

    @Override
    public void show() {
        sound = new Sounds();
        sound.victory();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skin flat earth/flat-earth-ui.json"));
        numshots = game3d.numShotsTaken;
        texture = new Texture("victory.png");
        splashImage = new Image(texture);
        stage.addActor(splashImage);
        textField = new TextArea("Number of shots = "+ numshots,skin );
        textField.setPosition(0,250);
        stage.addActor(textField);

        quitButton = new TextButton("Quit", skin);
        stage.addActor(quitButton);
        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
