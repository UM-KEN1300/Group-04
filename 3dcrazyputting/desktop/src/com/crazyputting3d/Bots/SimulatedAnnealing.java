package com.crazyputting3d.Bots;
import com.crazyputting3d.Objects.StateVector;
import com.crazyputting3d.Engine.physicsEngine;

import java.util.concurrent.ThreadLocalRandom;

public class SimulatedAnnealing extends Bot{

    // initial angle-step used to determine the direction with best euclidean distance by comparing between the (current angle + angle-step) and (current angle - angle-step).
    public double initial_angle_step = pi/180;
    public double angle_step = initial_angle_step;


    /**
     * Constructor, inherits the constructor from the Abstract class Bot.
     * param engine object.
     */

    public SimulatedAnnealing(physicsEngine engine) {
        super(engine);
    }

    public static double randomDouble(double min, double max) {
        return (ThreadLocalRandom.current().nextDouble() * (max - min)) + min;
    }

    /**
     * calculateMove() method uses a Simulated Annealing ideology to calculate the best possible shot.
     * returns StateVector with the optimal initial direction and speed. Optimal in this scenario, is either the ball in the hole, or as close to the hole as the algorithm could possibly calculate.
     */

    public StateVector calculateMove() {

        StateVector min;
        double angle = Math.acos(Math.abs(xt - x0) / Math.sqrt(Math.pow(x0 - xt, 2) + Math.pow(y0 - yt, 2)));
        double vx = Math.cos(angle) * v;
        double vy = Math.sin(angle) * v;
        engine.setVelocitiesForBot(x0, y0, vx, vy);
        double tempEuclideanDistance = engine.get_closestEuclideanDistance();
        double tempEuclideanDistanceRight;
        double tempEuclideanDistanceLeft;
        boolean left;
        boolean right;
        double counter = 0;

        //SA
        double energyRight;
        double energyLeft;
        double random;
        double temperature = 1000;

        while (tempEuclideanDistance >= radius) {
            numberOfIterations++;
            tempEuclideanDistance = engine.get_closestEuclideanDistance();
            engine.setVelocitiesForBot(x0, y0, Math.cos(angle + angle_step) * v,
                    Math.sin(angle + angle_step) * v);
            tempEuclideanDistanceRight = engine.get_closestEuclideanDistance();
            engine.setVelocitiesForBot(x0, y0, Math.cos(angle - angle_step) * v,
                    Math.sin(angle - angle_step) * v);
            tempEuclideanDistanceLeft = engine.get_closestEuclideanDistance();

            //SA
            random = randomDouble(0, 1);
            energyRight = Math.exp((-Math.abs(tempEuclideanDistance-tempEuclideanDistanceRight)/(temperature)));
            energyLeft = Math.exp((-Math.abs(tempEuclideanDistance-tempEuclideanDistanceLeft)/(temperature)));

            if(tempEuclideanDistance > tempEuclideanDistanceRight || tempEuclideanDistance > tempEuclideanDistanceLeft){
                if (tempEuclideanDistanceRight < tempEuclideanDistanceLeft){
                    right = true;
                    left = false;
                    if(energyLeft >= random){
                        right = false;
                        left = true;
                    }
                } else {
                    right = false;
                    left = true;
                    if(energyRight >= random){
                        right = true;
                        left = false;
                    }
                }

            } else {
                right = false;
                left = false;
                angle_step = angle_step/2;
            }



            if(right){
                engine.setVelocitiesForBot(x0, y0, Math.cos(angle + angle_step) * v, Math.sin(angle + angle_step) * v);
                tempEuclideanDistance = tempEuclideanDistanceRight;
                angle += angle_step;
                vx = Math.cos(angle) * v;
                vy = Math.sin(angle) * v;
            }
            else if(left){
                engine.setVelocitiesForBot(x0, y0, Math.cos(angle - angle_step) * v, Math.sin(angle - angle_step) * v);
                tempEuclideanDistance = tempEuclideanDistanceLeft;
                angle -= angle_step;
                vx = Math.cos(angle) * v;
                vy = Math.sin(angle) * v;
            }

            if(!left&!right&Math.sin(angle_step)*Math.hypot(x0-xt, y0-yt)<engine.getrOfHole()/Math.pow(10,100*(0.15-radius))){
                angle_step = pi/180;
                v -= 0.01;
                if(v<=0 && counter == 5){
                    System.out.println("returned because v = 0");
                    return new StateVector(x0, y0, vx, vy);
                }else if(v<=0) {
                    v = 5;
                    System.out.println("changed ");
                    angle_step = initial_angle_step - initial_angle_step/5;
                    initial_angle_step = initial_angle_step - initial_angle_step/5;
                    angle = Math.acos(Math.abs(xt - x0) / Math.sqrt(Math.pow(x0 - xt, 2) + Math.pow(y0 - yt, 2)));
                    vx = Math.cos(angle) * v;
                    vy = Math.sin(angle) * v;
                    engine.setVelocitiesForBot(x0, y0, vx, vy);
                    temperature = 100;
                    counter++;
                }
            }
            temperature = temperature*0.99;
        }

        min = new StateVector(x0, y0, Math.cos(angle) * v, Math.sin(angle) * v);
        System.out.println("Returned at the end");
        System.out.println(numberOfIterations + " Iterations");
        return min;
    }
}
