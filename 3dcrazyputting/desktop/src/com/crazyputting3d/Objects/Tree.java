package com.crazyputting3d.Objects;

/**
 * Tree object.
 * author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 *          Gabrijel Radovčić, Elza Strazda
 * version 1.0
 * since   2021-03-11
 */

public class Tree {

    double xStart; //x start coordinate of tree
    double yStart; //y start coordinate of tree
    double radius; //radius of tree

    /**
     * Constructor assigns each tree an x-position, y-position and radius.
     */

    public Tree(double xStart, double yStart, double radius){
        this.xStart = xStart;
        this.yStart = yStart;
        this.radius = radius;
    }

    /**
     * Setters and getters.
     */

    public double getXStart(){
        return xStart;
    }
    public double getYStart(){
        return yStart;
    }
    public double getRadius(){
        return radius;
    }
    public void setXStart(double xStart){
        this.xStart=xStart;
    }
    public void setYStart(double yStart){
        this.yStart=yStart;
    }
    public void setRadius(double radius){
        this.radius=radius;
    }

    /**
     * isInTree() checks if the ball hits the tree.
     * param values for x and y coordinates of the ball.
     * returns true if the ball hits the tree, or false otherwise.
     */

    public boolean isInTree(double x, double y){
        if(((x-xStart)*(x-xStart)+(y-yStart)*(y-yStart))<radius*radius){
            return true;
        }
        return false;
    }
}
