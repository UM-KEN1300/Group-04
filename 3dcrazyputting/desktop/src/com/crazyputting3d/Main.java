package com.crazyputting3d;

import java.io.IOException;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.crazyputting3d.UI.MenuTest;

/**
 * This is the main class. To run the program, run the
 * main method of this class. 
 * Authors: Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van
 * Gelder, Trinh Le, Gabrijel Radovčić, Elza Strazda
 * version 2.0
 * since 2022-05-11
 */

public class Main {
    public static void main(String[] args) throws IOException {
 
            Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
            config.setForegroundFPS(60);
            config.setTitle("Crazy Putting!");
            config.setWindowedMode(600,360);
            new Lwjgl3Application(new MenuTest(), config);

    }
}
