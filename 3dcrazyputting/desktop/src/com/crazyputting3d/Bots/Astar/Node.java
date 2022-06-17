package com.crazyputting3d.Bots.Astar;

import java.awt.*;

public class Node {
    public int x;
    public int y;
    public double g;
    public double f;
    public boolean dead;
    public Node parent;
    public Color state = Color.WHITE;

    public Node(int x, int y) {
        this.x=x;
        this.y=y;
    }

    public void setDead() {
        state=Color.BLACK;
        dead=true;
    }

    public void setPath() {
        state=Color.GREEN;
    }

    public void setSearched() {
        state=Color.ORANGE;
    }

    public void setStart() {
        state=Color.BLUE;
    }

    public void setEnd() {
        state=Color.RED;
    }



}


