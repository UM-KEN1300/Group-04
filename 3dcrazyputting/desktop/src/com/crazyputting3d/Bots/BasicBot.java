package com.crazyputting3d.Bots;
import java.io.IOException;
import com.crazyputting3d.Engine.physicsEngine;
import com.crazyputting3d.Objects.StateVector;

/**
 * Basic Bot class. 
 * author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 * Gabrijel Radovčić, Elza Strazda
 * version 2.0
 * since   2022-05-11
 */

public class BasicBot extends Bot{

    public double speedX;
    public double speedY;
    public double directionX;
    public double directionY;
    public double slopex;
    public double slopey;

    /**
    * Constructor, inherits the constructor from the Abstract class Bot.
    * param engine object.
    */

    public BasicBot(physicsEngine engine) {
        super(engine);
    }

    /**
    * calculateMove() method shoots in the direction of the hole. 
    * Returns StateVector with initial X and Y direction and speed of the shot aimed directly towards the hole.
    * If the ball doesn't go in the hole, compute the x and y derivative at each step and add it to the 
    * x and y speed. 
    */
    
    public StateVector calculateMove(){
        numberOfIterations++;
        double angle = Math.atan2((yt - y0) , (xt - x0));

        directionX = Math.cos(angle)*length;
        directionY = Math.sin(angle)*length;

        speedX = v*directionX/length;
        speedY = v*directionY/length;

        try {
            physicsEngine enginetest;
            enginetest = new physicsEngine();
            enginetest.setVelocities(speedX, speedX);
            if(engine.isInHole()) {
                slopex=0;
                slopey=0;
            } else {
                slopex = enginetest.getSlopex();
                slopey = enginetest.getSlopey();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
         speedX = speedX+slopex;
         speedY = speedY+slopey;

        StateVector min = new StateVector(x0,y0,speedX,speedY);

        return min;
    }
}