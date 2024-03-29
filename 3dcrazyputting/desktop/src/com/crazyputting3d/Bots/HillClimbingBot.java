package com.crazyputting3d.Bots;
import com.crazyputting3d.Objects.StateVector;
import com.crazyputting3d.Engine.physicsEngine;

/**
 * Hill Climbing Bot class. 
 * author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 * Gabrijel Radovčić, Elza Strazda
 * version 3.0
 * since   2022-05-11
 */

public class HillClimbingBot extends Bot {

     // initial angle-step used to determine the direction with best euclidean distance by comparing between the (current angle + angle-step) and (current angle - angle-step). 
    public double angle_step = pi / 180; 

    /**
    * Constructor, inherits the constructor from the Abstract class Bot.
    * param engine object.
    */

    public HillClimbingBot(physicsEngine engine) {
        super(engine);
    }

    /**
    * calculateMove() method uses an Hill climbing ideology to calculate the best possible shot. 
    * returns StateVector with the optimal initial direction and speed. Optimal in this scenario, is either the ball in the hole, or as close to the hole as the algorithm could possibly calculate. 
    */

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
            numberOfIterations++;
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
            if(!left&!right&Math.sin(angle_step)*Math.hypot(x0-xt, y0-yt)<engine.getrOfHole()/Math.pow(10,100*(0.15-radius))){
                angle_step = pi/180;
                v -= 0.01;
                if(v<=0){
                    angle_step = 2*i*pi/180;
                    i=2*i;
                    v=5;
                    if(angle_step>=pi){
                        vx0 = vx;
                        vy0 = vy;
                        return new StateVector(x0, y0, vx, vy);
                    }
                }
            }

        }
        vx0 =  Math.cos(angle) * v;
        vy0 =  Math.sin(angle) * v;
        min = new StateVector(x0, y0, Math.cos(angle) * v, Math.sin(angle) * v);
        return min;
    }
}