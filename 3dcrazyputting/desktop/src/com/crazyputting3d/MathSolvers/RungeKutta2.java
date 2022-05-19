package com.crazyputting3d.MathSolvers;
import com.crazyputting3d.Objects.StateVector;
import  com.crazyputting3d.Engine.physicsEngine;

/**
 * Runge Kutta 2 class. 
 * author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 *          Gabrijel Radovčić, Elza Strazda
 * version 1.0
 * since   2022-05-11
 */

public class RungeKutta2 extends Solver{
   
    /**
     * This is the class which implements the Runge Kutta 2 method for solving ODE's.
     * For more information: https://en.wikipedia.org/wiki/Runge%E2%80%93Kutta_methods.
     */

    public RungeKutta2(physicsEngine engine) {
        super(engine);
    }

    public StateVector run(StateVector a, double m) {
        double x0 = a.getX();
        double y0 = a.getY();
        double vx0 = a.getVX();
        double vy0 = a.getVY();
        double ax0 = engine.acelerationX(x0, y0, vx0, vy0, m);
        double ay0 = engine.acelerationY(x0, y0, vx0, vy0, m);

        double x1 = x0 + vx0 * physicsEngine.h;
        double y1 = y0 + vy0 * physicsEngine.h;
        double vx1 = vx0 + ax0 * physicsEngine.h;
        double vy1 = vy0 + ay0 * physicsEngine.h;
        double ax1 = engine.acelerationX(x1, y1, vx1, vy1, m);
        double ay1 = engine.acelerationY(x1, y1, vx1, vy1, m);

        a.setX(x0 + physicsEngine.h * (vx0 + vx1)/2);
        a.setY(y0 + physicsEngine.h * (vy0 + vy1)/2);
        a.setVX(vx0 + physicsEngine.h * (ax0 + ax1)/2);
        a.setVY(vy0 + physicsEngine.h * (ay0 + ay1)/2);
        return a;
    }
    
}