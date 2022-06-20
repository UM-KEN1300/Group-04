package com.crazyputting3d.Bots.Astar;

import java.awt.*;
import javax.swing.*;

/**
 * Author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 * Gabrijel Radovčić, Elza Strazda
 * version 3.0
 * since   2022-05-11
 */

public class AstarPanel extends JPanel {
    Astar astar;
    public Node[][] field;

    public AstarPanel(Node[][] field) {
        this.field=field;
    }

    public void run() {
        JFrame frame = new JFrame("A-Star Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setSize(750,800);
        frame.setLocationRelativeTo(null);
        frame.setLocation(0, 0);
        frame.toBack();
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;

        int ynode = 3;
        int xnode = 3;

        for(int x=0; x<field.length; x++) {
            for(int y=0; y<field[x].length; y++) {
                g2d.setColor(field[x][y].state);
                g2d.fillRect(x*xnode, y*ynode, xnode,ynode);
            }
        }

        //Draw the grid
        g2d.setColor(Color.BLACK);
        for(int x=0; x<field.length; x++) {
            for(int y=0; y<field[x].length; y++) {
                g2d.drawRect(x*xnode, y*ynode, xnode, ynode);
            }
        }
    }
}
