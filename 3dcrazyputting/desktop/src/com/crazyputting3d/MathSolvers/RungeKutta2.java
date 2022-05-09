package com.crazyputting3d.MathSolvers;

import com.crazyputting3d.Objects.StateVector;
import  com.crazyputting3d.physicsEngine;
import com.crazyputting3d.InputReader.cheat;

public class RungeKutta2 {

    public final double g = 9.81;

   
    public StateVector run(StateVector a, double m) {
        double x0 = a.getX();
        double y0 = a.getY();
        double vx0 = a.getVX();
        double vy0 = a.getVY();
        double ax0 = acelerationX(x0, y0, vx0, vy0, m);
        double ay0 = acelerationY(x0, y0, vx0, vy0, m);
        double x1 = x0 + vx0 * physicsEngine.h;
        double y1 = y0 + vy0 * physicsEngine.h;
        double vx1 = vx0 + ax0 * physicsEngine.h;
        double vy1 = vy0 + ay0 * physicsEngine.h;
        if (terminates(vx0, vy0, m)) {
            a.setX(x1);
            a.setY(y1);
            a.setVX(vx1);
            a.setVY(vy1);
            return a;
        }
        double ax1 = acelerationX(x1, y1, vx1, vy1, m);
        double ay1 = acelerationY(x1, y1, vx1, vy1, m);
        a.setX(x0 + physicsEngine.h * (1.0 / 2.0) * (vx0 + vx1));
        a.setY(y0 + physicsEngine.h * (1.0 / 2.0) * (vy0 + vy1));
        a.setVX(vx0 + physicsEngine.h * (1.0 / 2.0) * (ax0 + ax1));
        a.setVY(vy0 + physicsEngine.h * 1.0 / 2.0 * (ay0 + ay1));
        return a;
    }

    public boolean terminates(double vx, double vy, double m) {
        if ((vx < physicsEngine.h && vx > -physicsEngine.h ) && (vy < physicsEngine.h && vy > - physicsEngine.h )){
            return true;
        }
        return false;
    }

    public double acelerationX(double x, double y, double vx, double vy, double m) {
        return -g * hxderivated(x, y) - m * g * vx / (Math.sqrt(vx * vx + vy * vy));
    }

    public double acelerationY(double x, double y, double vx, double vy, double m) {
        return -g * hyderivated(x, y) - m * g * vy / (Math.sqrt(vx * vx + vy * vy));
    }

    
    public double h(double x, double y) {
        cheat cheat = new cheat();
        return cheat.getHeightFunction(x, y);
    }

    /**
     * hxderivated() is used to get the derivative of the height function in terms
     * of x.
     * param values for x and y, to be input once calling h(x, y).
     * returns derivative of the function in terms of x.
     */

    public double hxderivated(double x, double y) {
        double dx = 0.000000000001;
        double derivative = (h(x + dx, y) - h(x, y)) / dx;
        return derivative;
    }

    /**
     * hyderivated() is used to get the derivative of the height function in terms
     * of y.
     * param values for x and y, to be input once calling h(x, y).
     * returns derivative of the function in terms of y.
     */

    public double hyderivated(double x, double y) {
        double dy = 0.000000000001;
        double derivative = (h(x, y + dy) - h(x, y)) / dy;
        return derivative;
    }
    
}