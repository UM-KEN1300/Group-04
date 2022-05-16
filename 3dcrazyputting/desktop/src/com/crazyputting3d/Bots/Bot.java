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
    public double x0;
    public double y0;
    public double xt;
    public double yt;
    public final double length = 0.3;
    public final double pi = 3.141159;
    public double radius;
    public double v = 5;
    public int i = 1;

    public Bot(physicsEngine engine) {
        this.engine = engine;
        this.xt = engine.getXt();
        this.yt = engine.getYt();
        this.radius = engine.getrOfHole();
        this.x0 = engine.getX0();
        this.y0 = engine.getY0();
    }
    public abstract StateVector calculateMove();

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