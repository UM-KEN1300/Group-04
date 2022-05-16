package com.crazyputting3d.Bots;
import com.crazyputting3d.physicsEngine;
import com.crazyputting3d.Objects.StateVector;

/**
 * Bot Abstract class. 
 * author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 *          Gabrijel Radovčić, Elza Strazda
 * version 1.0
 * since   2022-05-11
 */

public abstract class Bot {

    public physicsEngine engine;
    public double x0;                           // initial x position of the ball. 
    public double y0;                           // initial y position of the ball. 
    public double xt;                           // x position of the hole. 
    public double yt;                           // y position of the hole. 
    public final double length = 0.3;
    public final double pi = 3.141159;  
    public double radius;                       // radius of the hole. 
    public double v = 5;
    public int i = 1;

    /**
    * Constructor, sets the undeclared fields with corresponding values by using the engine object.
    * param engine object.
    */

    public Bot(physicsEngine engine) {

        this.engine = engine;
        this.xt = engine.getXt();
        this.yt = engine.getYt();
        this.radius = engine.getrOfHole();
        this.x0 = engine.getX0();
        this.y0 = engine.getY0();
    }

    /**
    * calculateMove() empty, to be implemented by the subclasses. 
    */

    public abstract StateVector calculateMove();

    /**
    * makeMove() initiates calculateMove() and calculates its runtime. 
    */

    public void makeMove(){
        long startTime = System.nanoTime();
        StateVector move = calculateMove();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Run time of algorithm"+getClass()+" (ms): " + duration/1000000);
        double vx = move.getVX();
        double vy = move.getVY();
        engine.setVelocities(vx, vy);
    }
}