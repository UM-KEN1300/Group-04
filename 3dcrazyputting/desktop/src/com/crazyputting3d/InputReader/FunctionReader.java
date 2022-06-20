package com.crazyputting3d.InputReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class FunctionReader {
    /**
     * Class FunctionReader must be run manually when changing the heightmap. The class creates a new cheat.java
     * file which acts as a new class which is responsible for distributing the heightmap function.
     * Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
     * Gabrijel Radovcic, Elza Strazda
     * version 2.0
     * since   2022-05-11
     */

    public static void writeCSV(int level) throws IOException {
        //Get the path file and create a Search object
        try (PrintWriter writer = new PrintWriter(new File("desktop/src/com/crazyputting3d/InputReader/Function.java"))) {
            Search search = new Search("inputFile"+level+".txt");

            //Create a stringbuilder and create the cheat.java file line by line
            StringBuilder sb = new StringBuilder();
            sb.append("package com.crazyputting3d.InputReader;");
            sb.append('\n');
            sb.append("public class Function{");
            sb.append('\n');
            sb.append("public double getHeightFunction(int level,double x, double y){");
            sb.append('\n');
            sb.append("switch (level){");
            sb.append('\n');
            sb.append("case 1: return 0;");
            sb.append('\n');
            sb.append("case 2: return 0.4*(0.9-Math.exp(-(Math.pow(x,2)+Math.pow(y,2))/8));");
            sb.append('\n');
            sb.append("case 3: return 0.4*(0.9-Math.exp(-(Math.pow(x,2)+Math.pow(y,2))/8));");
            sb.append('\n');
            sb.append("case 4: return Math.cos(Math.cos(x*y/3)-8);");
            sb.append('\n');
            sb.append("case 5: return 0.35*Math.sin(x)+0.45*Math.cos(y);");
            sb.append('\n');
            sb.append("case 6: return 0.4*(0.9-Math.exp(-0.2*(Math.pow(x,2)+Math.pow(y,2))/8));");
            sb.append('\n');
            sb.append("case 7: return 0.4*(0.9-Math.exp(-(Math.pow(x,2)+Math.pow(y,2))/8));");
            sb.append('\n');
            sb.append("case 8: return 0;");
            sb.append('\n');
            sb.append("case 9: return 0.4*(0.9-Math.exp(-(Math.pow(x,2)+Math.pow(y,2))/8));");
            sb.append('\n');
            sb.append("case 10: return 0.4*(0.9-Math.exp(-(Math.pow(x,2)+Math.pow(y,2))/8));");
            sb.append('\n');
            sb.append("case 11: return 0;");
            sb.append('\n');
            sb.append("case 12: return 0;");
            sb.append('\n');
            sb.append("case 13: return 0;");
            sb.append('\n');
            sb.append("case 14: return 0;");
            sb.append('\n');
            sb.append("case 15: return 0;");
            sb.append('\n');
            sb.append("default: return " + search.get_heightProfile() + ";");
            sb.append('\n');
            sb.append("}");
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
        FunctionReader.writeCSV(1);
    }
    
}
