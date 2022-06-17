package com.crazyputting3d.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
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
    private Label label4;
    private Label label5;
    private Label label6;
    private int numshots;
    private int numberOfIterations;
    private double runtime;
    private Skin skin;
    private TextButton quitButton;
    private Sounds sound;
    private TextButton playAgainButton;


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
            if(bot.getRuntime()!=0) {
                numberOfIterations = bot.getNumberOfIteretions();
                runtime = bot.getRuntime();
                label2 = new Label("Number of iterations: " + numberOfIterations, skin);
                label2.setPosition(0,180);
                stage.addActor(label2);
    
                label3 = new Label("Runtime: " + runtime + " ms",skin);
                label3.setPosition(0,160);
                stage.addActor(label3);
    
                label4 = new Label("Vx: " + bot.get_v0x() + "   Vy: " +bot.get_v0y(), skin);
                label4.setPosition(0,140);
                stage.addActor(label4);
            }
        }

        if(game.botInt==5) {
            label5 = new Label("Runtime: " + game.astarduration + " ms", skin);
            label5.setPosition(0,160);
            stage.addActor(label5); 

            label6 = new Label("A-star Pathfinding Algorithm", skin);
            label6.setPosition(0,180);
            stage.addActor(label6); 
        }

        quitButton = new TextButton("Quit", skin);
        stage.addActor(quitButton);
        playAgainButton = new TextButton("Play Again", skin);
        playAgainButton.setPosition(300,200);
        stage.addActor(playAgainButton);

        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
                System.exit(0);
            }
        });
        playAgainButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
                config.setForegroundFPS(60);
                config.setTitle("Crazy Putting!");
                config.setWindowedMode(600,360);
                new Lwjgl3Application(new MenuTest(), config);
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
