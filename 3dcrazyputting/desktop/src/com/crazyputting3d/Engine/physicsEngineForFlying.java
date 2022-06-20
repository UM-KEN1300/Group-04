package com.crazyputting3d.Engine;

import java.io.IOException;
import com.crazyputting3d.Objects.StateVector;

/**
 * The physics engine simulates the real-life motion of the ball in a golf
 * course.
 * author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen
 * van Gelder, Trinh Le,
 * Gabrijel Radovcic, Elza Strazda
 * version 3.0
 * since 2022-05-11
 */

public class physicsEngineForFlying extends physicsEngine{
    private double angle;
    private boolean flying;
    private double flyingTime;

    /**
     * Constructor initiates variables according to input file data. These include:
     * x and y starting position of the ball and hole, kinetic and static friction
     * for grass and sand.
     * Initiates a storage for all present sand pits and trees.
     * Sets terrain coordinates.
     */

    public physicsEngineForFlying(int level) throws IOException {
        super(level);
    }

    /**
     * isInWater() determines whether the ball coordinates are in the water.
     * param values for x and y coordinates of the ball.
     * Gets the height function value for param x and y, and checks if it is smaller
     * than 0. If so, it means it is present in water.
     * returns true if the ball is in water, and false if it is not.
     */

    public boolean isInWater(double x, double y, double z) {

        if (h(x,y)<0 && z < 0) {
            return true;
        } else {
            return false;
        }
    }

    public StateVector trajectory(StateVector v, boolean botPlays) {
        while (true) {
            double m = 0;
            double ms = 0;
            double x = v.getX();
            double y = v.getY();
            double vx = v.getVX();
            double vy = v.getVY();
            double velocity = Math.sqrt(vx * vx + vy * vy);
            double z = h(x,y);
            if(!flying){
                if (isFinish(x, y)) {
                    return v;
                }
                else if (isInSandPit(x, y)) {
                    m = muks;
                    ms = muss;
                } else {
                    m = muk;
                    ms = mus;
                }
                StateVector newv = solver.run(v,m);
                if(h(newv.getX(),newv.getY())-h(x,y)>=0){
                    angle = Math.tanh(Math.hypot(hxderivated(v.getX(), v.getY()), hyderivated(v.getX(), v.getY())));
                    double potentialz =  h*velocity * Math.tan(angle)
                             - g / 2 * Math.pow(h*velocity, 2) / Math.pow(velocity * Math.cos(angle), 2);
                    flying = isFlying(x+h*vx, y+h*vy, potentialz);
                    flyingTime = h;
                }
            }
            else{
                z =  flyingTime*velocity * Math.tan(angle)
                    - g / 2 * Math.pow(flyingTime*velocity, 2) / Math.pow(velocity * Math.cos(angle), 2);
                flying = isFlying(x, y,z);
                if(flying){
                    flyingTime += h;
                }
                else{
                    flyingTime=0;
                }
            }
            if (!botPlays) {
                if (speedCounter % ((0.005 / h) * ((int)velocity )) == 0) {
                    ball_coordinates_x[counter] = x;
                    ball_coordinates_y[counter] = y;
                    ball_coordinates_z[counter] = z;
                    counter++;
                }
                speedCounter++;
            }
            if (!flying) {
                if (isFinish(x, y)) {
                    return v;
                }
                else if (isInSandPit(x, y)) {
                    m = muks;
                    ms = muss;
                } else {
                    m = muk;
                    ms = mus;
                }
                v = solver.run(v, m);
                if (botPlays) {
                    slopex = slopex + hxderivated(v.getX(), v.getY()) * h / (m * g) + h / 2;
                    slopey = slopey + hyderivated(v.getX(), v.getY()) * h / (m * g) + h / 2;
                }

                if (botPlays && (closestEuklidiandistance > Math.hypot(v.getX() - xt, v.getY() - yt))) {
                    closestEuklidiandistance = Math.hypot(v.getX() - xt, v.getY() - yt);
                }
                if (botPlays && (closestEuklidiandistance == initialDistance)) {
                    closestEuklidiandistance = Math.hypot(v.getX() - xt, v.getY() - yt) + Math.hypot(x - xt, y - yt);
                }
                double staticFriction = Math
                    .sqrt(Math.pow(hxderivated(v.getX(), v.getY()), 2) + Math.pow(hyderivated(v.getX(), v.getY()), 2));
            if (terminates(vx, vy, m)) {
                v.setX(x + v.getVX() * h);
                v.setY(y + v.getVY() * h);
                if (ms > staticFriction) {
                    if (!botPlays) {
                        ball_coordinates_x[counter] = x;
                        ball_coordinates_y[counter] = y;
                        ball_coordinates_z[counter] = z;
                        counter++;
                    }
                    return v;
                }
            }
            } else {
                v.setX(x+h*vx);
                v.setY(y+h*vy);
            }
            if (isInWater(v.getX(), v.getY(), z)) {
                if (!botPlays) {
                    ball_coordinates_x[counter] = x;
                    ball_coordinates_y[counter] = y;
                    ball_coordinates_z[counter] = z;
                    counter++;
                }
                return null;
            }
            StateVector stop = checkObstacles(x, y, z, v, botPlays);
            if (stop != null) {
                flying = false;
                if (isInWater(stop.getX(), stop.getY(), h(stop.getX(), stop.getY()))) {
                    if (!botPlays) {
                        ball_coordinates_x[counter] = x;
                        ball_coordinates_y[counter] = y;
                        ball_coordinates_z[counter] = z;
                        counter++;
                    }
                    return null;
                }
                return stop;
            }

        }

    }

    public StateVector checkObstacles(double x, double y, double z, StateVector v, boolean botPlays) {
        if (isInTree(v.getX(), v.getY())) {
            if (!botPlays) {
                ball_coordinates_x[counter] = x;
                ball_coordinates_y[counter] = y;
                ball_coordinates_z[counter] = h(x,y);
                counter++;
            }
            v.setVX(h);
            v.setVY(h);
            v.setX(x);
            v.setY(y);
            return v;
        }
        if (isInWall(v.getX(), v.getY())) {
            if (!botPlays) {
                ball_coordinates_x[counter] = x;
                ball_coordinates_y[counter] = y;
                ball_coordinates_z[counter] = h(x, y);
                counter++;
            }
            v.setVX(h);
            v.setVY(h);
            v.setX(x);
            v.setY(y);
            return v;
        }

        if (!isInTerrain(v.getX(), v.getY())) {
            if (!botPlays) {
                ball_coordinates_x[counter] = x;
                ball_coordinates_y[counter] = y;
                ball_coordinates_z[counter] = h(x, y);
                counter++;
            }
            v.setVX(h);
            v.setVY(h);
            v.setX(x);
            v.setY(y);
            return v;
        }
        return null;
    }

    public boolean isFlying(double x, double y, double z) {
        if (z > h(x, y)){
            return true;
        }
        return false;
    }

}