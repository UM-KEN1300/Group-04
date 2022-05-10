package com.crazyputting3d.MathSolvers;
import com.crazyputting3d.Objects.StateVector;
import  com.crazyputting3d.physicsEngine;

/**
 * Runge Kutta 4 class. 
 * author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 *          Gabrijel Radovčić, Elza Strazda
 * version 1.0
 * since   2022-05-11
 */

public class RungeKutta4 extends Solver{

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

}