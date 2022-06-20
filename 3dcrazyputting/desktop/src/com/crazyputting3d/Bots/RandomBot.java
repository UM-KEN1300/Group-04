package com.crazyputting3d.Bots;
import java.util.concurrent.ThreadLocalRandom;
import com.crazyputting3d.Objects.StateVector;
import com.crazyputting3d.Engine.physicsEngine;

/**
 * Random Bot class. 
 * author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 * Gabrijel Radovčić, Elza Strazda
 * version 3.0
 * since   2022-05-11
 */

public class RandomBot extends Bot{

    private double speedX;
    private double speedY;
    private double directionX;
    private double directionY;
    private boolean flag;
    private int counter;

    /**
    * Constructor, inherits the constructor from the Abstract class Bot.
    * param engine object.
    */

    public RandomBot(physicsEngine engine) {
        super(engine);
        this.flag=true;
    }

    /**
    * randomDouble() method generates a random double between min and max. 
    * param min - lower boundary, max - higher boundary for the generation of the random double.
    * returns a random double. 
    */

    public static double randomDouble(double min, double max) {
        return (ThreadLocalRandom.current().nextDouble() * (max - min)) + min;
    }

    /**
    * setRandSpeed() method updates the initial speed of each simulation by generating a random Double value between 3 and 5. Can be set to 5 permanently for faster but not always possible solutions. 
    */

    public void setRandSpeed() {
        v = randomDouble(3, 5);
    }

    /**
    * setRandDirection() method generates a randomly calculated angle by using Math.random()*Math.PI*2 and calculates + updates both X and Y direction fields with the new angle. 
    */

    public void setRandDirection() {
        double angle = Math.random()*Math.PI*2;
        directionX = Math.cos(angle)*length;
        directionY = Math.sin(angle)*length;
    }

    /**
    * calculateMove() method uses randomly generated directions by the method setRandDirection() until the simulation hits the hole. 
    * returns StateVector with the optimal initial direction and speed. Optimal in this scenario, is when the ball goes inside the hole. 
    * When the number of iterations reaches 5000, the computations stop and the method returns the shot which was the closest.
    */

    public StateVector calculateMove() {
        flag = true;
        counter = 0;
        double euklidianDistance = Double.MAX_VALUE;
        double minVx = Double.MAX_VALUE;
        double minVy = Double.MAX_VALUE;
        StateVector min = new StateVector(x0,y0,minVx,minVy);
        while(flag) {
            numberOfIterations++;
            setRandDirection();
            setRandSpeed();
            speedX = v*directionX/length;
            speedY = v*directionY/length;
            StateVector temp = engine.setVelocitiesForBot(x0, y0, speedX, speedY);
            double tempEuclidianDistance = Math.sqrt(Math.pow(temp.getX()-xt, 2)+Math.pow(temp.getY()-yt, 2));
            if(tempEuclidianDistance<radius){
                min.setVX(speedX);
                min.setVY(speedY);
                vx0 = speedX;
                vy0 = speedY;
                flag=false;
                return min;
            }
            if(tempEuclidianDistance<euklidianDistance){
                min = temp;
                min.setVX(speedX);
                min.setVY(speedY);
                euklidianDistance = tempEuclidianDistance;
            }
            counter++;
            if(counter>=5000) {
                flag=false;
                vx0 = speedX;
                vy0 = speedY;
                return min;
            }
        }
        return min;
    }
}