package com.crazyputting3d;
public class HillClimbingBot {
    public final double pi=3.141159;
    private physicsEngine engine;
    private double xt;
    private double yt;
    private double radius;
    private double x0;
    private double y0;
    public HillClimbingBot(physicsEngine engine){
        this.engine = engine;
        this.xt = engine.getXt();
        this.yt = engine.getYt();
        this.radius = engine.getrOfHole();
        this.x0 = engine.getX0();
        this.y0 = engine.getY0();
    }
    public StateVector calculateMove(){
        double angle = Math.acos(Math.abs(xt-x0)/Math.sqrt(Math.pow(x0-xt, 2)+Math.pow(y0-yt, 2)));
        double euklidianDistance = Double.MAX_VALUE;
        double minVx = Double.MAX_VALUE;
        double minVy = Double.MAX_VALUE;
        StateVector min = new StateVector(x0,y0,minVx,minVy);
        for(int i = 0; i < 360; i++){
            angle += pi/180.0;
            for(double v = 5; v >= 0; v-=0.1){
                double vx = Math.cos(angle)*v;
                double vy = Math.sin(angle)*v;
                StateVector temp = engine.setVelocitiesForBot(x0, y0, vx, vy);
                double tempEuclidianDistance = Math.sqrt(Math.pow(temp.getX()-xt, 2)+Math.pow(temp.getY()-yt, 2));
                if(tempEuclidianDistance<radius){
                    min.setVX(vx);
                    min.setVY(vy);
                    return min;
                }
                if(tempEuclidianDistance<euklidianDistance){
                    min = temp;
                    euklidianDistance = tempEuclidianDistance;
                }
            }
        }
        return min;
    }
     public void makeMove(){
        long startTime = System.nanoTime();
        StateVector move = calculateMove();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Run time of the Hill Climbing bot algorithm (ms): " + duration/1000000);
         double vx = move.getVX();
         double vy = move.getVY();
         engine.setVelocities(vx, vy);
     }
}