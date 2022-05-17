package com.crazyputting3d;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import com.crazyputting3d.Objects.StateVector;

public class experimentmain {
    public static void writeCSV(String[] time) {

        try (PrintWriter writer = new PrintWriter(new File("Function.csv"))) {

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

    public static void main(String[] args) throws IOException {
        String[] errors = new String[100];
        for (int i = 100; i > 0; i--) {
            System.out.println(i);
            errors[100-i] = 0.1/i + ",";
            for (int j = 0; j < 6; j++) {
                if(j==3){
                    continue;
                }
                StateVector vector = new StateVector(0, 0, 1, 0);
                //physicsEngine engine = new physicsEngine(0.1/i);
                if(j==5){
                    //vector = engine.start(vector, true, j, (10.0*i)-3);
                }
                else{
                    //vector = engine.start(vector, true, j, 10.0*i); 
                }
                double error = Math.abs(vector.getX()- 0.2548415502915308);
                errors[100-i] += error + ",";
            }
        }
        writeCSV(errors);
    }
}
