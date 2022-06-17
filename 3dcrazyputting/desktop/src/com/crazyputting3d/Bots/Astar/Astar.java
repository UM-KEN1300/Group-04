package com.crazyputting3d.Bots.Astar;

import java.util.*;

import com.badlogic.gdx.utils.Array;

import java.awt.*;


public class Astar {
    private Node startNode;
    private Node endNode;
    private Node[][] nodes;

    private ArrayList<Node> path = new ArrayList<>();

    private ArrayList<Node> closedNodes = new ArrayList<>();
    private ArrayList<Node> openNodes = new ArrayList<>();

    private boolean found;

    private int xsize;
    private int ysize;
    private int xoffset;
    private int yoffset;


    public Astar(Node startNode,Node endNode,int xsize,int ysize,int xoffset, int yoffset) {
        this.startNode = startNode;
        this.endNode = endNode;

        this.xsize = xsize;
        this.ysize = ysize;
        this.xoffset = xoffset;
        this.yoffset = yoffset;

        path = new ArrayList<>();
        closedNodes = new ArrayList<>();
        openNodes = new ArrayList<>();
        
        nodes = new Node[(xsize+Math.abs(xoffset))*10][(ysize+Math.abs(yoffset))*10];
    }

    public Node[][] search() {

        ArrayList<Node> nbours = new ArrayList<>();
        Node current = startNode;
        openNodes.add(current);
        int lowestindex=0;
        double lowestf;

        while(!found) {
            lowestf=1000000;
            for(int i=0; i<openNodes.size(); i++) {
                if(openNodes.get(i).f<lowestf) {
                    lowestindex = i;
                    lowestf=openNodes.get(i).f;
                } else if (openNodes.get(i).f==lowestf) {
                    if(openNodes.get(i).g<openNodes.get(lowestindex).g) {
                        lowestindex = i;                    
                    }
                }
            }

            if(openNodes.size()!=0) {
                current = openNodes.get(lowestindex);
            }

            current.setSearched();

            openNodes.remove(current);
            closedNodes.add(current);

            if(current.equals(endNode)) {
                trackPath();
                startNode.setStart();
                endNode.setEnd();
                found=true;
                return nodes;
            }

            if(current.x+1<nodes.length) {
                nbours.add(nodes[current.x+1][current.y]);
            }
            if(current.x-1>=0) {
                nbours.add(nodes[current.x-1][current.y]);
            }
            if(current.y+1<nodes[0].length) {
                nbours.add(nodes[current.x][current.y+1]);
            }
            if(current.y-1>=0) {
                nbours.add(nodes[current.x][current.y-1]);
            }
            
            for(int i=0; i<nbours.size(); i++) {
                if(nbours.get(i).dead==false && !closedNodes.contains(nbours.get(i))) {
                    if(!openNodes.contains(nbours.get(i))) {
                        nbours.get(i).parent = current;
                        openNodes.add(nbours.get(i));
                    }
                }
            }
            nbours.clear();

        }
     
        return null;
    }

    public ArrayList<Node> getPath() {
        return path;
    }

    private void trackPath() {
        Node node = endNode;
        while(node!=startNode) {
            node=node.parent;
            if(node!=startNode) {
                node.setPath();
                path.add(node);
            }
        }
    }

    public void initialize() {
        //Initialize all the nodes and set their costs
        for(int i=0; i<(xsize+Math.abs(xoffset))*10; i++) {
            for(int j=0; j<(ysize+Math.abs(yoffset))*10; j++) {
                nodes[i][j] = new Node(i, j);
                setC(nodes[i][j]);
            }
        }

        startNode = nodes[startNode.x][startNode.y];
        endNode = nodes[endNode.x][endNode.y];
    }

    private void setC(Node node) {
        double dx = Math.abs(node.x-endNode.x);
        double dy = Math.abs(node.y-endNode.y);
        double h = Math.sqrt((dx*dx)+(dy*dy));

        double dx2 = Math.abs(node.x-startNode.x);
        double dy2 = Math.abs(node.y-startNode.y);
        double g=Math.sqrt((dx2*dx2)+(dy2*dy2));

        node.g = g;
        node.f = h+g;
    }

    public void setDeadNode(int x, int y) {
        //System.out.println(nodes[x].length);
        nodes[x][y].setDead();
    }

    

}
