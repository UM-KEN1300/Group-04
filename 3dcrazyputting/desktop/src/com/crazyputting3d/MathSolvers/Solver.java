package com.crazyputting3d.MathSolvers;
import  com.crazyputting3d.physicsEngine;
import com.crazyputting3d.InputReader.cheat;
import com.crazyputting3d.Objects.StateVector;

/**
 * Solver Abstract class. 
 * author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 *          Gabrijel Radovčić, Elza Strazda
 * version 1.0
 * since   2022-05-11
 */

public abstract class Solver {
    
    public final double g = 9.81;


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

    public StateVector run(StateVector a, double m) {
        return null;
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
