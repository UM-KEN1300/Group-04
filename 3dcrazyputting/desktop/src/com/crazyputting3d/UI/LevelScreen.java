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

public class LevelScreen implements Screen {
    private Stage stage;
    private TextButton backButton;
    private TextButton selectLevel;
    private Skin skin;
    private Texture texture;
    private Texture texture2;
    private Image splashImage;
    private Image splashImage2;
    public static Lwjgl3ApplicationConfiguration config2 = new Lwjgl3ApplicationConfiguration();

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
                System.out.println("Level 1");
                physicsEngine.solvernum = MenuScreen.solverSelect.getSelectedIndex();
                config2.setForegroundFPS(60);
                config2.setTitle("Crazy Putting!");
                config2.setWindowedMode(1280,720);
                new Lwjgl3Application(new game3d(true,false,MenuScreen.botSelect.getSelectedIndex()), config2);
            }
        }
        if(Gdx.input.getX()>186 && Gdx.input.getX()<247 && Gdx.input.getY() > 110 && Gdx.input.getY() <175 ){
            if(Gdx.input.isTouched()) {
                System.out.println("Level 2");
            }
        }
        if(Gdx.input.getX()>285 && Gdx.input.getX()<351 && Gdx.input.getY() > 110 && Gdx.input.getY() <175 ){
            if(Gdx.input.isTouched()) {
                System.out.println("Level 3");
            }
        }
        if(Gdx.input.getX()>388 && Gdx.input.getX()<456 && Gdx.input.getY() > 110 && Gdx.input.getY() <175 ){
            if(Gdx.input.isTouched()) {
                System.out.println("Level 4");
            }
        }
        if(Gdx.input.getX()>491 && Gdx.input.getX()<556 && Gdx.input.getY() > 110 && Gdx.input.getY() <175 ){
            if(Gdx.input.isTouched()) {
                System.out.println("Level 5");
            }
        }
        if(Gdx.input.getX()>81 && Gdx.input.getX()<150 && Gdx.input.getY() > 222 && Gdx.input.getY() <280 ){
            if(Gdx.input.isTouched()) {
                System.out.println("Level 6");
            }
        }
        if(Gdx.input.getX()>186 && Gdx.input.getX()<247 && Gdx.input.getY() > 222 && Gdx.input.getY() <280 ){
            if(Gdx.input.isTouched()) {
                System.out.println("Level 7");
            }
        }
        if(Gdx.input.getX()>285 && Gdx.input.getX()<351 && Gdx.input.getY() > 222 && Gdx.input.getY() <280){
            if(Gdx.input.isTouched()) {
                System.out.println("Level 8");
            }
        }
        if(Gdx.input.getX()>388 && Gdx.input.getX()<456 && Gdx.input.getY() > 222 && Gdx.input.getY() < 280 ){
            if(Gdx.input.isTouched()) {
                System.out.println("Level 9");
            }
        }
        if(Gdx.input.getX()>491 && Gdx.input.getX()<556 && Gdx.input.getY() > 222 && Gdx.input.getY() < 280){
            if(Gdx.input.isTouched()) {
                System.out.println("Level 10");
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