package com.crazyputting3d.UI;

import java.io.IOException;

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

public class LevelScreen implements Screen {
    private Stage stage;
    private TextButton backButton;
    private TextButton selectLevel;
    private Skin skin;
    private Texture texture;
    private Texture texture2;
    private Image splashImage;
    private Image splashImage2;
    private MenuScreen screen;
    public Lwjgl3ApplicationConfiguration config2 = new Lwjgl3ApplicationConfiguration();
    public LevelScreen(MenuScreen screen){
        this.screen = screen;
    }

    @Override
    public void show() {
        stage = new Stage();


        texture = new Texture("3dcrazyputting\\assets\\LevelImage Medium 5.jpeg");
        splashImage = new Image(texture);
        splashImage.setPosition(0,40);
        texture2 = new Texture("3dcrazyputting\\assets\\Screenshot 2022-06-09 at 14.14.29.png");
        splashImage2 = new Image(texture2);

        final MenuScreen menuScreen = new MenuScreen();
        skin = new Skin(Gdx.files.internal("skin freeze\\freezing-ui.json"));
        Gdx.input.setInputProcessor(stage);

        backButton = new TextButton("Back", skin);
        selectLevel = new TextButton("Select your Level", skin);
        selectLevel.setPosition(200,280);

        stage.addActor(splashImage2);
        stage.addActor(splashImage);
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
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(),1/30f));
        if(Gdx.input.getX()>81 && Gdx.input.getX()<150 && Gdx.input.getY() > 110 && Gdx.input.getY() <175 ){
            if(Gdx.input.isTouched()) {
                try {
                    FunctionReader.writeCSV(1);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                physicsEngine.solvernum = screen.getSelectedSolver();
                config2.setForegroundFPS(60);
                config2.setTitle("Crazy Putting!");
                config2.setWindowedMode(1280,720);
                new Lwjgl3Application(new game3d(true,false,screen.getSelectedBot(),1), config2);
            }
        }
        if(Gdx.input.getX()>186 && Gdx.input.getX()<247 && Gdx.input.getY() > 110 && Gdx.input.getY() <175 ){
            if(Gdx.input.isTouched()) {
                try {
                    FunctionReader.writeCSV(2);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                physicsEngine.solvernum = screen.getSelectedSolver();
                config2.setForegroundFPS(60);
                config2.setTitle("Crazy Putting!");
                config2.setWindowedMode(1280,720);
                new Lwjgl3Application(new game3d(true,false,screen.getSelectedBot(),2), config2);
            }
        }
        if(Gdx.input.getX()>285 && Gdx.input.getX()<351 && Gdx.input.getY() > 110 && Gdx.input.getY() <175 ){
            if(Gdx.input.isTouched()) {
                try {
                    FunctionReader.writeCSV(3);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                physicsEngine.solvernum = screen.getSelectedSolver();
                config2.setForegroundFPS(60);
                config2.setTitle("Crazy Putting!");
                config2.setWindowedMode(1280,720);
                new Lwjgl3Application(new game3d(true,false,screen.getSelectedBot(),3), config2);
            }
        }
        if(Gdx.input.getX()>388 && Gdx.input.getX()<456 && Gdx.input.getY() > 110 && Gdx.input.getY() <175 ){
            if(Gdx.input.isTouched()) {
                try {
                    FunctionReader.writeCSV(4);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                physicsEngine.solvernum = screen.getSelectedSolver();
                config2.setForegroundFPS(60);
                config2.setTitle("Crazy Putting!");
                config2.setWindowedMode(1280,720);
                new Lwjgl3Application(new game3d(true,false,screen.getSelectedBot(),4), config2);
            }
        }
        if(Gdx.input.getX()>491 && Gdx.input.getX()<556 && Gdx.input.getY() > 110 && Gdx.input.getY() <175 ){
            if(Gdx.input.isTouched()) {
                try {
                    FunctionReader.writeCSV(5);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                physicsEngine.solvernum = screen.getSelectedSolver();
                config2.setForegroundFPS(60);
                config2.setTitle("Crazy Putting!");
                config2.setWindowedMode(1280,720);
                new Lwjgl3Application(new game3d(true,false,screen.getSelectedBot(),5), config2);
            }
        }
        if(Gdx.input.getX()>81 && Gdx.input.getX()<150 && Gdx.input.getY() > 222 && Gdx.input.getY() <280 ){
            if(Gdx.input.isTouched()) {
                try {
                    FunctionReader.writeCSV(6);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                physicsEngine.solvernum = screen.getSelectedSolver();
                config2.setForegroundFPS(60);
                config2.setTitle("Crazy Putting!");
                config2.setWindowedMode(1280,720);
                new Lwjgl3Application(new game3d(true,false,screen.getSelectedBot(),6), config2);
            }
        }
        if(Gdx.input.getX()>186 && Gdx.input.getX()<247 && Gdx.input.getY() > 222 && Gdx.input.getY() <280 ){
            if(Gdx.input.isTouched()) {
                try {
                    FunctionReader.writeCSV(7);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                physicsEngine.solvernum = screen.getSelectedSolver();
                config2.setForegroundFPS(60);
                config2.setTitle("Crazy Putting!");
                config2.setWindowedMode(1280,720);
                new Lwjgl3Application(new game3d(true,false,screen.getSelectedBot(),7), config2);
            }
        }
        if(Gdx.input.getX()>285 && Gdx.input.getX()<351 && Gdx.input.getY() > 222 && Gdx.input.getY() <280){
            if(Gdx.input.isTouched()) {
                try {
                    FunctionReader.writeCSV(8);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                physicsEngine.solvernum = screen.getSelectedSolver();
                config2.setForegroundFPS(60);
                config2.setTitle("Crazy Putting!");
                config2.setWindowedMode(1280,720);
                new Lwjgl3Application(new game3d(true,false,screen.getSelectedBot(),8), config2);
            }
        }
        if(Gdx.input.getX()>388 && Gdx.input.getX()<456 && Gdx.input.getY() > 222 && Gdx.input.getY() < 280 ){
            if(Gdx.input.isTouched()) {
                try {
                    FunctionReader.writeCSV(9);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                physicsEngine.solvernum = screen.getSelectedSolver();
                config2.setForegroundFPS(60);
                config2.setTitle("Crazy Putting!");
                config2.setWindowedMode(1280,720);
                new Lwjgl3Application(new game3d(true,false,screen.getSelectedBot(),9), config2);
            }
        }
        if(Gdx.input.getX()>491 && Gdx.input.getX()<556 && Gdx.input.getY() > 222 && Gdx.input.getY() < 280){
            if(Gdx.input.isTouched()) {
                try {
                    FunctionReader.writeCSV(10);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                physicsEngine.solvernum = screen.getSelectedSolver();
                config2.setForegroundFPS(60);
                config2.setTitle("Crazy Putting!");
                config2.setWindowedMode(1280,720);
                new Lwjgl3Application(new game3d(true,false,screen.getSelectedBot(),10), config2);
            }
        }


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
