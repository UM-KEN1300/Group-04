package com.crazyputting3d;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Menu {
    /**
     * The Menu class initializes the starting menu.
     * author  Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
     *          Gabrijel Radovčić, Elza Strazda
     * version 1.0
     * since   2021-03-11
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
    private boolean settingsFlag=true;
    Settings settings = new Settings();

    /**
    * run() creates frame and buttons and adds Action Listeners. 
    * Outputs the sound for the initial ball hit. 
    */

    public void run(){
        //Screen size is 315x560
        frame = new JFrame("CRAZY PUTTING - Group 04");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocation(620, 220);
        img = new ImageIcon("A:\\GitHub\\Group-04\\3dcrazyputting\\assets\\title6.jpeg");
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


        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
                config.setForegroundFPS(60);
                config.setTitle("Crazy Putting!");
                config.setWindowedMode(1280,720);
                new Lwjgl3Application(new game3d(true,false), config);
            }
        });

        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
                config.setForegroundFPS(60);
                config.setTitle("Crazy Putting!");
                config.setWindowedMode(1280,720);
                new Lwjgl3Application(new game3d(false,false), config);
            }
        });

        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                System.exit(0);
            }
        });

        jb4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                frame.dispose();
                Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
                config.setForegroundFPS(60);
                config.setTitle("Crazy Putting!");
                config.setWindowedMode(1280,720);
                new Lwjgl3Application(new game3d(false,true), config);
            }
        });

        jb5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(settingsFlag) {
                    settings.run();
                    settingsFlag=false;
                } else if(!settingsFlag) {
                    settings.remove();
                    settingsFlag = true;
                }
            }
        });


        background.add(jb1);
        background.add(jb2);
        background.add(jb3);
        background.add(jb4);
        background.add(jb5);
        background.add(title);
        frame.add(background); 
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Menu start_up = new Menu(); 
        start_up.run(); 
    }
    
}