package com.crazyputting3d.Bots;
import com.crazyputting3d.Objects.StateVector;
import com.crazyputting3d.physicsEngine;

/**
 * Newton Raphson Bot class. 
 * author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 *          Gabrijel Radovčić, Elza Strazda
 * version 1.0
 * since   2022-05-11
 */

public class NewtonRaphsonBot extends Bot{

    

    public NewtonRaphsonBot(physicsEngine engine) {
        super(engine);
    }

    public StateVector calculateMove() {
        double distance = Math.hypot(x0-xt, y0-yt);
        double accuracy = 0.000001;
        double minVx = Double.MAX_VALUE;
        double minVy = Double.MAX_VALUE;
        StateVector min = new StateVector(x0, y0, minVx, minVy);
        double dx = 0.0000000001;
        double angle = Math.acos(Math.abs(xt - x0) / distance);
        double originalangle = angle;
        double vx = Math.cos(angle) * v;
        double vy = Math.sin(angle) * v;
        engine.setVelocitiesForBot(x0, y0, vx, vy);
        double tempEuclidianDistance = engine.get_closestEuclideanDistance();
        double vx2 = Math.cos(angle+dx) * v;
        double vy2 = Math.sin(angle+dx) * v;
        engine.setVelocitiesForBot(x0, y0, vx2, vy2);
        double tempEuclidianDistance2 = engine.get_closestEuclideanDistance();
        double angle_step = 0;
        double vx3 = Math.cos(angle-dx) * v;
        double vy3 = Math.sin(angle-dx) * v;
        engine.setVelocitiesForBot(x0, y0, vx3, vy3);
        double tempEuclidianDistance3 = engine.get_closestEuclideanDistance();

        while (tempEuclidianDistance >= radius) {
            angle_step = ((tempEuclidianDistance-tempEuclidianDistance2)/dx)/((tempEuclidianDistance3-2*tempEuclidianDistance+tempEuclidianDistance2)/(dx*dx));
            angle -= angle_step;
            if(angle_step<accuracy){
                v -= 0.1;
                angle = originalangle;
                if(v<=0){
                    angle = Math.random()*5;
                    System.out.println(angle);
                    v=5;
                }
            }
            vx = Math.cos(angle) * v;
            vy = Math.sin(angle) * v;
            engine.setVelocitiesForBot(x0, y0, vx, vy);
            tempEuclidianDistance = engine.get_closestEuclideanDistance();
            vx2 = Math.cos(angle+dx) * v;
            vy2 = Math.sin(angle+dx) * v;
            engine.setVelocitiesForBot(x0, y0, vx2, vy2);
            tempEuclidianDistance2 = engine.get_closestEuclideanDistance();
        }
        min = new StateVector(x0, y0, Math.cos(angle) * v, Math.sin(angle) * v);

        return min;
    }
}
