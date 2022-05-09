package com.crazyputting3d.Bots;

import com.crazyputting3d.Objects.StateVector;
import com.crazyputting3d.physicsEngine;

public class NewtonRaphsonBot {
    public final double pi = 3.141159;
    private physicsEngine engine;
    private double xt;
    private double yt;
    private double radius;
    private double x0;
    private double y0;
    double v = 5;
    int i = 1;

    public NewtonRaphsonBot(physicsEngine engine) {
        this.engine = engine;
        this.xt = engine.getXt();
        this.yt = engine.getYt();
        this.radius = engine.getrOfHole();
        this.x0 = engine.getX0();
        this.y0 = engine.getY0();
    }

    public StateVector calculateMove() {
        double distance = Math.hypot(x0-xt, y0-yt);
        double accuracy = 0.000001;
        double minVx = Double.MAX_VALUE;
        double minVy = Double.MAX_VALUE;
        StateVector min = new StateVector(x0, y0, minVx, minVy);
        double dx = 0.0000000001;
        double angle = 0;//Math.acos(Math.abs(xt - x0) / distance);
        double originalangle = angle;
        double vx = Math.cos(angle) * v;
        double vy = Math.sin(angle) * v;
        engine.setVelocitiesForBot(x0, y0, vx, vy);
        double tempEuclidianDistance = engine.get_closestEuclideanDistance();
        double vx2 = Math.cos(angle+dx) * v;
        double vy2 = Math.sin(angle+dx) * v;
        engine.setVelocitiesForBot(x0, y0, vx2, vy2);
        double tempEuclidianDistance2 = engine.get_closestEuclideanDistance();
        double angle_step = 0;

        while (tempEuclidianDistance >= radius) {
            System.out.println(angle);
            angle_step = tempEuclidianDistance/((tempEuclidianDistance-tempEuclidianDistance2)/dx);
            angle -= angle_step;
            if(angle_step<accuracy){
                System.out.println(v);
                v -= 0.1;
                angle = originalangle;
                if(v<=0){
                    return new StateVector(x0, y0, 0, 0);
                }
            }
            vx = Math.cos(angle) * v;
            vy = Math.sin(angle) * v;
            engine.setVelocitiesForBot(x0, y0, vx, vy);
            tempEuclidianDistance = engine.get_closestEuclideanDistance();
            vx2 = Math.cos(angle+dx) * v;
            vy2 = Math.sin(angle+dx) * v;
            engine.setVelocitiesForBot(x0, y0, vx2, vy2);
            tempEuclidianDistance2 = engine.get_closestEuclideanDistance();
        }
        min = new StateVector(x0, y0, Math.cos(angle) * v, Math.sin(angle) * v);

        return min;
    }

    public void makeMove() {
        long startTime = System.nanoTime();
        StateVector move = calculateMove();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Run time of the Newton Raphson bot algorithm (ms): " + duration / 1000000);
        double vx = move.getVX();
        double vy = move.getVY();
        engine.setVelocities(vx, vy);
    }
}
