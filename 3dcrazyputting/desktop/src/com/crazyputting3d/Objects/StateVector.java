package com.crazyputting3d.Objects;

/**
 * State Vector object.
 * author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 *          Gabrijel Radovčić, Elza Strazda
 * version 1.0
 * since   2021-03-11
 */


public class StateVector {

    double x;
    double y;
    double vx;
    double vy;

    /**
     * Constructor assigns each StateVector an x-position, y-position, x-velocity and y-velocity.
     */

    public StateVector(double x, double y, double vx, double vy){
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    /**
     * Setters and getters.
     */

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getVX(){
        return vx;
    }
    public double getVY(){
        return vy;
    }
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }
    public void setVX(double vx){
        this.vx = vx;
    }
    public void setVY(double vy){
        this.vy = vy;
    }

    /**
     * toString() returns StateVector in a string format for testing purposes. 
     */

    public String toString(){
        return x+"|"+y+"|"+vx+"|"+vy;
    }
}
