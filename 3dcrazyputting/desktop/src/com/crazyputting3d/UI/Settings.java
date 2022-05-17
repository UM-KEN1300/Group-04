package com.crazyputting3d.UI;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.crazyputting3d.InputReader.inputEditor;
import java.awt.event.*;

/**
 * Settings Class.
 * Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 * Gabrijel Radovčić, Elza Strazda
 * version 1.0
 * since   2022-05-11
 */

public class Settings {

    //Create the instance variables 

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

    public void run(){
        /**
         * the run() methods creates the settings frame, with all its buttons and textfields
         */
        frame = new JFrame("Settings");
        frame.setResizable(false);
        frame.setLocation(320, 150);
        frame.setSize(200,500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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

        frame.add(fieldHoleXt);
        frame.add(fieldHoleYt);
        frame.add(fieldHoleX0);
        frame.add(fieldHoleY0);
        frame.add(jb1);
        frame.add(lb1);
        frame.add(lb2);
        frame.add(lb3);
        frame.add(lb4);

        frame.setLayout(null);
        frame.setVisible(true);
    }


    public void remove() {
        frame.dispose();
    }

}