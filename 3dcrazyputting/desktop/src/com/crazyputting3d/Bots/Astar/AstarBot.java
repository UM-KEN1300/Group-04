package com.crazyputting3d.Bots.Astar;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import com.crazyputting3d.Engine.physicsEngine;
import com.crazyputting3d.InputReader.Function;
import com.crazyputting3d.InputReader.Search;

public class AstarBot {

    protected physicsEngine engine;
    private Search search;
    private int level;
    private Astar astar;
    private Function function;

    private float terrainX1;
    private float terrainX2;
    private float terrainZ1;
    private float terrainZ2;
    private double ballX;
    private double ballY;
    private double holeX;
    private double holeY;
    private double[] xvaluesSP;
    private double[] zvaluesSP;
    private double[] xvaluesT;
    private double[] zvaluesT;
    private double[] xvaluesW;
    private double[] zvaluesW;
    private double runtime;

    private ArrayList<Double> coords = new ArrayList<>();
    private ArrayList<Node> path = new ArrayList<>();

    private ArrayList<Float> allXCoords;
    private ArrayList<Float> allYCoords;
    private ArrayList<Float> allZCoords;
    public float[] coordsx;
    public float[] coordsy;
    public float[] coordsz;
    private double epsilon = Math.pow(2,-53);
    private int moveNumber = 0;
    ArrayList<Node> crucialPoints = new ArrayList<>();

    public AstarBot(physicsEngine engine, int level) {
        this.engine = engine;
        this.level = level;
        coordsx = new float[500000];
        coordsy = new float[500000];
        coordsz = new float[500000];
        allXCoords = new ArrayList<>();
        allYCoords = new ArrayList<>();
        allZCoords = new ArrayList<>();
        function = new Function();
        getValues();
        setTerrainCoords();
        long startTime = System.nanoTime();
        Node start = getStart();
        Node end = getEnd();
        astar = new Astar(start, end, (int) terrainX2, (int) terrainZ2, (int) terrainX1, (int) terrainZ1);
        astar.initialize();
        setDead(astar);
        AstarPanel panel = new AstarPanel(astar.search());
        path = astar.getPath();
        crucialPoints.add(start);
        for(int i = path.size()-2;i >=1 ; i--){
            Node first = path.get(i+1);
            Node middle = path.get(i);
            Node last = path.get(i-1);
            if(Math.abs((first.y-middle.y)*(last.x-middle.x)-((last.y-middle.y)*(first.x-middle.x)))>=epsilon){
                crucialPoints.add(middle);
                middle.state = Color.MAGENTA;
            }
        }
        crucialPoints.add(end);
        panel.run();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        runtime += duration/1000000;
    }

    public void calculateMove() {
            double vx = Math.sqrt(2*physicsEngine.g*engine.get_muk()*Math.abs(crucialPoints.get(moveNumber).x-crucialPoints.get(moveNumber+1).x)/10);
            double vy = Math.sqrt(2*physicsEngine.g*engine.get_muk()*Math.abs(crucialPoints.get(moveNumber).y-crucialPoints.get(moveNumber+1).y)/10);
            if(crucialPoints.get(moveNumber).y-crucialPoints.get(moveNumber+1).y>0){
                vy=-vy;
            }
            if(crucialPoints.get(moveNumber).x-crucialPoints.get(moveNumber+1).x>0){
                vx=-vx;
            }
            engine.setVelocities(vx, vy);
            moveNumber++;
    }
    public void makeMove(){
        while(moveNumber<crucialPoints.size()-1){
            calculateMove();
        }
    }

    public float[] getCoordsx() {
        for (int i = 0; i < allXCoords.size(); i++) {
            coordsx[i] = allXCoords.get(i);
        }
        return coordsx;
    }

    public float[] getCoordsy() {
        for (int i = 0; i < allYCoords.size(); i++) {
            coordsy[i] = allYCoords.get(i);
        }
        return coordsy;
    }

    public float[] getCoordsz() {
        for (int i = 0; i < allZCoords.size(); i++) {
            coordsz[i] = allZCoords.get(i);
        }
        return coordsz;
    }
    public double getRuntime(){
        return runtime;
    }

