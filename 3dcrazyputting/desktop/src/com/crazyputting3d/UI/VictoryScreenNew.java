package com.crazyputting3d.UI;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.crazyputting3d.Bots.Bot;
import com.crazyputting3d.Sounds.Sounds;

public class VictoryScreenNew implements Screen {
    private game3d game;
    private Bot bot;
    private Stage stage;
    private Texture texture;
    private Image splashImage;
    private Label label;
    private Label label2;
    private Label label3;
    private int numshots;
    private int numberOfIterations;
    private double runtime;
    private Skin skin;
    private TextButton quitButton;
    private Sounds sound;
    public VictoryScreenNew(game3d game){
        this.game = game;
        this.bot = game.getBot();
    }
    @Override
    public void show() {
        sound = new Sounds();
        sound.victory();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skin default/uiskin.json"));
        numshots = game.getNumOfShootsTaken();

        texture = new Texture("victory.png");
        splashImage = new Image(texture);
        stage.addActor(splashImage);
        label = new Label("Number of shots = "+ numshots,skin );
        if(numshots == 1){
            label.setText("Hole in one!");
        }
        label.setSize( 170,30);
        label.setPosition(0,200);
        stage.addActor(label);

        if(bot!=null){
            numberOfIterations = bot.getNumberOfIteretions();
            runtime = bot.getRuntime();
            label2 = new Label("Number of iterations: " + numberOfIterations, skin);
            label2.setPosition(0,180);
            stage.addActor(label2);

            label3 = new Label("Runtime: " + runtime + " ms",skin);
            label3.setPosition(0,160);
            stage.addActor(label3);
        }

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
