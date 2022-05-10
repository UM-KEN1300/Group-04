package com.crazyputting3d.Bots;
import java.util.concurrent.ThreadLocalRandom;
import com.crazyputting3d.Objects.StateVector;
import com.crazyputting3d.physicsEngine;
import com.crazyputting3d.InputReader.cheat;

/**
 * Random Bot class. 
 * author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 *          Gabrijel Radovčić, Elza Strazda
 * version 1.0
 * since   2022-05-11
 */

public class RandomBot extends Bot{

    public RandomBot(physicsEngine engine) {
        this.engine = engine;
        this.holeX = engine.getXt();
        this.holeY = engine.getYt();
        this.ballX = engine.getX0();
        this.ballY = engine.getY0();
        this.radius = engine.getrOfHole();
        this.speed = 0;
        this.directionX=0;
        this.directionY=0;
        this.flag=true;
    }

    public double h(double x, double y){
        cheat cheat = new cheat();
        return cheat.getHeightFunction(x, y);
    }

    public static double randomDouble(double min, double max) {
        return (ThreadLocalRandom.current().nextDouble() * (max - min)) + min;
    }

    public void setRandSpeed() {
        //speed = randomDouble(3, 5);
        speed=5;
    }

    public void setRandDirection() {
        double angle = Math.random()*Math.PI*2;
        directionX = Math.cos(angle)*length;
        directionY = Math.sin(angle)*length;
    }

    public StateVector trajectory() {
        double euklidianDistance = Double.MAX_VALUE;
        double minVx = Double.MAX_VALUE;
        double minVy = Double.MAX_VALUE;
        StateVector min = new StateVector(ballX,ballY,minVx,minVy);
        while(flag) {
            setRandDirection();
            setRandSpeed();
            speedX = speed*directionX/length;
            speedY = speed*directionY/length;
            StateVector temp = engine.setVelocitiesForBot(ballX, ballY, speedX, speedY);
            double tempEuclidianDistance = Math.sqrt(Math.pow(temp.getX()-holeX, 2)+Math.pow(temp.getY()-holeY, 2));
            if(tempEuclidianDistance<radius){
                min.setVX(speedX);
                min.setVY(speedY);
                flag=false;
                return min;
            }
            if(tempEuclidianDistance<euklidianDistance){
                min = temp;
                euklidianDistance = tempEuclidianDistance;
            }
        }
        return min;
    }

    public void makeMove(){
        long startTime = System.nanoTime();
        StateVector move = trajectory();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Run time of the random bot algorithm (ms): " + duration/1000000);
        double vx = move.getVX();
        double vy = move.getVY();
        engine.setVelocities(vx, vy);
    }
}
