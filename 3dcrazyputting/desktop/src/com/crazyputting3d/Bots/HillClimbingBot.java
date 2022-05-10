package com.crazyputting3d.Bots;
import com.crazyputting3d.Objects.StateVector;
import com.crazyputting3d.physicsEngine;

/**
 * Hill Climbing Bot class. 
 * author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 *          Gabrijel Radovčić, Elza Strazda
 * version 1.0
 * since   2022-05-11
 */

public class HillClimbingBot extends Bot {
    public double angle_step = pi / 180;

    public HillClimbingBot(physicsEngine engine) {
        super(engine);
    }

    public StateVector calculateMove() {

        double minVx = Double.MAX_VALUE;
        double minVy = Double.MAX_VALUE;
        StateVector min = new StateVector(x0, y0, minVx, minVy);

        double angle = Math.acos(Math.abs(xt - x0) / Math.sqrt(Math.pow(x0 - xt, 2) + Math.pow(y0 - yt, 2)));
        double vx = Math.cos(angle) * v;
        double vy = Math.sin(angle) * v;
        engine.setVelocitiesForBot(x0, y0, vx, vy);
        double tempEuclidianDistance = engine.get_closestEuclideanDistance();
        int counter = 0;
        double tempEuclidianDistanceRight = 0;
        double tempEuclidianDistanceLeft = 0;
        boolean left = false;
        boolean right = false;

        while (tempEuclidianDistance >= radius) {
            if (counter == 0) {
                engine.setVelocitiesForBot(x0, y0, Math.cos(angle + angle_step) * v,
                        Math.sin(angle + angle_step) * v);
                tempEuclidianDistanceRight = engine.get_closestEuclideanDistance();
                engine.setVelocitiesForBot(x0, y0, Math.cos(angle - angle_step) * v,
                        Math.sin(angle - angle_step) * v);
                tempEuclidianDistanceLeft = engine.get_closestEuclideanDistance();
                counter++;

                if (tempEuclidianDistance > tempEuclidianDistanceRight) {
                    right = true;
                }
                if (tempEuclidianDistance > tempEuclidianDistanceLeft) {
                    left = true;
                }
                if (right && left) {
                    if (tempEuclidianDistanceRight > tempEuclidianDistanceLeft) {
                        right = false;
                    } else {
                        left = false;
                    }
                }
            }
            if(right){
                engine.setVelocitiesForBot(x0, y0, Math.cos(angle + angle_step) * v,
                        Math.sin(angle + angle_step) * v);
                tempEuclidianDistanceRight = engine.get_closestEuclideanDistance();
                if(tempEuclidianDistance>tempEuclidianDistanceRight){
                    tempEuclidianDistance = tempEuclidianDistanceRight;
                    angle += angle_step;
                    vx = Math.cos(angle) * v;
                    vy = Math.sin(angle) * v;
                }
                else{
                    right = false;
                    angle_step = angle_step / 2;
                    counter = 0;
                }
            }
            else if(left){
                engine.setVelocitiesForBot(x0, y0, Math.cos(angle - angle_step) * v,
                        Math.sin(angle - angle_step) * v);
                tempEuclidianDistanceLeft = engine.get_closestEuclideanDistance();
                if(tempEuclidianDistance>tempEuclidianDistanceLeft){
                    tempEuclidianDistance = tempEuclidianDistanceLeft;
                    angle -= angle_step;
                    vx = Math.cos(angle) * v;
                    vy = Math.sin(angle) * v;
                    
                }
                else{
                    left = false;
                    angle_step = angle_step / 2;
                    counter = 0;
                }
            }
            else{
                angle_step = angle_step / 2;
                counter = 0;
            }
            if(!left&!right&Math.sin(angle_step)*Math.hypot(x0-xt, y0-yt)<engine.getrOfHole()){
                angle_step = pi/180;
                v -= 0.1;
                if(v<=0){
                    angle_step = 2*i*pi/180;
                    i=2*i;
                    v=5;
                    if(angle_step>=pi){
                        return new StateVector(x0, y0, vx, vy);
                    }
                }
            }

        }
        min = new StateVector(x0, y0, Math.cos(angle) * v, Math.sin(angle) * v);

        return min;
    }
}
