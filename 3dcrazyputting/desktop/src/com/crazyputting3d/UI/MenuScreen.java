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
import com.crazyputting3d.Network.Client;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;


public class MenuScreen implements Screen {
    private Skin skin;
    private Stage stage;
    private Skin skinNeutralizer;
    private TextButton play;
    private TextButton playBot;
    private TextButton playMaze;
    private TextButton settings;
    private TextButton multiplayer;
    public SelectBox<String> botSelect;
    public SelectBox<String> solverSelect;
    private TextArea textField;
    private Table title;
    private Table quit;
    private CheckBox improvedPhysics;
    private CheckBox flyingBall;
    private CheckBox bouncing;
    private CheckBox aDiagonal;
    private TextButton quitButton;
    private Lwjgl3ApplicationConfiguration config2 = new Lwjgl3ApplicationConfiguration();
    private int selectedSolver;
    private int selectedBot;

    private Texture background;
    private Image splashImage;

    private LevelScreen levelScreen;
    private SettingScreen settingScreen;
    private MultiplayerScreen multiplayerScreen;
    private LevelScreenBot levelScreenBot;
    private MazeScreen mazeScreen;


    public MenuScreen(){
        levelScreen = new LevelScreen(this);
        levelScreenBot = new LevelScreenBot(this);
        mazeScreen = new MazeScreen(this);
        stage = new Stage();
        settingScreen = new SettingScreen();
        multiplayerScreen = new MultiplayerScreen(this);
        Gdx.input.setInputProcessor(stage);
    }




    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setPosition(300,220);

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
        playMaze = new TextButton("PlayMaze",skin);
        settings = new TextButton("Settings", skin);
        multiplayer = new TextButton("Multiplayer", skin);

       // dialogBot = new Dialog("Select Bot and Solver", skin);
        //dialogBot.setSize(250,150);
        botSelect = new SelectBox<String>(skinNeutralizer);
        botSelect.setItems("Hill Climbing", "Newton Raphson", "Random Based", "Rule Based", "Brute Force", "A* Maze","simulated anealing");
        //dialogBot.getContentTable().add(botSelect);
        //dialogBot.getContentTable().row();
        solverSelect = new SelectBox<String>(skinNeutralizer);
        solverSelect.setItems("Eulers Method", "Runge-Kutta 2", "Runge-Kutta 4", "Dormand Prince", "Verlets Method", "Predictor corrector");

        
        
        table1.add(botSelect);
        table1.row().pad(10,0,10,0);
        table1.add(solverSelect);


        improvedPhysics = new CheckBox("improvedPhysics", skinNeutralizer);
        improvedPhysics.setPosition(100, 10);
        flyingBall = new CheckBox("FlyingBall", skinNeutralizer);
        flyingBall.setPosition(250, 10);
        bouncing = new CheckBox("Bouncing", skinNeutralizer);
        bouncing.setPosition(350, 10);
        aDiagonal = new CheckBox("A*Diagonal", skinNeutralizer);
        aDiagonal.setPosition(100,30);

        stage.addActor(improvedPhysics);
        stage.addActor(flyingBall);
        stage.addActor(bouncing);
        stage.addActor(aDiagonal);

        table.add(play).fillX().uniformX();
        table.row().pad(10,0,10,0);
        table.add(playBot).fillX().uniformX();
        table.row();
        table.add(playMaze).fillX().uniformX();
        table.row().pad(10,0,10,0);
        table.add(multiplayer);
        table.row();
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
                selectedSolver = solverSelect.getSelectedIndex();
                selectedBot = botSelect.getSelectedIndex();
                ((Game)Gdx.app.getApplicationListener()).setScreen(levelScreenBot);
            }
        });
        playMaze.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectedSolver = solverSelect.getSelectedIndex();
                selectedBot = botSelect.getSelectedIndex();
                ((Game)Gdx.app.getApplicationListener()).setScreen(mazeScreen);
            }
        });
        multiplayer.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
           
                try {
                    //Exception will be thrown here if server is offline
                    Client client = new Client();
                    client.GetPlayerData();

                    //If no exception, join server
                    physicsEngine.solvernum = solverSelect.getSelectedIndex();
                    selectedSolver = solverSelect.getSelectedIndex();
                    selectedBot = botSelect.getSelectedIndex();
                    config2.setForegroundFPS(60);
                    config2.setTitle("Crazy Putting!");
                    config2.setWindowedMode(1280,720);
                    new Lwjgl3Application(new game3d(false,false,botSelect.getSelectedIndex(),11), config2);
                } catch (Exception e) {
                    ((Game)Gdx.app.getApplicationListener()).setScreen(multiplayerScreen);
                }
            }
        });
        quit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
                System.exit(0);
            }
        });

        settings.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(settingScreen);
            }
        });
        
        improvedPhysics.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(improvedPhysics.isChecked()){
                   physicsEngine.improvedPhysics=true;
                }
                else{
                    physicsEngine.improvedPhysics=false;
                }
            }
    });

        flyingBall.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(flyingBall.isChecked()){
                   physicsEngine.fly  = true;
                }
                else{
                    physicsEngine.fly  = false;
                }
            }
        });

        bouncing.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(bouncing.isChecked()){
                   physicsEngine.bouncing = true;
                }
                else{
                    physicsEngine.bouncing = false;
                }
                   
              
            }
        });
        aDiagonal.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
//                if(aDiagonal.isChecked()){
//
//                } else
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
    public int getSelectedSolver(){
        return selectedSolver;
    }
    public int getSelectedBot(){
        return selectedBot;
    }
}
