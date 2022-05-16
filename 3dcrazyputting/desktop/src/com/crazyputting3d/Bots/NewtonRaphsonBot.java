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
        System.out.println("newton bot");
    }

    public StateVector calculateMove() {
        double distance = Math.hypot(x0-xt, y0-yt);
        double accuracy = 0.0000001;
        double dx = 0.01;
        double angle = Math.acos(Math.abs(xt - x0) / distance);
        double vx = Math.cos(angle) * v;
        double vy = Math.sin(angle) * v;
        double minVx = vx;
        double minVy = vy;
        StateVector min = new StateVector(x0, y0, minVx, minVy);
        engine.setVelocitiesForBot(x0, y0, vx, vy);
        double tempEuclidianDistance = engine.get_closestEuclideanDistance();
        double closestDistance = tempEuclidianDistance;
        double vx2 = Math.cos(angle+dx) * v;
        double vy2 = Math.sin(angle+dx) * v;
        engine.setVelocitiesForBot(x0, y0, vx2, vy2);
        double tempEuclidianDistance2 = engine.get_closestEuclideanDistance();
        double angle_step = 0;
        double vx3 = Math.cos(angle-dx) * v;
        double vy3 = Math.sin(angle-dx) * v;
        engine.setVelocitiesForBot(x0, y0, vx3, vy3);
        double tempEuclidianDistance3 = engine.get_closestEuclideanDistance();

        while (tempEuclidianDistance >= radius&&tempEuclidianDistance2 >= radius && tempEuclidianDistance3>=radius) {
            angle_step = dx-((tempEuclidianDistance-tempEuclidianDistance3)*dx)/((tempEuclidianDistance2-2*tempEuclidianDistance+tempEuclidianDistance3));
            angle -= angle_step;
            if(angle_step<accuracy){
                v -= 0.01;
                if(v<=0){
                    break;
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
            vx3 = Math.cos(angle-dx) * v;
            vy3 = Math.sin(angle-dx) * v;
            engine.setVelocitiesForBot(x0, y0, vx3, vy3);
            tempEuclidianDistance3 = engine.get_closestEuclideanDistance();
            if(tempEuclidianDistance<closestDistance){
                minVx = vx;
                minVy = vy;
                closestDistance=tempEuclidianDistance;
            }
            if(tempEuclidianDistance2<closestDistance){
                minVx = vx2;
                minVy = vy2;
                closestDistance=tempEuclidianDistance2;
            }
            if(tempEuclidianDistance3<closestDistance){
                minVx = vx3;
                minVy = vy3;
                closestDistance=tempEuclidianDistance3;
            }
        }
        min = new StateVector(x0, y0, minVx, minVy);

        return min;
    }
}
