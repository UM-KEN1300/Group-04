package com.crazyputting3d.Bots;
import com.crazyputting3d.Engine.physicsEngine;
import com.crazyputting3d.Objects.StateVector;

/**
 * Bot Abstract class. 
 * author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 * Gabrijel Radovčić, Elza Strazda
 * version 3.0
 * since   2022-05-11
 */

public abstract class Bot {

    //Set instance variables which can be used by the subclasses

    protected physicsEngine engine;
    protected double x0;
    protected double y0;
    protected double xt;
    protected double yt;                         
    protected final double length = 0.3;
    protected final double pi = 3.141159;  
    protected double radius;                     
    protected double v = 5;
    protected int i = 1;
    protected int numberOfIterations;
    protected double runtime;
    protected double vx0;
    protected double vy0;


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
        runtime += duration/1000000;
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
    /**
     * gerRunTime() will return the number of ms the bot took to complete the computation. 
     */
    public double getRuntime(){
        return runtime;
    }

    public double get_v0x(){
        return vx0;
    }
    public double get_v0y(){
        return vy0;
    }
}