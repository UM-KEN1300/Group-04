package com.crazyputting3d.MathSolvers;
import com.crazyputting3d.Objects.StateVector;
import  com.crazyputting3d.Engine.physicsEngine;

/**
 * Verlets method class. 
 * author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 * Gabrijel Radovčić, Elza Strazda
 * version 2.0
 * since   2022-05-11
 */

public class VerletsMethod extends Solver{

    /**
     * This is the class which implements the Verlets method for solving ODE's.
     * For more information: https://en.wikipedia.org/wiki/Runge%E2%80%93Kutta_methods.
     */

    public VerletsMethod(physicsEngine engine) {
        super(engine);
    }

    public StateVector run(StateVector a, double m){

        double x0 = a.getX();
        double y0 = a.getY();
        double vx0 = a.getVX();
        double vy0 = a.getVY();
        double ax0 = engine.acelerationX(x0, y0, vx0, vy0, m);
        double ay0 = engine.acelerationY(x0, y0, vx0, vy0, m);

        double x1  = x0 + vx0*physicsEngine.h + ax0*physicsEngine.h*physicsEngine.h/2;
        double y1  = y0 + vy0*physicsEngine.h + ay0*physicsEngine.h*physicsEngine.h/2;
        double vx1 = vx0+physicsEngine.h*ax0;
        double vy1 = vy0+physicsEngine.h*ay0;
        double ax1 = engine.acelerationX(x1, y1, vx1, vy1, m);
        double ay1 = engine.acelerationY(x1, y1, vx1, vy1, m);

        double vx2 = vx0 + (ax0+ax1)*physicsEngine.h/2;
        double vy2 = vy0 + (ay0+ay1)*physicsEngine.h/2;

        a.setX(x1);
        a.setY(y1);
        a.setVX(vx2);
        a.setVY(vy2);
        return a;
    }
}