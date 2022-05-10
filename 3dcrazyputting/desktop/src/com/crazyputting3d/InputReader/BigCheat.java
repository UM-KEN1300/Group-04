package com.crazyputting3d.InputReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class BigCheat {
    /**
     * Class BigCheat must be run manually when changing the heightmap. The class creates a new cheat.java
     * file which acts as a new class which is responsible for distributing the heightmap function.
     * Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
     * Gabrijel Radovcic, Elza Strazda
     * version 1.0
     * since   2022-05-11
     */

    protected static int i;

    public static void writeCSV() throws IOException {
        //Get the path file and create a Search object
        try (PrintWriter writer = new PrintWriter(new File("3dcrazyputting\\desktop\\src\\com\\crazyputting3d\\InputReader\\cheat.java"))) {
            Search search = new Search("input.txt");
            //Create a stringbuilder and create the cheat.java file line by line
            StringBuilder sb = new StringBuilder();
            sb.append("package com.crazyputting3d.InputReader;");
            sb.append('\n');
            sb.append("public class cheat{");
            sb.append('\n');
            sb.append("public double getHeightFunction(double x, double y){");
            sb.append('\n');
            sb.append(" return " + search.get_heightProfile() + ";");
            sb.append('\n');
            sb.append("}");
            sb.append('\n');
            sb.append("}");
            sb.append('\n');
            writer.write(sb.toString());
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }  
    }
    
    public static void main(String[] args) throws IOException {
        BigCheat.writeCSV();
    }
}
