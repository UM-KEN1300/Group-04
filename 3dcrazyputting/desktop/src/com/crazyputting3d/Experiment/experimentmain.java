package com.crazyputting3d.Experiment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import com.crazyputting3d.Engine.physicsEngine;
import com.crazyputting3d.Objects.StateVector;

public class experimentmain {
    public static void writeCSV(String[] time) {

        try (PrintWriter writer = new PrintWriter(new File("3dcrazyputting\\desktop\\src\\com\\crazyputting3d\\Experiment\\Experiment.csv"))) {

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < time.length; i++) {

                sb.append(time[i]);
                sb.append('\n');

            }
            writer.write(sb.toString());
        }

        catch (FileNotFoundException e) {

            System.out.println(e.getMessage());
        }
    }
    // inpiut used is: 
    // x0 = 0.0
    // y0 = 0.0
    // xt = 2.0
    // yt = 2.0
    // r = 0.15
    // muk = 0.1
    // mus = 0.2
    // heightProfile = Math.exp(-(x*x+y*y)/40)
    public static void main(String[] args) throws IOException {
        // first we calculate exact value like aproximation of our runge kutta 4 solver with small step size
        physicsEngine engine2 = new physicsEngine();
        StateVector vector2 = new StateVector(0, 0, 1, 0);
        physicsEngine.h = 0.0000001;
        physicsEngine.solvernum=2;
        vector2 = engine2.start(vector2, false);
        double exact = vector2.getX();
        // then we run for k different step sizes and for 4 different solvers
        int k = 10;
        String[] errors = new String[k];
        for (int i = k; i > 0; i--) {
            errors[k-i] = 1.0/i + ",";
            for (int j = 0; j < 6; j++) {
                if(j==3||j==5){
                    continue;
                }
                StateVector vector = new StateVector(0, 0, 1, 0);
                physicsEngine engine = new physicsEngine();
                physicsEngine.h = 1.0/i;
                physicsEngine.solvernum=j;
                vector = engine.start(vector, false); 
                double error = Math.abs(vector.getX()-exact);
                errors[k-i] += error + ",";
            }
        }
        writeCSV(errors);
    }
}
