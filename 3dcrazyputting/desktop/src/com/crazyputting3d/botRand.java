package com.crazyputting3d;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class botRand {
    physicsEngine engine;
    private double speed;
    private double directionX;
    private double directionY;
    private double speedX;
    private double speedY;
    private boolean flag;
    private double holeX;
    private double holeY;
    private int count;
    final double length = 0.3;
    
    private Search search;

    public botRand() {
        try {
            this.search = new Search("input.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.holeX = search.get_xt();
        this.holeY = search.get_yt();
        this.count=0;
        this.speed = 0;
        this.directionX=0;
        this.directionY=0;
        this.flag=true;
    }

    public double h(double x, double y){
        cheat cheat = new cheat();
        return cheat.getHeightFunction(x, y);
    }

    public void testTrajectory() {
        engine.setVelocities(2, 2);
    }

    public static double randomDouble(double min, double max) {
        return (ThreadLocalRandom.current().nextDouble() * (max - min)) + min;
    }

    public void setRandSpeed() {
        //speed = randomDouble(3, 5);
        speed=5;
    }

    public void setRandDirection() {
        double angle = Math.random()*Math.PI*2;
        directionX = Math.cos(angle)*length;
        directionY = Math.sin(angle)*length;
    }

    public void trajectory() {
        while(flag) {
            try {
                engine = new physicsEngine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            setRandDirection();
            setRandSpeed();
            speedX = speed*directionX/length;
            speedY = speed*directionY/length;
            if((speedX>0&&holeX<0)||(speedX<0&&holeX>0)||(speedY>0&&holeY<0)||(speedY<0&&holeY>0)) { continue; }
            count++;
            engine.setVelocities(speedX, speedY);
            if(engine.isInHole()) {
                System.out.println("Hole in one! On try number: " + count);
                System.out.println("Hole in one speedX: " + speedX);
                System.out.println("Hole in one speedY: " + speedY);
                flag=false;
                break;
            }
            System.out.println(count + ": Speed X: " + speedX + " Speed Y: " + speedY);
        }
    }

    public double[] getTrajectory() {
        double[] arr = new double[2];
        arr[0] = speedX;
        arr[1] = speedY;
        return arr;
    }

    public static void main(String[] args) {
        botRand bot = new botRand();
        bot.trajectory();
    }
}
