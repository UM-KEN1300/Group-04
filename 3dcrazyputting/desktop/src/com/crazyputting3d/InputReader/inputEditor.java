package com.crazyputting3d.InputReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Input Editor class is used to remotely change the input.txt file. It is made to be used 
 * by the settings.java file. 
 * author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 * Gabrijel Radovčić, Elza Strazda
 * version 2.0
 * since   2022-05-11
 */

public class inputEditor {
    /**
     * Create the instance field for the input editor
     */

    double x0;
    double y0;
    double xt;
    double yt;
    double r;
    double muk;
    double mus;
    String heightprofile;
    double[] treeX;
    double[] treeY;
    double[] treeR;
    double[] sandPitX;
    double[] sandPitY;
    double muks;
    double muss;

    /**
     * editXtYt is the method for changing the location of the hole
     */
    public void editXtYt(double newxt, double newyt) {
        try {
            getInformation();
        } catch (IOException e) {
            e.printStackTrace();
        }
        xt = newxt;
        yt = newyt;
        try {
            writeCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * editX0Y0 is the method for changing the location of the ball
     */
    public void editX0Y0(double newx0, double newy0) {
        try {
            getInformation();
        } catch (IOException e) {
            e.printStackTrace();
        }
        x0 = newx0;
        y0 = newy0;
        try {
            writeCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Copy the data from the input.txt file at its current state
     */

    public void writeCSV() throws IOException{
        try (PrintWriter writer = new PrintWriter(new File("3dcrazyputting\\assets\\input.txt"))) {
            StringBuilder sb = new StringBuilder();
            sb.append("x0 = " + x0);
            sb.append('\n');
            sb.append("y0 = " + y0);
            sb.append('\n');
            sb.append("xt = " + xt);
            sb.append('\n');
            sb.append("yt = " + yt);
            sb.append('\n');
            sb.append("r = " + r);
            sb.append('\n');
            sb.append("muk = " + muk);
            sb.append('\n');
            sb.append("mus = " + mus);
            sb.append('\n');
            sb.append("heightProfile = " + heightprofile);
            sb.append('\n');

            for(int i=0; i<treeX.length; i++) {
                sb.append("TreeX" +(i+1)+" = " + treeX[i]);
                sb.append('\n');
            }

            for(int i=0; i<treeY.length; i++) {
                sb.append("TreeY" +(i+1)+" = " + treeY[i]);
                sb.append('\n');
            }

            for(int i=0; i<treeR.length; i++) {
                sb.append("TreeR" +(i+1)+" = " + treeR[i]);
                sb.append('\n');
            }

            for(int i=0; i<sandPitX.length; i++) {
                sb.append("sandPitX" +(i+1)+" = " + sandPitX[i]);
                sb.append('\n');
            }

            for(int i=0; i<sandPitY.length; i++) {
                sb.append("sandPitY" +(i+1)+" = " + sandPitY[i]);
                sb.append('\n');
            }

            sb.append("muks = " + muks);
            sb.append('\n');
            sb.append("muss = " + muss);

            writer.write(sb.toString());
        }

        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }  
    }

    /**
     * Set all the instance variables to the current state of the variables. 
     */

    public void getInformation() throws IOException {
        Search search = new Search("input.txt");
        x0 = search.get_x0(); y0 = search.get_y0();
        xt = search.get_xt(); yt = search.get_yt();
        r = search.get_r(); muk = search.get_muk(); 
        mus = search.get_mus(); heightprofile = search.get_heightProfile();
        treeX = search.get_treeX(); treeY = search.get_treeY(); treeR = search.get_treeR();
        sandPitX = search.get_sandPitX(); sandPitY = search.get_sandPitY();
        muks = search.get_muks(); muss = search.get_muss();
    }
}