package com.crazyputting3d.Bots;
import java.io.IOException;
import com.crazyputting3d.physicsEngine;
import com.crazyputting3d.Objects.StateVector;

/**
 * Basic Bot class. 
 * author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 *          Gabrijel Radovčić, Elza Strazda
 * version 1.0
 * since   2022-05-11
 */

public class BasicBot extends Bot{
    public double speedX;
    public double speedY;
    public double directionX;
    public double directionY;
    public double slopex;
    public double slopey;
    public BasicBot(physicsEngine engine) {
        super(engine);
    }

    public StateVector calculateMove(){
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

        System.out.println("slopex:"+slopex);
        System.out.println("slopey:"+slopey);

        return min;
    }
}