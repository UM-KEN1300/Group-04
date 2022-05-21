package com.crazyputting3d.UI;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.crazyputting3d.Engine.physicsEngine;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Menu class initializes the starting menu.
 * author  Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 * Gabrijel Radovčić, Elza Strazda
 * version 2.0
 * since   2021-03-11
 */
    

public class Menu {

    /**
     * Instance variables used to create the main menu frame.
     */

    private JFrame frame; 
    private JLabel background;
    private JButton jb1;  
    private JButton jb2;  
    private JButton jb3; 
    private JButton jb4; 
    private JButton jb5; 
    private ImageIcon img; 
    private JLabel title;
    public boolean settingsFlag=true;
    Settings settings = new Settings();
    private JComboBox<String> botSelect;
    private JComboBox<String> solverSelect;
    /**
    * run() creates frame and buttons and adds Action Listeners. 
    * Outputs the sound for the initial ball hit. 
    */

    public void run(){
        //Screen size is 560x315
        frame = new JFrame("CRAZY PUTTING - Group 04");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocation(420, 225);
        img = new ImageIcon("3dcrazyputting\\assets\\title6.jpeg");
        background = new JLabel("",img,JLabel.CENTER);
        background.setBounds(0,0,1280,720);
        jb1 = new JButton("Game");
        jb1.setSize(90, 50);
        jb1.setLocation(280, 80);
        jb2 = new JButton("Simulator");
        jb2.setSize(90, 50);
        jb2.setLocation(280, 140);
        jb4 = new JButton("Bot");
        jb4.setSize(90, 50);
        jb4.setLocation(280, 200);
        jb3 = new JButton("Quit");
        jb3.setSize(90, 50);
        jb3.setLocation(0, 315);
        jb5 = new JButton("Settings");
        jb5.setSize(90,50);
        jb5.setLocation(0, 0);

        title = new JLabel();
        title.setLocation(280,0);
        title.setSize(100,100);
        title.setText("Crazy Putting 3D!");

        String [] botNames = {"Hill Climbing", "Newton Raphson", "Random Based", "Rule Based", "Brute Force"};
        botSelect = new JComboBox<>(botNames);
        botSelect.setSelectedIndex(0);
        botSelect.setLocation(160,170);
        botSelect.setSize(110,20);

        String [] solverNames = {"Eulers Method", "Runge-Kutta 2", "Runge-Kutta 4", "Dormand Prince", "Verlets Method", "Predictor corrector"};
        solverSelect = new JComboBox<>(solverNames);
        solverSelect.setSelectedIndex(0);
        solverSelect.setLocation(160,140);
        solverSelect.setSize(110,20);



        //Button for playing the game
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                physicsEngine.solvernum = solverSelect.getSelectedIndex();
                frame.dispose();
                Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
                config.setForegroundFPS(60);
                config.setTitle("Crazy Putting!");
                config.setWindowedMode(1280,720);
                new Lwjgl3Application(new game3d(true,false,botSelect.getSelectedIndex()), config);
            }
        });

        //Button for launching the simulator
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                physicsEngine.solvernum = solverSelect.getSelectedIndex();
                frame.dispose();
                Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
                config.setForegroundFPS(60);
                config.setTitle("Crazy Putting!");
                config.setWindowedMode(1280,720);
                new Lwjgl3Application(new game3d(false,false,botSelect.getSelectedIndex()), config);
            }
        });

        //Quit button
        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                System.exit(0);
            }
        });

        //Button for playing with a bot
        jb4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                physicsEngine.solvernum = solverSelect.getSelectedIndex(); 
                frame.dispose();
                Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
                config.setForegroundFPS(60);
                config.setTitle("Crazy Putting!");
                config.setWindowedMode(1280,720);
                new Lwjgl3Application(new game3d(false,true,botSelect.getSelectedIndex()), config);
            }
        });

        //Button to create the settings frame
        jb5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.run();
            }
        });

        background.add(jb1);
        background.add(jb2);
        background.add(jb3);
        background.add(jb4);
        background.add(jb5);
        background.add(title);
        background.add(botSelect);
        background.add(solverSelect);
        frame.add(background); 
        frame.pack();
        frame.setVisible(true);
    }
    
}