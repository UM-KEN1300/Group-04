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
    public double speedX;
    public double speedY;
    public double directionX;
    public double directionY;
    public boolean flag;
    public RandomBot(physicsEngine engine) {
        super(engine);
        this.flag=true;
        System.out.println("random bot");
    }

    public double h(double x, double y){
        cheat cheat = new cheat();
        return cheat.getHeightFunction(x, y);
    }

    public static double randomDouble(double min, double max) {
        return (ThreadLocalRandom.current().nextDouble() * (max - min)) + min;
    }

    public void setRandSpeed() {
        v =5;//= randomDouble(0.1, 5);
    }

    public void setRandDirection() {
        double angle = Math.random()*Math.PI*2;
        directionX = Math.cos(angle)*length;
        directionY = Math.sin(angle)*length;
    }

    public StateVector calculateMove() {
        double euklidianDistance = Double.MAX_VALUE;
        double minVx = Double.MAX_VALUE;
        double minVy = Double.MAX_VALUE;
        StateVector min = new StateVector(x0,y0,minVx,minVy);
        while(flag) {
            setRandDirection();
            setRandSpeed();
            speedX = v*directionX/length;
            speedY = v*directionY/length;
            StateVector temp = engine.setVelocitiesForBot(x0, y0, speedX, speedY);
            double tempEuclidianDistance = Math.sqrt(Math.pow(temp.getX()-xt, 2)+Math.pow(temp.getY()-yt, 2));
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
}
