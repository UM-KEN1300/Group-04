package com.crazyputting3d;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class experimentmain {
    public static void writeCSV(String[] time) {

        try (PrintWriter writer = new PrintWriter(new File("Function.csv"))) {
    
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < time.length; i++){
                
                sb.append(time[i]);
                sb.append('\n');
 
            }
            writer.write(sb.toString());
        }

        catch (FileNotFoundException e) {

            System.out.println(e.getMessage());
        }  
    }
}
