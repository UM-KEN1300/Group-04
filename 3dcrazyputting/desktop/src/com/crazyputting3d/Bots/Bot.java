package com.crazyputting3d.Bots;
import com.crazyputting3d.physicsEngine;
import com.crazyputting3d.InputReader.cheat;

/**
 * Bot Abstract class. 
 * author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 *          Gabrijel Radovčić, Elza Strazda
 * version 1.0
 * since   2022-05-11
 */

public abstract class Bot {

    public physicsEngine engine;
    public double x0;
    public double y0;
    public double xt;
    public double yt;
    public double speed;
    public double directionX;
    public double directionY;
    public final double length = 0.3;
    public double speedX;
    public double speedY;
    public double slopex;
    public double slopey;
    public final double pi = 3.141159;
    public double radius;
    public double angle_step = pi / 180;
    public double v = 5;
    public int i = 1;
    public boolean flag;
    public double holeX;
    public double holeY;
    public double ballX;
    public double ballY;

    public double h(double x, double y) {
        cheat cheat = new cheat();
        return cheat.getHeightFunction(x, y);
    }

    public double hxderivated(double x, double y) {
        double dx = 0.000000000001;
        double derivative = (h(x + dx, y) - h(x, y)) / dx;
        return derivative;
    }

    public double hyderivated(double x, double y) {
        double dy = 0.000000000001;
        double derivative = (h(x, y + dy) - h(x, y)) / dy;
        return derivative;
    }
}