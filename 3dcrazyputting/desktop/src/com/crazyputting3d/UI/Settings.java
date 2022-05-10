package com.crazyputting3d.UI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.JTextField;

import com.crazyputting3d.InputReader.inputEditor;

import java.awt.event.*;



public class Settings {

    private JFrame frame;
    private JTextField fieldHoleXt;
    private JTextField fieldHoleYt;
    private JTextField fieldHoleX0;
    private JTextField fieldHoleY0;
    private JButton jb1;
    private String contentXt;
    private String contentYt;
    private String contentX0;
    private String contentY0;
    private JLabel lb1;
    private JLabel lb2;
    private JLabel lb3;
    private JLabel lb4;
    private inputEditor ie;
    private int botInt;
    private JLabel label;
    private JButton jb6; 
    private JButton jb7; 
    private JButton jb8;
    private JButton jb9;

    public void run(){
        frame = new JFrame("Settings");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocation(320, 150);
        frame.setSize(200,500);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);

        ie = new inputEditor();

        fieldHoleXt = new JTextField();
        fieldHoleXt.setBounds(0,20,200,30);
        fieldHoleYt = new JTextField();
        fieldHoleYt.setBounds(0,70,200,30);

        fieldHoleX0 = new JTextField();
        fieldHoleX0.setBounds(0,120,200,30);
        fieldHoleY0 = new JTextField();
        fieldHoleY0.setBounds(0,170,200,30);

        jb1 = new JButton("Apply");
        jb1.setSize(70, 30);
        jb1.setLocation(55, 200);

        lb1 = new JLabel("X Position of the hole: ");
        lb1.setSize(200,10);
        lb1.setLocation(0,5);

        lb2 = new JLabel("Y Position of the hole: ");
        lb2.setSize(200,10);
        lb2.setLocation(0,55);

        lb3 = new JLabel("X Position of the ball: ");
        lb3.setSize(200,10);
        lb3.setLocation(0,105);

        lb4 = new JLabel("Y Position of the ball: ");
        lb4.setSize(200,10);
        lb4.setLocation(0,155);

        jb6 = new JButton();
        jb6.setText("Random Bot");
        jb6.setBounds(0, 240, 200, 20);
        jb6.setVisible(true);
        jb7 = new JButton();
        jb7.setText("Rule-based Bot");
        jb7.setBounds(0, 270, 200, 20);
        jb7.setVisible(true);
        jb8 = new JButton();
        jb8.setText("Hill Climbing");
        jb8.setBounds(0, 300, 200, 20);
        jb8.setVisible(true);
        jb9 = new JButton();
        jb9.setText("Newton-Raphson ");
        jb9.setBounds(0, 330, 200, 20);
        jb9.setVisible(true);

        label=new JLabel("Current bot chosen: Random Bot");
        label.setSize(200,20);
        label.setLocation(0,360);

        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

        jb6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botInt=0;
                label.setText("Current bot chosen: Random Bot");
            }
        });
        jb7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botInt=1;
                label.setText("Current bot chosen: Rule Based Bot");
            }
        });
        jb8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botInt=2;   
                label.setText("Current bot chosen: Hill Climbing Bot");         }
        });
        jb9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botInt=3; 
                label.setText("Current bot chosen: Newton-Raphson Bot");           }
        });


        frame.add(fieldHoleXt);
        frame.add(fieldHoleYt);
        frame.add(fieldHoleX0);
        frame.add(fieldHoleY0);
        frame.add(jb1);
        frame.add(lb1);
        frame.add(lb2);
        frame.add(lb3);
        frame.add(lb4);
        frame.add(jb6);
        frame.add(jb7);
        frame.add(jb8);
        frame.add(jb9);
        frame.add(label);

        frame.setLayout(null);
        frame.setVisible(true);
    }

    public int getBotInt() {
        return botInt;
    }

    public void remove() {
        frame.dispose();
    }

    public static void main(String[] args) {
        Settings test = new Settings();
        test.run();
    }

}