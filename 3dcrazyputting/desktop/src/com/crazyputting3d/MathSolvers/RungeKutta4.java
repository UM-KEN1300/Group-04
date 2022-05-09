package com.crazyputting3d.MathSolvers;

import com.crazyputting3d.Objects.StateVector;
import  com.crazyputting3d.physicsEngine;
import com.crazyputting3d.InputReader.cheat;

public class RungeKutta4 {

    public final double g = 9.81;

    public StateVector run(StateVector a, double m) {
        double x0 = a.getX();
        double y0 = a.getY();
        double vx0 = a.getVX();
        double vy0 = a.getVY();
        double ax1 = acelerationX(x0, y0, vx0, vy0, m);
        double ay1 = acelerationY(x0, y0, vx0, vy0, m);
        double vx1 = vx0 + ax1 * physicsEngine.h / 2;
        double vy1 = vy0 + ay1 * physicsEngine.h / 2;
        double x1 = x0 + vx0 * physicsEngine.h / 2;
        double y1 = y0 + vx0 * physicsEngine.h / 2;

        double ax2 = acelerationX(x1, y1, vx1, vy1, m);
        double ay2 = acelerationY(x1, y1, vx1, vy1, m);
        double vx2 = vx0 + ax2 * physicsEngine.h / 2;
        double vy2 = vy0 + ay2 * physicsEngine.h / 2;
        double x2 = x0 + vx1 * physicsEngine.h / 2;
        double y2 = y0 + vx1 * physicsEngine.h / 2;
        if (terminates(vx0, vy0, m)) {
            a.setX(x2);
            a.setY(y2);
            a.setVX(vx2);
            a.setVY(vy2);
            return a;
        }

        double ax3 = acelerationX(x2, y2, vx2, vy2, m);
        double ay3 = acelerationY(x2, y2, vx2, vy2, m);
        double vx3 = vx0 + ax3 * physicsEngine.h;
        double vy3 = vy0 + ay3 * physicsEngine.h;
        double x3 = x0 + vx2 * physicsEngine.h;
        double y3 = y0 + vx2 * physicsEngine.h;

        double ax4 = acelerationX(x3, y3, vx3, vy3, m);
        double ay4 = acelerationY(x3, y3, vx3, vy3, m);

        a.setX(x0 + physicsEngine.h * 1.0 / 6.0 * (vx0 + 2 * vx1 + 2 * vx2 + vx3));
        a.setY(y0 + physicsEngine.h * 1.0 / 6.0 * (vy0 + 2 * vy1 + 2 * vy2 + vy3));
        a.setVX(vx0 + physicsEngine.h * 1.0 / 6.0 * (ax1 + 2 * ax2 + 2 * ax3 + ax4));
        a.setVY(vy0 + physicsEngine.h * 1.0 / 6.0 * (ay1 + 2 * ay2 + 2 * ay3 + ay4));

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
