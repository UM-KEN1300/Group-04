package com.crazyputting3d.Objects;

public class Wall {
    double xStart; //x starting coordinate for wall
    double xEnd; //x ending coordinate for wall
    double yStart; //y starting coordinate for wall
    double yEnd; //y ending coordinate for wall


    public Wall(double xStart, double xEnd, double yStart, double yEnd){
        this.xStart = xStart;
        this.xEnd = xEnd;
        this.yStart = yStart;
        this.yEnd = yEnd;
    }

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

    public boolean isInWall(double x, double y){
        if(x>xStart&&x<xEnd&&y>yStart&&y<yEnd){
            return true;
        }
        return false;
    }
}
