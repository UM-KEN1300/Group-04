package com.crazyputting3d.Objects;

/**
 * Sandpit object.
 * author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 *          Gabrijel Radovčić, Elza Strazda
 * version 1.0
 * since   2021-03-11
 */

public class Sandpit{

    double xStart; //x starting coordinate for sandpit
    double xEnd; //x ending coordinate for sandpit
    double yStart; //y starting coordinate for sandpit
    double yEnd; //y ending coordinate for sandpit
    double muks; //kinetic friction coeff of sand
    double muss; //static friction coeff of sand

    /**
     * Constructor assigns each Sandpit an x-position range, y-position range, static and kinetic friction.
     */

    public Sandpit(double xStart, double xEnd, double yStart, double yEnd, double muks, double muss){
        this.xStart = xStart;
        this.xEnd = xEnd;
        this.yStart = yStart;
        this.yEnd = yEnd;
        this.muks = muks;
        this.muss = muss;
    }

    /**
     * Setters and getters.
     */

    public double getXStart(){
        return xStart;
    }

    public double getXEnd(){
        return xEnd;
    }

    public double getYStart(){
        return yStart;
    }

    public double getYEnd(){
        return yEnd;
    }

    public double getMuks(){
        return muks;
    }

    public double getMuss(){
        return muss;
    }

    public void setXStart(double xStart){
        this.xStart=xStart;
    }
    public void setXEnd(double xEnd){
        this.xEnd=xEnd;
    }

    public void setYStart(double yStart){
        this.yStart=yStart;
    }
    public void setyEnd(double yEnd){
        this.yEnd=yEnd;
    }

    public void setMuks(double muks){
        this.muks=muks;
    }

    public void setMuss(double muss){
        this.muss=muss;
    }

    /**
     * isInSandPit() checks if the ball goes in the Sandpit.
     * param values for x and y coordinates of the ball.
     * returns true if the ball goes in the Sandpit, or false otherwise.
     */

    public boolean isInSandPit(double x, double y){
        if(x>=xStart&&x<=xEnd&&y>=yStart&&y<=yEnd){
            return true;
        }
        return false;
    }

}