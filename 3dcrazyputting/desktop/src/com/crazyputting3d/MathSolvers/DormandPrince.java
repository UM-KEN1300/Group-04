package com.crazyputting3d.MathSolvers;
import com.crazyputting3d.Objects.StateVector;
import  com.crazyputting3d.physicsEngine;

/**
 * Dormand Prince class. 
 * author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 *          Gabrijel Radovčić, Elza Strazda
 * version 1.0
 * since   2022-05-11
 */

public class DormandPrince extends Solver{

    /**
     *  This is the code which implements the Dormand Prince which is a mathematical solver for solving ODE's.
     *  For more information, go to https://en.wikipedia.org/wiki/Dormand%E2%80%93Prince_method.
     */

    public DormandPrince(physicsEngine engine) {
        super(engine);
    }

    public StateVector DormandPrinceRun(StateVector a, double m,double e) {
        double x0 = a.getX();
        double y0 = a.getY();
        double vx0 = a.getVX();
        double vy0 = a.getVY();
        double ax0 = engine.acelerationX(x0, y0, vx0, vy0, m);
        double ay0 = engine.acelerationY(x0, y0, vx0, vy0, m);
        double x1 = x0 + vx0 * physicsEngine.h;
        double y1 = y0 + vy0 * physicsEngine.h;
        double vx1 = vx0 + (1.0 / 5.0 * ax0) * physicsEngine.h;
        double vy1 = vy0 + (1.0 / 5.0 * ay0) * physicsEngine.h;
        if (engine.terminates(vx0, vy0, m)) {
            a.setX(x1);
            a.setY(y1);
            a.setVX(vx1);
            a.setVY(vy1);
            return a;
        }
        double ax1 = engine.acelerationX(x1, y1, vx1, vy1, m);
        double ay1 = engine.acelerationY(x1, y1, vx1, vy1, m);
        double x2 = x0 + vx1 * physicsEngine.h;
        double y2 = y0 + vy1 * physicsEngine.h;

        double vx2 = vx0 + (3.0 / 40.0 * ax0) * physicsEngine.h + (9.0 / 40.0 * ax1) * physicsEngine.h;
        double vy2 = vy0 + (3.0 / 40.0 * ay0) * physicsEngine.h + (9.0 / 40.0 * ay1) * physicsEngine.h;
        double ax2 = engine.acelerationX(x2, y2, vx2, vy2, m);
        double ay2 = engine.acelerationY(x2, y2, vx2, vy2, m);
        double x3 = x0 + vx2 * physicsEngine.h;
        double y3 = y0 + vy2 * physicsEngine.h;

        double vx3 = vx0 + (44.0 / 45.0 * ax0) * physicsEngine.h - (56.0 / 15.0 * ax1) * physicsEngine.h + (32.0 / 9.0 * ax2) * physicsEngine.h;
        double vy3 = vy0 + (44.0 / 45.0 * ay0) * physicsEngine.h - (56.0 / 15.0 * ay1) * physicsEngine.h + (32.0 / 9.0 * ay2) * physicsEngine.h;
        double ax3 = engine.acelerationX(x3, y3, vx3, vy3, m);
        double ay3 = engine.acelerationY(x3, y3, vx3, vy3, m);
        double x4 = x0 + vx3 * physicsEngine.h;
        double y4 = y0 + vy3 * physicsEngine.h;

        double vx4 = vx0 + (19372.0 / 6561.0 * ax0) * physicsEngine.h - (25360.0 / 2187.0 * ax1 * physicsEngine.h + (64448.0 / 6561.0 * ax2) * physicsEngine.h
                - (212.0 / 729.0 * ax3) * physicsEngine.h);
        double vy4 = vy0 + (19372.0 / 6561.0 * ay0) * physicsEngine.h - (25360.0 / 2187.0 * ay1) * physicsEngine.h + (64448.0 / 6561.0 * ay2) * physicsEngine.h
                - (212.0 / 729.0 * ay3 * physicsEngine.h);
        double ax4 = engine.acelerationX(x4, y4, vx4, vy4, m);
        double ay4 = engine.acelerationY(x4, y4, vx4, vy4, m);
        double x5 = x0 + vx4 * physicsEngine.h;
        double y5 = y0 + vy4 * physicsEngine.h;

        double vx5 = vx0 + (9017.0 / 3168.0 * ax0) * physicsEngine.h - (355.0 / 33.0 * ax1) * physicsEngine.h + (46732.0 / 5247.0 * ax2) * physicsEngine.h
                + (64448.0 / 6561.0 * ax3) * physicsEngine.h - (5103.0 / 18656.0 * ax4) * physicsEngine.h;
        double vy5 = vy0 + (9017.0 / 3168.0 * ay0) * physicsEngine.h - (355.0 / 33.0 * ay1) * physicsEngine.h + (46732.0 / 5247.0 * ay2) * physicsEngine.h
                + (64448.0 / 6561.0 * ay3) * physicsEngine.h - (5103.0 / 18656.0 * ay4) * physicsEngine.h;
        double ax5 = engine.acelerationX(x5, y5, vx4, vy4, m);
        double ay5 = engine.acelerationY(x5, y5, vx4, vy4, m);

        double vx6 = vx0 + (35.0 / 384.0 * ax0) * physicsEngine.h + (500.0 / 1113.0 * ax2) * physicsEngine.h + (125.0 / 192.0 * ax3) * physicsEngine.h
                - (2187.0 / 6784.0 * ax4) * physicsEngine.h + (11.0 / 84.0 * ax5) * physicsEngine.h;

        double exact_next = x0 + (35.0 / 384.0 * vx0) * physicsEngine.h + (500.0 / 1113.0 * vx2) * physicsEngine.h + (125.0 / 192.0 * vx3) * physicsEngine.h
                - (2187.0 / 6784.0 * vx4) * physicsEngine.h + (11.0 / 84.0 * vx5) * physicsEngine.h;
        double average_next = x0 + physicsEngine.h * (1921409.0 * vx0 + 9690880.0 * vx2 + 13122270.0 * vx3 - 5802111.0 * vx4
                + 1902912.0 * vx5 + 534240.0 * vx6) / 21369600.0;
        double s = Math.abs(exact_next - average_next);
        if(s>e*physicsEngine.h){
            double optimal_t = Math.pow((physicsEngine.h * e) / ((2 * s)), 1.0 / 5.0);
            physicsEngine.h = optimal_t*physicsEngine.h;
            return DormandPrinceRun(a, m, e);
        }
        else{
            a.setX(x0 + (35.0 / 384.0 * vx0) * physicsEngine.h + (500.0 / 1113.0 * vx2) * physicsEngine.h + (125.0 / 192.0 * vx3) * physicsEngine.h
                - (2187.0 / 6784.0 * vx4) * physicsEngine.h + (11.0 / 84.0 * vx5) * physicsEngine.h);
            a.setY(y0 + (35.0 / 384.0 * vy0) * physicsEngine.h + (500.0 / 1113.0 * vy2) * physicsEngine.h + (125.0 / 192.0 * vy3) * physicsEngine.h
                - (2187.0 / 6784.0 * vy4) * physicsEngine.h + (11.0 / 84.0 * vy5) * physicsEngine.h);
            a.setVX(vx0 + (35.0 / 384.0 * ax0) * physicsEngine.h + (500.0 / 1113.0 * ax2) * physicsEngine.h + (125.0 / 192.0 * ax3) * physicsEngine.h
                - (2187.0 / 6784.0 * ax4) * physicsEngine.h
                + (11.0 / 84.0 * ax5) * physicsEngine.h);
            a.setVY(vy0 + (35.0 / 384.0 * ay0) * physicsEngine.h + (500.0 / 1113.0 * ay2) * physicsEngine.h + (125.0 / 192.0 * ay3) * physicsEngine.h
                - (2187.0 / 6784.0 * ay4) * physicsEngine.h
                + (11.0 / 84.0 * ay5) * physicsEngine.h);
            physicsEngine.h = Math.max(physicsEngine.h,e*physicsEngine.h);
            return a;
        }
    }

    @Override
    public StateVector run(StateVector v, double m) {

        return DormandPrinceRun(v, m, 0.001);
    }
}