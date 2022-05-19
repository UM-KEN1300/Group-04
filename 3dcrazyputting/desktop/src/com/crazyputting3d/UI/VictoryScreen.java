package com.crazyputting3d.UI;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.crazyputting3d.Sounds.Sounds;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class VictoryScreen will be initiated when the player has shot the ball into
 * the hole.
 * Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van
 * Gelder, Trinh Le,
 * Gabrijel Radovčić, Elza Strazda
 * version 1.0
 * since 2022-05-11
 */

public class VictoryScreen {

    private JFrame frame;
    private JLabel background;
    private JLabel label;
    private JLabel label1;
    private JLabel label2;
    private JButton jb2;
    private JButton jb3;
    private ImageIcon img;
    private int numShots;
    Sounds sound;

    /**
     * run() creates frame and buttons and adds Action Listeners.
     * It also receives an integer for the amount of shots the user has taken
     * to get the ball into the hole.
     */

    public void run(int numshots, boolean botPlays, int numberOfIterations, double runtime) {
        sound = new Sounds();
        sound.victory();

        this.numShots = numshots;
        label = new JLabel("Number of shots: " + numShots);
        label.setSize(500, 100);
        label.setLocation(40, 150);
        if (botPlays) {
            label1 = new JLabel("Number of iterations: " + numberOfIterations);
            label1.setSize(500, 100);
            label1.setLocation(10, 180);
            label2 = new JLabel("Runtime: " + runtime+" ms");
            label2.setSize(500, 100);
            label2.setLocation(10, 210);
        }
        if (numShots == 1) {
            label.setText("Hole in one!");
        }

        frame = new JFrame("Victory!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocation(320, 150);

        img = new ImageIcon("3dcrazyputting\\assets\\victory.png");
        background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0, 0, 1000, 700);

        jb2 = new JButton("Go back to main menu");
        jb2.setSize(200, 50);
        jb2.setLocation(205, 150);
        jb3 = new JButton("Quit");
        jb3.setSize(90, 50);
        jb3.setLocation(260, 210);

        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Menu start_up = new Menu();
                start_up.run();
            }
        });

        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        background.add(jb2);
        background.add(jb3);
        frame.add(label);
        frame.add(label1);
        frame.add(label2);
        frame.add(background);
        frame.pack();
        frame.setVisible(true);
    }

}