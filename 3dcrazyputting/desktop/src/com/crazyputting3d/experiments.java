package com.crazyputting3d;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class experiments {
    public static void writeFunction() throws IOException {
        try (PrintWriter writer = new PrintWriter(new File("C:\\Users\\caspe\\Desktop\\new file\\3dcrazyputting\\assets\\experiment.txt"))) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 2*10*(Math.pow(2,15)); i++){
                float v0x = (float)(Math.random()*5);
                sb.append("v0x"+i+" = "+v0x);
                sb.append('\n');
                float v0y = (float)(Math.random()*5);
                sb.append("v0y"+i+" = "+v0y);
                sb.append('\n');

            }
            writer.write(sb.toString());
        }

        catch (FileNotFoundException e) {

            System.out.println(e.getMessage());
        } 
    } 
    public static void main(String[] args) throws IOException {
        writeFunction();
    }
}
