package com.crazyputting3d.Bots;

import java.io.IOException;

import com.crazyputting3d.physicsEngine;
import com.crazyputting3d.InputReader.cheat;
import com.crazyputting3d.Objects.StateVector;

public class BasicBot {
    private physicsEngine engine;
    private double x0;
    private double y0;
    private double xt;
    private double yt;
    private double speed;
    private double directionX;
    private double directionY;
    final double length = 0.3;
    private double speedX;
    private double speedY;
    private double slopex;
    private double slopey;

    public BasicBot(physicsEngine engine) {
        this.engine = engine;
        this.x0 = engine.getX0();
        this.y0 = engine.getY0();
        this.xt = engine.getXt();
        this.yt = engine.getYt();
        this.speed = 5;
    }

    public StateVector play(){
        double angle = Math.atan2((yt - y0) , (xt - x0));

        directionX = Math.cos(angle)*length;
        directionY = Math.sin(angle)*length;

        speedX = speed*directionX/length;
        speedY = speed*directionY/length;

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

    public double h(double x, double y) {
        cheat cheat = new cheat();
        return cheat.getHeightFunction(x, y);
    }


    public double hxderivated(double x, double y) {
        double dx = 0.000000000001;
        double derivative = (h(x + dx, y) - h(x, y)) / dx;
        return derivative;
    }

    public double hyderivated(double x, double y) {
        double dy = 0.000000000001;
        double derivative = (h(x, y + dy) - h(x, y)) / dy;
        return derivative;
    }
    
    public void makeMove(){
        //long startTime = System.nanoTime();
        StateVector move = play();
        //long endTime = System.nanoTime();
        //long duration = (endTime - startTime);
        //System.out.println("Run time of the Basic Rule bot algorithm (ms): " + duration/1000000);
        double vx = move.getVX();
        double vy = move.getVY();
        engine.setVelocities(vx, vy);
     }
}
