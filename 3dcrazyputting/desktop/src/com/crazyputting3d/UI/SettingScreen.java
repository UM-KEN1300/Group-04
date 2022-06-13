package com.crazyputting3d.UI;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.crazyputting3d.InputReader.inputEditor;

import java.awt.*;


public class SettingScreen implements Screen{
    private Stage stage;
    private Skin skin;
    private TextButton backButton;
    private TextButton apply;
    private Label lb1, lb2, lb3, lb4, Title, player;
    private Table table, table2;
    private TextArea fieldHoleXt, fieldHoleYt, fieldHoleX0, fieldHoleY0, playerTag;
    private String contentXt;
    private String contentYt;
    private String contentX0;
    private String contentY0;
    private inputEditor ie;




    @Override
    public void show() {
        ie = new inputEditor();
        stage = new Stage();
        final MenuScreen menuScreen = new MenuScreen();
        skin = new Skin(Gdx.files.internal("skin default/uiskin.json"));

        Title = new Label("Settings", skin);
        Title.setPosition(170,250);
       // Title.setFontScale(5, 4);
        Title.setFontScale(4);
        Title.setSize(100,80);

        backButton = new TextButton("Back", skin);
        apply = new TextButton("Apply", skin);
        apply.setPosition(360,65);

        backButton.setSize(60,40);

        table = new Table();
        table2 = new Table();

        lb1 = new Label("X Position of the hole: ", skin);
        lb2 = new Label("Y Position of the hole: ", skin);
        lb3 = new Label("X Position of the ball: ", skin);
        lb4 = new Label("Y Position of the ball: ", skin);
        player = new Label("Player ID: ", skin);

        fieldHoleXt = new TextArea("",skin);
        fieldHoleYt = new TextArea("",skin);
        fieldHoleX0= new TextArea("",skin);
        fieldHoleY0= new TextArea("",skin);
        playerTag = new TextArea("", skin);

        table2.add(fieldHoleXt);
        table2.row().pad(10,0,10,0);
        table2.add(fieldHoleYt);
        table2.row();
        table2.add(fieldHoleX0);
        table2.row().pad(10,0,10,0);
        table2.add(fieldHoleY0);
        table2.row();
        table2.add(playerTag);
        table2.setPosition(280,150);



        table.add(lb1);
        table.row().pad(10,0,10,0);
        table.add(lb2);
        table.row();
        table.add(lb3);
        table.row().pad(10,0,10,0);
        table.add(lb4);
        table.row();
        table.add(player);
        table.setPosition(100,150);

        stage.addActor(apply);
        stage.addActor(Title);
        stage.addActor(table);
        stage.addActor(table2);
        stage.addActor(backButton);


        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(menuScreen);
            }
        });

        apply.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                contentXt = fieldHoleXt.getText();
                contentYt = fieldHoleYt.getText();

                contentX0 = fieldHoleX0.getText();
                contentY0 = fieldHoleY0.getText();

                fieldHoleXt.setText("");
                fieldHoleYt.setText("");

                fieldHoleX0.setText("");
                fieldHoleY0.setText("");


                if(contentXt!=null&&contentYt!=null) {
                    ie.editXtYt(Double.parseDouble(contentXt), Double.parseDouble(contentYt));
                }

                if(contentX0!=null&&contentY0!=null) {
                    ie.editX0Y0(Double.parseDouble(contentX0), Double.parseDouble(contentY0));
                }
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
