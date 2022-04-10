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
    private ImageIcon img; 

    /**
    * run() creates frame and buttons and adds Action Listeners. 
    * Outputs the sound for the initial ball hit. 
    */

    public void run(){

        frame = new JFrame("CRAZY PUTTING - Group 04");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocation(320, 150);
        img = new ImageIcon("assets\\title6.jpeg");
        background = new JLabel("",img,JLabel.CENTER);
        background.setBounds(0,0,1000,700);
        jb1 = new JButton("Game");
        jb1.setSize(90, 50);
        jb1.setLocation(280, 60);
        jb2 = new JButton("Simulator");
        jb2.setSize(90, 50);
        jb2.setLocation(280, 120);
        jb3 = new JButton("Quit");
        jb3.setSize(90, 50);
        jb3.setLocation(280, 180);

        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
                config.setForegroundFPS(60);
                config.setTitle("Crazy Putting!");
                config.setWindowedMode(1280,720);
                new Lwjgl3Application(new game3d(true), config);
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
                new Lwjgl3Application(new game3d(false), config);
            }
        });

        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                System.exit(0);
            }
        });

        background.add(jb1);
        background.add(jb2);
        background.add(jb3);
        frame.add(background); 
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Menu start_up = new Menu(); 
        start_up.run(); 
    }
}