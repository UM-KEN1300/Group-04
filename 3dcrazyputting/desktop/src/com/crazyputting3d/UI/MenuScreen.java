package com.crazyputting3d.UI;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.crazyputting3d.Engine.physicsEngine;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import org.lwjgl.system.CallbackI;


public class MenuScreen implements Screen {
    private Skin skin;
    private Stage stage;
    private Skin skinNeutralizer;
    private TextButton play;
    private TextButton playBot;
    private TextButton settings;
    private TextButton multiplayer;
    private Dialog dialogBot;
    private Dialog dialogSolver;
    public static SelectBox<String> botSelect;
    public static SelectBox<String> solverSelect;
    private TextArea textField;
    private Table title;
    private Table quit;
    private TextButton quitButton;
    public static Lwjgl3ApplicationConfiguration config2 = new Lwjgl3ApplicationConfiguration();


    private Texture background;
    private Image splashImage;

    private LevelScreen levelScreen = new LevelScreen();
    private VictoryScreenNew victoryScreenNew = new VictoryScreenNew();
    private SettingScreen settingScreen = new SettingScreen();


    public MenuScreen(){
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
    }
    public static void CloseScreen(){
        config2.setInitialVisible(false);
    }




    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setPosition(300,200);

        background = new Texture("title6.jpeg");
        splashImage = new Image(background);

        stage.addActor(splashImage);

        Table table1 = new Table();
        table1.setPosition(80,90);
        stage.addActor(table);
        stage.addActor(table1);





        skin = new Skin(Gdx.files.internal("skin flat earth/flat-earth-ui.json"));
        skinNeutralizer = new Skin(Gdx.files.internal("skin default/uiskin.json"));

        //textField= new TextArea("Crazy Putting 3D!", skinNeutralizer);
        title = new Table();
        title.setPosition(120,300);
        title.add(textField);
       // stage.addActor(title);

        quit = new Table();
        quit.setPosition(40,30);
        quitButton = new TextButton("Quit", skin);
        quit.add(quitButton);
        stage.addActor(quit);

        play = new TextButton("Play",skin);
        playBot = new TextButton("PlayBot",skin);
        settings = new TextButton("Settings", skin);
        multiplayer = new TextButton("Multiplayer", skin);

       // dialogBot = new Dialog("Select Bot and Solver", skin);
        //dialogBot.setSize(250,150);
        botSelect = new SelectBox<String>(skinNeutralizer);
        botSelect.setItems("Hill Climbing", "Newton Raphson", "Random Based", "Rule Based", "Brute Force");
        //dialogBot.getContentTable().add(botSelect);
        //dialogBot.getContentTable().row();
        solverSelect = new SelectBox<String>(skinNeutralizer);
        solverSelect.setItems("Eulers Method", "Runge-Kutta 2", "Runge-Kutta 4", "Dormand Prince", "Verlets Method", "Predictor corrector");

        table1.add(botSelect);
        table1.row().pad(10,0,10,0);
        table1.add(solverSelect);




        table.add(play).fillX().uniformX();
        table.row().pad(10,0,10,0);
        table.add(playBot).fillX().uniformX();
        table.row();
        table.add(multiplayer);
        table.row().pad(10,0,10,0);
        table.add(settings);


        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(levelScreen);
            }


        });
        playBot.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                physicsEngine.solvernum = solverSelect.getSelectedIndex();
                config2.setForegroundFPS(60);
                config2.setTitle("Crazy Putting!");
                config2.setWindowedMode(1280,720);
                new Lwjgl3Application(new game3d(false,true,botSelect.getSelectedIndex(),11), config2);

            }
        });
        multiplayer.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                physicsEngine.solvernum = solverSelect.getSelectedIndex();
                config2.setForegroundFPS(60);
                config2.setTitle("Crazy Putting!");
                config2.setWindowedMode(1280,720);
                new Lwjgl3Application(new game3d(false,false,botSelect.getSelectedIndex(),11), config2);
            }
        });
        quit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        settings.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                ((Game)Gdx.app.getApplicationListener()).setScreen(settingScreen);
            }
        });



    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(),1/30f));
        stage.draw();


//
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
        stage.dispose();
    }
}
