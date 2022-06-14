package com.crazyputting3d.Experiment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import com.crazyputting3d.Engine.physicsEngine;
import com.crazyputting3d.Objects.StateVector;

    /**
    * The class experimentmain is the class which computes the data for the experiments
    * Authors: Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen
    * van Gelder, Trinh Le,
    * Gabrijel Radovcic, Elza Strazda
    * version 2.0
    * since 2022-05-11
    */

public class experimentmain {
    /**
     * The writeCSV() method is used to write all the data computed to the Experiment.csv file. 
     */
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
    /**
    * input used is: 
    * x0 = 0.0
    * y0 = 0.0
    * xt = 2.0
    * yt = 2.0
    * r = 0.15
    * muk = 0.1
    * mus = 0.2
    * heightProfile = Math.exp(-(x*x+y*y)/40)
     */

    public static void main(String[] args) throws IOException {
        /**
         * First, we calculate a close to exact approximation using the Runge Kutta 4 solver with
         * a very small step size. 
         */
        
        physicsEngine engine2 = new physicsEngine(1);
        StateVector vector2 = new StateVector(0, 0, 1, 0);
        physicsEngine.h = 0.0000001;
        physicsEngine.solvernum=2;
        vector2 = engine2.start(vector2, false);
        double exact = vector2.getX();

        /**
         * Next, we run the the experiment for four different solvers, these solvers are:
         * Runge Kutta 2
         * Runge kutta 4
         * Eulers method
         * Verlets method
         * We run this experiment for k different step sizes and then compute the errors. 
         */

        int k = 10;
        String[] errors = new String[k];
        for (int i = k; i > 0; i--) {
            errors[k-i] = 1.0/i + ",";
            for (int j = 0; j < 6; j++) {
                if(j==3||j==5){
                    continue;
                }
                StateVector vector = new StateVector(0, 0, 1, 0);
                physicsEngine engine = new physicsEngine(1);
                physicsEngine.h = 1.0/i;
                physicsEngine.solvernum=j;
                vector = engine.experimentloop(vector); 
                double error = Math.abs(vector.getX()-exact);
                errors[k-i] += error + ",";
            }
        }
        writeCSV(errors);
    }
}
