package com.crazyputting3d;

public class HillClimbingBot {
    public final double pi = 3.141159;
    private physicsEngine engine;
    private double xt;
    private double yt;
    private double radius;
    private double x0;
    private double y0;
    double angle_step = pi / 180;
    double v = 5;
    int i = 1;

    public HillClimbingBot(physicsEngine engine) {
        this.engine = engine;
        this.xt = engine.getXt();
        this.yt = engine.getYt();
        this.radius = engine.getrOfHole();
        this.x0 = engine.getX0();
        this.y0 = engine.getY0();
    }

    public StateVector calculateMove() {

        double minVx = Double.MAX_VALUE;
        double minVy = Double.MAX_VALUE;
        StateVector min = new StateVector(x0, y0, minVx, minVy);

        double angle = Math.acos(Math.abs(xt - x0) / Math.sqrt(Math.pow(x0 - xt, 2) + Math.pow(y0 - yt, 2)));
        double vx = Math.cos(angle) * v;
        double vy = Math.sin(angle) * v;
        engine.setVelocitiesForBot(x0, y0, vx, vy);
        double tempEuclidianDistance = engine.get_closestEuclideanDistance();
        int counter = 0;
        double tempEuclidianDistanceRight = 0;
        double tempEuclidianDistanceLeft = 0;
        boolean left = false;
        boolean right = false;

        while (tempEuclidianDistance >= radius) {
            if (counter == 0) {
                engine.setVelocitiesForBot(x0, y0, Math.cos(angle + angle_step) * v,
                        Math.sin(angle + angle_step) * v);
                tempEuclidianDistanceRight = engine.get_closestEuclideanDistance();
                engine.setVelocitiesForBot(x0, y0, Math.cos(angle - angle_step) * v,
                        Math.sin(angle - angle_step) * v);
                tempEuclidianDistanceLeft = engine.get_closestEuclideanDistance();
                counter++;

                if (tempEuclidianDistance > tempEuclidianDistanceRight) {
                    right = true;
                }
                if (tempEuclidianDistance > tempEuclidianDistanceLeft) {
                    left = true;
                }
                if (right && left) {
                    if (tempEuclidianDistanceRight > tempEuclidianDistanceLeft) {
                        right = false;
                    } else {
                        left = false;
                    }
                }
            }
            if(right){
                engine.setVelocitiesForBot(x0, y0, Math.cos(angle + angle_step) * v,
                        Math.sin(angle + angle_step) * v);
                tempEuclidianDistanceRight = engine.get_closestEuclideanDistance();
                if(tempEuclidianDistance>tempEuclidianDistanceRight){
                    tempEuclidianDistance = tempEuclidianDistanceRight;
                    angle += angle_step;
                    vx = Math.cos(angle) * v;
                    vy = Math.sin(angle) * v;
                }
                else{
                    right = false;
                    angle_step = angle_step / 2;
                    counter = 0;
                }
            }
            else if(left){
                engine.setVelocitiesForBot(x0, y0, Math.cos(angle - angle_step) * v,
                        Math.sin(angle - angle_step) * v);
                tempEuclidianDistanceLeft = engine.get_closestEuclideanDistance();
                if(tempEuclidianDistance>tempEuclidianDistanceLeft){
                    tempEuclidianDistance = tempEuclidianDistanceLeft;
                    angle -= angle_step;
                    vx = Math.cos(angle) * v;
                    vy = Math.sin(angle) * v;
                    
                }
                else{
                    left = false;
                    angle_step = angle_step / 2;
                    counter = 0;
                }
            }
            else{
                angle_step = angle_step / 2;
                counter = 0;
            }
            if(!left&!right&Math.sin(angle_step)*Math.hypot(x0-xt, y0-yt)<engine.getrOfHole()){
                v -= 0.1;
                if(v<=0){
                    angle_step = 2*i*pi/180;
                    i=2*i;
                    v=5;
                    if(angle_step>=pi){
                        return new StateVector(x0, y0, 0, 0);
                    }
                }
            }

        }
        min = new StateVector(x0, y0, Math.cos(angle) * v, Math.sin(angle) * v);

        return min;
    }

    public void makeMove() {
        long startTime = System.nanoTime();
        StateVector move = calculateMove();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Run time of the Hill Climbing bot algorithm (ms): " + duration / 1000000);
        double vx = move.getVX();
        double vy = move.getVY();
        engine.setVelocities(vx, vy);
    }
}
