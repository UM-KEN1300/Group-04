package com.crazyputting3d.Bots;
import com.crazyputting3d.Engine.physicsEngine;
import com.crazyputting3d.Objects.StateVector;

/**
 * Bot Abstract class. 
 * author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 *          Gabrijel Radovčić, Elza Strazda
 * version 1.0
 * since   2022-05-11
 */

public abstract class Bot {

    protected physicsEngine engine;
    protected double x0;                           // initial x position of the ball. 
    protected double y0;                           // initial y position of the ball. 
    protected double xt;                           // x position of the hole. 
    protected double yt;                           // y position of the hole. 
    protected final double length = 0.3;
    protected final double pi = 3.141159;  
    protected double radius;                       // radius of the hole. 
    protected double v = 5;
    protected int i = 1;
    protected int numberOfIterations;
    protected double runtime;


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
        runtime = duration/1000000;
        double vx = move.getVX();
        double vy = move.getVY();
        engine.setVelocities(vx, vy);
        x0 = engine.getX0();
        y0 = engine.getY0();
    }
    /**
     * getNumberOfIterations() return number of times each algorithm calculated their move before it came to hole in one
     */
    public int getNumberOfIteretions(){
        return numberOfIterations;
    }
    public double getRuntime(){
        return runtime;
    }
}