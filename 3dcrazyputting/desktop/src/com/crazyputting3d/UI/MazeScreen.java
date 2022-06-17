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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.crazyputting3d.Engine.physicsEngine;
import com.crazyputting3d.InputReader.FunctionReader;

import java.io.IOException;

public class MazeScreen implements Screen{
    private Stage stage;
    private TextButton backButton;
    private TextButton selectLevel;
    private Skin skin;
    private Texture texture;
    private Texture texture2;
    private Texture texture3;
    private Image image;
    private Image image2;
    private Image image3;
    private MenuScreen screen;
    public Lwjgl3ApplicationConfiguration config2 = new Lwjgl3ApplicationConfiguration();
    public MazeScreen(MenuScreen screen){
        this.screen = screen;
    }
    @Override
    public void show() {
        stage = new Stage();
        texture = new Texture("3dcrazyputting/assets/levelBot.jpeg");
        image = new Image(texture);
        image.setPosition(-25,100);
        texture2 = new Texture("3dcrazyputting/assets/LevelBot background.png");
        image2 = new Image(texture2);

        final MenuScreen menuScreen = new MenuScreen();
        skin = new Skin(Gdx.files.internal("skin freeze/freezing-ui.json"));
        Gdx.input.setInputProcessor(stage);

        backButton = new TextButton("Back", skin);
        selectLevel = new TextButton("Select your Level", skin);
        selectLevel.setPosition(180,280);

        stage.addActor(image2);
        stage.addActor(image);
        stage.addActor(backButton);
        stage.addActor(selectLevel);

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(menuScreen);
            }
        });
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.getX()>59 && Gdx.input.getX()<131 && Gdx.input.getY() > 160 && Gdx.input.getY() <230 ){
            if(Gdx.input.isTouched()) {
                try {
                    FunctionReader.writeCSV(13);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                physicsEngine.solvernum = screen.getSelectedSolver();
                config2.setForegroundFPS(60);
                config2.setTitle("Crazy Putting!");
                config2.setWindowedMode(1280,720);
                new Lwjgl3Application(new game3d(false,true,screen.getSelectedBot(),13), config2);
            }
        }

        if(Gdx.input.getX()>165 && Gdx.input.getX()<237 && Gdx.input.getY() > 160 && Gdx.input.getY() <230 ){
            if(Gdx.input.isTouched()) {
                try {
                    FunctionReader.writeCSV(11);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                physicsEngine.solvernum = screen.getSelectedSolver();
                config2.setForegroundFPS(60);
                config2.setTitle("Crazy Putting!");
                config2.setWindowedMode(1280,720);
                new Lwjgl3Application(new game3d(false,true,screen.getSelectedBot(),11), config2);

            }
        }
        if(Gdx.input.getX()>272 && Gdx.input.getX()<343 && Gdx.input.getY() > 160 && Gdx.input.getY() <230 ){
            if(Gdx.input.isTouched()) {
                try {
                    FunctionReader.writeCSV(12);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                physicsEngine.solvernum = screen.getSelectedSolver();
                config2.setForegroundFPS(60);
                config2.setTitle("Crazy Putting!");
                config2.setWindowedMode(1280,720);
                new Lwjgl3Application(new game3d(false,true,screen.getSelectedBot(),12), config2);
            }
        }
        if(Gdx.input.getX()>381 && Gdx.input.getX()<457 && Gdx.input.getY() > 160 && Gdx.input.getY() <230 ){
            if(Gdx.input.isTouched()) {
                try {
                    FunctionReader.writeCSV(14);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                physicsEngine.solvernum = screen.getSelectedSolver();
                config2.setForegroundFPS(60);
                config2.setTitle("Crazy Putting!");
                config2.setWindowedMode(1280,720);
                new Lwjgl3Application(new game3d(false,true,screen.getSelectedBot(),14), config2);
            }
        }
        if(Gdx.input.getX()>488 && Gdx.input.getX()<557 && Gdx.input.getY() > 160 && Gdx.input.getY() <230 ){
            if(Gdx.input.isTouched()) {
                try {
                    FunctionReader.writeCSV(15);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                physicsEngine.solvernum = screen.getSelectedSolver();
                config2.setForegroundFPS(60);
                config2.setTitle("Crazy Putting!");
                config2.setWindowedMode(1280,720);
                new Lwjgl3Application(new game3d(false,true,screen.getSelectedBot(),15), config2);

            }
        }

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
