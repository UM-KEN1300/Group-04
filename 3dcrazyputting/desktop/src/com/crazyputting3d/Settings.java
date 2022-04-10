package com.crazyputting3d;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.*;



public class Settings {

    private JFrame frame;
    private JTextField fieldHoleX;
    private JTextField fieldHoleY;
    private JButton jb1;
    private String contentX;
    private String contentY;
    private JLabel lb1;
    private JLabel lb2;
    private inputEditor ie;

    public void run(){
        frame = new JFrame("Victory!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocation(320, 150);
        frame.setSize(200,500);

        ie = new inputEditor();

        fieldHoleX = new JTextField();
        fieldHoleX.setBounds(0,20,200,30);
        fieldHoleY = new JTextField();
        fieldHoleY.setBounds(0,70,200,30);

        jb1 = new JButton("Apply");
        jb1.setSize(70, 30);
        jb1.setLocation(55, 100);

        lb1 = new JLabel("X Position of the hole: ");
        lb1.setSize(200,10);
        lb1.setLocation(0,5);

        lb2 = new JLabel("Y Position of the hole: ");
        lb2.setSize(200,10);
        lb2.setLocation(0,55);

        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentX = fieldHoleX.getText();
                contentY = fieldHoleY.getText();

                fieldHoleX.setText("");
                fieldHoleY.setText("");

                ie.editXtYt(Double.parseDouble(contentX), Double.parseDouble(contentY));
            }
        });

        
        frame.add(fieldHoleX);
        frame.add(fieldHoleY);
        frame.add(jb1);
        frame.add(lb1);
        frame.add(lb2);

        frame.setLayout(null);
        frame.setVisible(true);
    }



    public void remove() {
        frame.dispose();
    }

    public static void main(String[] args) {
        Settings test = new Settings();
        test.run();
    }

}