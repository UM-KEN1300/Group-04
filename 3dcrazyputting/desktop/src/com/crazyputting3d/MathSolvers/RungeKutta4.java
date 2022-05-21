package com.crazyputting3d.MathSolvers;
import com.crazyputting3d.Objects.StateVector;
import  com.crazyputting3d.Engine.physicsEngine;

/**
 * Runge Kutta 4 class. 
 * author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 * Gabrijel Radovčić, Elza Strazda
 * version 2.0
 * since   2022-05-11
 */

public class RungeKutta4 extends Solver{

    /**
     * This is the class which implements the Runge Kutta 4 method for solving ODE's.
     * For more information: https://en.wikipedia.org/wiki/Runge%E2%80%93Kutta_methods.
     */
    
    public RungeKutta4(physicsEngine engine) {
        super(engine);
    }

    public StateVector run(StateVector a, double m) {

        double x0 = a.getX();
        double y0 = a.getY();
        double vx0 = a.getVX();
        double vy0 = a.getVY();
        double ax0 = engine.acelerationX(x0, y0, vx0, vy0, m);
        double ay0 = engine.acelerationY(x0, y0, vx0, vy0, m);

        
        double x1 = x0 + vx0 * physicsEngine.h / 2.0;
        double y1 = y0 + vy0 * physicsEngine.h / 2.0;
        double vx1 = vx0 + ax0 * physicsEngine.h / 2.0;
        double vy1 = vy0 + ay0 * physicsEngine.h / 2.0;
        double ax1 = engine.acelerationX(x1, y1, vx1, vy1, m);
        double ay1 = engine.acelerationY(x1, y1, vx1, vy1, m);

        
        double x2 = x0 + vx1 * physicsEngine.h / 2.0;
        double y2 = y0 + vy1 * physicsEngine.h / 2.0;
        double vx2 = vx0 + ax1 * physicsEngine.h / 2.0;
        double vy2 = vy0 + ay1 * physicsEngine.h / 2.0;
        double ax2 = engine.acelerationX(x2, y2, vx2, vy2, m);
        double ay2 = engine.acelerationY(x2, y2, vx2, vy2, m);

        
        double x3 = x0 + 1.0*vx2 * physicsEngine.h;
        double y3 = y0 + 1.0*vy2 * physicsEngine.h;
        double vx3 = vx0 + 1.0*ax2 * physicsEngine.h;
        double vy3 = vy0 + 1.0*ay2 * physicsEngine.h;
        double ax3 = engine.acelerationX(x3, y3, vx3, vy3, m);
        double ay3 = engine.acelerationY(x3, y3, vx3, vy3, m);

        a.setX(x0 + physicsEngine.h * (vx0 + 2.0 * vx1 + 2.0 * vx2 + vx3)/6.0);
        a.setY(y0 + physicsEngine.h * (vy0 + 2.0 * vy1 + 2.0 * vy2 + vy3)/6.0);
        a.setVX(vx0 + physicsEngine.h * (ax0 + 2.0 * ax1 + 2.0 * ax2 + ax3)/6.0);
        a.setVY(vy0 + physicsEngine.h * (ay0 + 2.0 * ay1 + 2.0 * ay2 + ay3)/6.0);

        return a;
    }

}