package com.crazyputting3d.Bots;
import java.io.IOException;
import com.crazyputting3d.physicsEngine;
import com.crazyputting3d.InputReader.cheat;
import com.crazyputting3d.Objects.StateVector;

public abstract class Bot {

    public physicsEngine engine;
    public double x0;
    public double y0;
    public double xt;
    public double yt;
    public double speed;
    public double directionX;
    public double directionY;
    final double length = 0.3;
    public double speedX;
    public double speedY;
    public double slopex;
    public double slopey;


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