package com.crazyputting3d;

public class BasicBot {
    private physicsEngine engine;
    private double x0;
    private double y0;
    private double xt;
    private double yt;
    private double radius;
    private boolean flag;
    private double speed;
    private double directionX;
    private double directionY;
    final double length = 0.3;
    private double speedX;
    private double speedY;

    public BasicBot(physicsEngine engine) {
        this.engine = engine;
        this.x0 = engine.getX0();
        this.y0 = engine.getY0();
        this.xt = engine.getXt();
        this.yt = engine.getYt();
        this.radius = engine.getrOfHole();
        this.flag = true;
        this.speed = 5;
    }

    public StateVector play(){
        double angle = Math.atan((yt - y0) / (xt - x0)) * 180 / Math.PI;
        directionX = Math.cos(angle)*length;
        directionY = Math.sin(angle)*length;

        double minVx = Double.MAX_VALUE;
        double minVy = Double.MAX_VALUE;

        StateVector min = new StateVector(x0,y0,minVx,minVy);
        speedX = speed*directionX/length;
        speedY = speed*directionY/length;

        System.out.println(speedX);
        System.out.println(speedY);
         
        min.setVX(speedX);
        min.setVY(speedY);

        return min;
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
