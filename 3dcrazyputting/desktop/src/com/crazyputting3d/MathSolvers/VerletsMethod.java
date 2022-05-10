package com.crazyputting3d.MathSolvers;
import com.crazyputting3d.Objects.StateVector;
import  com.crazyputting3d.physicsEngine;

/**
 * Verlets method class. 
 * author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 *          Gabrijel Radovčić, Elza Strazda
 * version 1.0
 * since   2022-05-11
 */

public class VerletsMethod extends Solver{

    public StateVector run(StateVector a, double m){

        double x0 = a.getX();
        double y0 = a.getY();
        double vx0 = a.getVX();
        double vy0 = a.getVY();
        double ax0 = acelerationX(x0, y0, vx0, vy0, m);
        double ay0 = acelerationY(x0, y0, vx0, vy0, m);
        double x1  = x0 + vx0*physicsEngine.h+1.0/2.0*ax0*physicsEngine.h*physicsEngine.h;
        double y1  = y0 + vy0*physicsEngine.h+1.0/2.0*ay0*physicsEngine.h*physicsEngine.h;
        double vx1 = vx0+physicsEngine.h*ax0;
        double vy1 = vy0+physicsEngine.h*ay0;
        if (terminates(vx0, vy0, m)) {
            a.setX(x1);
            a.setY(y1);
            a.setVX(vx1);
            a.setVY(vy1);
            return a;
        }
        double ax1 = acelerationX(x1, y1, vx1, vy1, m);
        double ay1 = acelerationY(x1, y1, vx1, vy1, m);
        double vx2 = vx0 + 1.0/2.0*(ax0+ax1)*physicsEngine.h;
        double vy2 = vy0 + 1.0/2.0*(ay0+ay1)*physicsEngine.h;
        a.setX(x1);
        a.setY(y1);
        a.setVX(vx2);
        a.setVY(vy2);
        return a;
    }
}