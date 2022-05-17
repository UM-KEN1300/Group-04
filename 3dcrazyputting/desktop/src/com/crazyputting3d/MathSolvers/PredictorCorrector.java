package com.crazyputting3d.MathSolvers;
import  com.crazyputting3d.physicsEngine;
import com.crazyputting3d.Objects.AdamsStateVector;
import com.crazyputting3d.Objects.StateVector;

/**
 * Adams Bashforth's class. 
 * author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 *          Gabrijel Radovčić, Elza Strazda
 * version 1.0
 * since   2022-05-11
 */

public class PredictorCorrector extends Solver{
    /**
     * This is the method for the Predictor corrector method. 
     * It implemehts a predictor which is Adams Bashforth
     * It also implements the corrector Adams Moulton
     * For more information go to: https://en.wikipedia.org/wiki/Linear_multistep_method
     */

    AdamsStateVector initialVector;
    public PredictorCorrector(physicsEngine engine) {
        super(engine);
        initialVector = Adams(engine.getTempV(),engine.get_muk());
    }
    public final double g = 9.81;
    private EulersMethod euler = new EulersMethod(engine);
    public AdamsStateVector Adams(StateVector a0, double m){
        Solver rk4 = new RungeKutta4(engine);
        StateVector a1;
        StateVector a2;
        StateVector a3;
        a1 = rk4.run(a0, m);
        a2 = rk4.run(a1, m);
        a3 = rk4.run(a2,m);
        // adams
        return new AdamsStateVector(a0,a1,a2,a3);
    }
    public StateVector run(StateVector a, double m){
        StateVector a0;
        StateVector a1;
        StateVector a2;
        StateVector a3;
        a0 = initialVector.getFirst();
        a1 = initialVector.getSecond();
        a2 = initialVector.getThird();
        a3 = initialVector.getFourth();
        a = euler.run(a,m);
        if(engine.terminates(a.getVX(),a.getVY(),m)){return a;}

        double ax0 = engine.acelerationX(a0.getX(), a0.getY(), a0.getVX(), a0.getVY(), m);
        double ay0 = engine.acelerationY(a0.getX(), a0.getY(), a0.getVX(), a0.getVY(), m);
        double ax1 = engine.acelerationX(a1.getX(), a1.getY(), a1.getVX(), a1.getVY(), m);
        double ay1 = engine.acelerationY(a1.getX(), a1.getY(), a1.getVX(), a1.getVY(), m);
        double ax2 = engine.acelerationX(a2.getX(), a2.getY(), a2.getVX(), a2.getVY(), m);
        double ay2 = engine.acelerationY(a2.getX(), a2.getY(), a2.getVX(), a2.getVY(), m);
        double ax3 = engine.acelerationX(a3.getX(), a3.getY(), a3.getVX(), a3.getVY(), m);
        double ay3 = engine.acelerationY(a3.getX(), a3.getY(), a3.getVX(), a3.getVY(), m);

        double x = a3.getX() + 1/ 24.0 * physicsEngine.h * (55 * a3.getVX() - 59 * a2.getVX() + 37 * a1.getVX() - 9 * a0.getVX());
        double y = a3.getY() + 1/ 24.0 * physicsEngine.h * (55 * a3.getVY() - 59 * a2.getVY() + 37 * a1.getVY() - 9 * a0.getVY());
        double vx = a3.getVX() + 1/ 24.0 * physicsEngine.h * (55 * ax3 - 59 * ax2 + 37 * ax1 - 9 * ax0);
        double vy = a3.getVY() + 1/ 24.0 * physicsEngine.h * (55 * ay3 - 59 * ay2 + 37 * ay1 - 9 * ay0);
        StateVector a4 = new StateVector(x,y,vx,vy);
        a4 = corrector(a0,a1,a2,a3,a4,m);
        return a4;
    }
    public StateVector corrector(StateVector a0, StateVector a1, StateVector a2, StateVector a3 ,StateVector a4, double m){
        double ax0 = engine.acelerationX(a0.getX(), a0.getY(), a0.getVX(), a0.getVY(), m);
        double ay0 = engine.acelerationY(a0.getX(), a0.getY(), a0.getVX(), a0.getVY(), m);
        double ax1 = engine.acelerationX(a1.getX(), a1.getY(), a1.getVX(), a1.getVY(), m);
        double ay1 = engine.acelerationY(a1.getX(), a1.getY(), a1.getVX(), a1.getVY(), m);
        double ax2 = engine.acelerationX(a2.getX(), a2.getY(), a2.getVX(), a2.getVY(), m);
        double ay2 = engine.acelerationY(a2.getX(), a2.getY(), a2.getVX(), a2.getVY(), m);
        double ax3 = engine.acelerationX(a3.getX(), a3.getY(), a3.getVX(), a3.getVY(), m);
        double ay3 = engine.acelerationY(a3.getX(), a3.getY(), a3.getVX(), a3.getVY(), m);
        double ax4 = engine.acelerationX(a4.getX(), a4.getY(), a4.getVX(), a4.getVY(), m);
        double ay4 = engine.acelerationY(a4.getX(), a4.getY(), a4.getVX(), a4.getVY(), m);

        double x = a3.getX() + 1/ 720.0 * physicsEngine.h * (251*a4.getVX() + 646 * a3.getVX() - 264 * a2.getVX() + 106 * a1.getVX() - 19 * a0.getVX());
        double y = a3.getY() + 1/ 720.0 * physicsEngine.h * (251*a4.getVY() + 646 * a3.getVY() - 264 * a2.getVY() + 106 * a1.getVY() - 19 * a0.getVY());
        double vx = a3.getVX() + 1/ 720.0 * physicsEngine.h * (251*ax4 + 646 * ax3 - 264 * ax2 + 106 * ax1 - 19 * ax0);
        double vy = a3.getVY() + 1/ 720.0 * physicsEngine.h * (251*ay4 + 646 * ay3 - 264 * ay2 + 106 * ay1 - 19 * ay0);
        StateVector a5 = new StateVector(x,y,vx,vy);
        initialVector.setFirst(a1);
        initialVector.setSecond(a2);
        initialVector.setThird(a3);
        initialVector.setFourth(a5);
        return a5;
    }
    
}