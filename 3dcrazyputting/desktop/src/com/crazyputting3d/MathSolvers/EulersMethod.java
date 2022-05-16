package com.crazyputting3d.MathSolvers;
import com.crazyputting3d.Objects.StateVector;
import  com.crazyputting3d.physicsEngine;

/**
 * Eulers Method class. 
 * author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 *          Gabrijel Radovčić, Elza Strazda
 * version 1.0
 * since   2022-05-11
 */

public class EulersMethod extends Solver {

    public EulersMethod(physicsEngine engine) {
        super(engine);
    }

    public StateVector run(StateVector a, double m) {
        double x = a.getX(); // x(n+1) = x(n) + h*x(n) derivated
        double y = a.getY(); // y(n+1) = y(n) + h*y(n) derivated
        double vx = a.getVX(); // vx(n+1) = vx(n) + h*vx(n) derivated
        double vy = a.getVY(); // vy(n+1) = vy(n) + h*vy(n) derivated
        a.setX(physicsEngine.h * vx + x);
        a.setY(physicsEngine.h * vy + y);
        double ax = engine.acelerationX(x, y, vx, vy, m);
        double ay = engine.acelerationY(x, y, vx, vy, m);
        a.setVX(vx + physicsEngine.h * ax);
        a.setVY(vy + physicsEngine.h * ay);
        return a;
    }

}