    public void setDead(Astar astar) {
        int xlength = (int) ((terrainX2 * 10) + Math.abs(terrainX1 * 10));
        int ylength = (int) ((terrainZ2 * 10) + Math.abs(terrainZ1 * 10));
        // Set trees to dead
        for (int i = 0; i < xvaluesT.length; i++) {
            int x = (int) (xvaluesT[i] * 10 + Math.abs(terrainX1 * 10));
            int y = (int) (zvaluesT[i] * 10 + Math.abs(terrainZ1 * 10));
            astar.setDeadNode(x, y);
            astar.setDeadNode(x + 1, y + 1);
            astar.setDeadNode(x + 1, y);
            astar.setDeadNode(x, y + 1);
            astar.setDeadNode(x - 1, y - 1);
            astar.setDeadNode(x, y - 1);
            astar.setDeadNode(x - 1, y);
            astar.setDeadNode(x + 1, y - 1);
            astar.setDeadNode(x - 1, y + 1);
        }

        for (int x = 0; x < xlength; x++) {
            for (int y = 0; y < ylength; y++) {
                // Set dead node for walls
                for (int i = 0; i < xvaluesW.length; i = i + 2) {
                    if (x >= (xvaluesW[i] * 10 + Math.abs(terrainX1 * 10))
                            && x <= (xvaluesW[i + 1] * 10 + Math.abs(terrainX1 * 10))
                            && y >= (zvaluesW[i] * 10 + Math.abs(terrainZ1 * 10))
                            && y <= (zvaluesW[i + 1] * 10 + Math.abs(terrainZ1 * 10))) {
                        astar.setDeadNode(x, y);
                    }
                }
                // Set dead node for water
                if (function.getHeightFunction(level, (x - Math.abs(terrainX1) * 10) / 10,
                        (y - Math.abs(terrainZ1) * 10) / 10) < 0) {
                    // System.out.println(x+" "+y);
                    astar.setDeadNode(x, y);
                }
            }
        }
    }

    public Node getStart() {
        int x = (int) ((ballX * 10) + Math.abs(terrainX1 * 10));
        int y = (int) ((ballY * 10) + Math.abs(terrainZ1 * 10));
        return new Node(x, y);
    }

    public Node getEnd() {
        int x = (int) ((holeX * 10) + Math.abs(terrainX1 * 10));
        int y = (int) ((holeY * 10) + Math.abs(terrainZ1 * 10));
        return new Node(x, y);
    }

    // Get all the values from search.java and put them into the appropriate
    // variables
    public void getValues() {
        try {
            search = new Search("inputFile" + this.level + ".txt");
            search.run();

            xvaluesSP = search.get_sandPitX();
            zvaluesSP = search.get_sandPitY();

            xvaluesT = search.get_treeX();
            zvaluesT = search.get_treeY();

            xvaluesW = search.get_wallX();
            zvaluesW = search.get_wallY();

            ballX = search.get_x0();
            ballY = search.get_y0();
            holeX = search.get_xt();
            holeY = search.get_yt();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add all the different coordinates in one ArrayList
        coords.add(ballX);
        coords.add(ballY);
        coords.add(holeX);
        coords.add(holeY);

        for (int i = 0; i < xvaluesSP.length; i++) {
            coords.add(xvaluesSP[i]);
            coords.add(zvaluesSP[i]);
        }

        for (int i = 0; i < xvaluesT.length; i++) {
            coords.add(xvaluesT[i]);
            coords.add(zvaluesT[i]);
        }

        for (int i = 0; i < xvaluesW.length; i++) {
            coords.add(xvaluesW[i]);
            coords.add(zvaluesW[i]);
        }
    }

    // Use the array which stores the coordinates to calculate the min and max of X
    // and Y
    public void setTerrainCoords() {
        double biggestX = -1000000;
        double smallestX = 1000000;
        double biggestY = -1000000;
        double smallestY = 1000000;
        int offset = 3;
        for (int i = 0; i < coords.size(); i++) {
            if (i % 2 == 0) {
                if (coords.get(i) > biggestX) {
                    biggestX = coords.get(i);
                }
                if (coords.get(i) < smallestX) {
                    smallestX = coords.get(i);
                }
            } else {
                if (coords.get(i) > biggestY) {
                    biggestY = coords.get(i);
                }
                if (coords.get(i) < smallestY) {
                    smallestY = coords.get(i);
                }
            }
        }
        terrainX1 = (int) (smallestX - offset);
        terrainX2 = (int) (biggestX + offset);
        terrainZ1 = (int) (smallestY - offset);
        terrainZ2 = (int) (biggestY + offset);
    }
}
