package com.crazyputting3d.Bots.Astar;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import com.crazyputting3d.Engine.physicsEngine;
import com.crazyputting3d.InputReader.Function;
import com.crazyputting3d.InputReader.Search;
import com.crazyputting3d.Objects.StateVector;


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
    private double [] xvaluesSP;
    private double [] zvaluesSP;
    private double [] xvaluesT;
    private double [] zvaluesT;
    private double [] xvaluesW;
    private double [] zvaluesW;

    private ArrayList<Double> coords = new ArrayList<>();
    private ArrayList<Node> path = new ArrayList<>();

    private double prevBallx;
    private double prevBally;
    private boolean notMoving;

    private ArrayList<Float> allXCoords;
    private ArrayList<Float> allYCoords;
    private ArrayList<Float> allZCoords;
    public float[] coordsx;
    public float[] coordsy;
    public float[] coordsz;

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
    }

    public StateVector calculateMove() {
        int iteration=0;
        int sight = 10;
        StateVector min = new StateVector(ballX, ballY, 0, 0);
        System.out.println("Astar bot active");

        while(iteration<1000) {
            Node start = getStart();
            Node end = getEnd();
            astar = new Astar(start, end, (int)terrainX2, (int)terrainZ2, (int)terrainX1, (int)terrainZ1);
            astar.initialize();
            setDead(astar);
            AstarPanel panel = new AstarPanel(astar.search());
            path=astar.getPath();

            if(path.size()<sight) {
                sight=path.size();
            }

            path.get(path.size()-sight).state = Color.magenta;

            if(iteration==0) {
                panel.run();
            }

            double xtest = (path.get(path.size()-sight).x - Math.abs(terrainX1)*10)/10;
            double ytest = (path.get(path.size()-sight).y - Math.abs(terrainZ1)*10)/10;
            sight=10;

            double angle = Math.atan2((ytest - ballY) , (xtest - ballX));


            double xdir = Math.cos(angle)*0.3;
            double ydir = Math.sin(angle)*0.3;

            double spdx = 2*xdir/0.3;
            double spdy = 2*ydir/0.3;

            double changev = (Math.random()*(2))-1;

            spdx = spdx + changev;
            spdy = spdy + changev;

            engine.setVelocities(spdx, spdy);
            System.out.println("Run with: " + spdx+" "+spdy+" on iteration number: "+iteration);
            
            for(int i=0; i<engine.get_ball_coordinatesX().length; i++) {
                allXCoords.add(engine.get_ball_coordinatesX()[i]);
                allYCoords.add(engine.get_ball_coordinatesY()[i]);
                allZCoords.add(engine.get_ball_coordinatesZ()[i]);
            }

            if(engine.isInHole()) {
                min = new StateVector(ballX,ballY,spdx,spdy);
                System.out.println("went in");
                return min;
            } else iteration++;

            prevBallx = ballX;
            prevBally = ballY;

            ballX = engine.get_ball_coordinatesX()[engine.get_ball_coordinatesX().length-1];
            ballY = engine.get_ball_coordinatesY()[engine.get_ball_coordinatesY().length-1];

            if(prevBallx == ballX && prevBally == ballY) {
                System.out.println("not moving");
                if(notMoving) {
                    sight=1;
                } else { 
                    sight=3;
                    notMoving=true;
                }
            } else notMoving=false;
    
        }
        return min;

    }

    public float[] getCoordsx() {
        for(int i=0; i<allXCoords.size(); i++) {
            coordsx[i] = allXCoords.get(i);
        }
        return coordsx;
    }

    public float[] getCoordsy() {
        for(int i=0; i<allYCoords.size(); i++) {
            coordsy[i] = allYCoords.get(i);
        }
        return coordsy;
    }

    public float[] getCoordsz() {
        for(int i=0; i<allZCoords.size(); i++) {
            coordsz[i] = allZCoords.get(i);
        }
        return coordsz;
    }

    public void setDead(Astar astar) {
        int xlength = (int)((terrainX2*10)+Math.abs(terrainX1*10));
        int ylength = (int)((terrainZ2*10)+Math.abs(terrainZ1*10));
        //Set trees to dead
        for(int i=0; i<xvaluesT.length; i++) {
            int x = (int)(xvaluesT[i]*10+Math.abs(terrainX1*10));
            int y = (int)(zvaluesT[i]*10+Math.abs(terrainZ1*10));
            astar.setDeadNode(x, y);
            astar.setDeadNode(x+1, y+1);
            astar.setDeadNode(x+1, y);
            astar.setDeadNode(x, y+1);
            astar.setDeadNode(x-1, y-1);
            astar.setDeadNode(x, y-1);
            astar.setDeadNode(x-1, y);
            astar.setDeadNode(x+1, y-1);
            astar.setDeadNode(x-1, y+1);
        }


        for(int x=0; x<xlength; x++) {
            for(int y=0; y<ylength; y++) {
                //Set dead node for walls
                for(int i=0; i<xvaluesW.length; i=i+2) {
                    if(x>=(xvaluesW[i]*10+Math.abs(terrainX1*10))&&x<=(xvaluesW[i+1]*10+Math.abs(terrainX1*10))&&y>=(zvaluesW[i]*10+Math.abs(terrainZ1*10))&&y<=(zvaluesW[i+1]*10+Math.abs(terrainZ1*10))) {
                        astar.setDeadNode(x, y);
                    }
                }
                //Set dead node for water
                if(function.getHeightFunction((x-Math.abs(terrainX1)*10)/10, (y-Math.abs(terrainZ1)*10)/10)<0) {
                    //System.out.println(x+" "+y);
                    astar.setDeadNode(x, y);
                }
            }
        }
    }

    public Node getStart() {
        int x = (int)((ballX*10)+Math.abs(terrainX1*10));
        int y = (int)((ballY*10)+Math.abs(terrainZ1*10));
        return new Node(x,y);
    }
    public Node getEnd() {
        int x = (int)((holeX*10)+Math.abs(terrainX1*10));
        int y = (int)((holeY*10)+Math.abs(terrainZ1*10));
        return new Node(x,y);
    }

    //Get all the values from search.java and put them into the appropriate variables
    public void getValues() {
        try {
            search = new Search("inputFile"+this.level+".txt");
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


        //Add all the different coordinates in one ArrayList
        coords.add(ballX);
        coords.add(ballY);
        coords.add(holeX);
        coords.add(holeY);

        
        for(int i=0; i<xvaluesSP.length; i++) {
            coords.add(xvaluesSP[i]);
            coords.add(zvaluesSP[i]);
        }

        for(int i=0; i<xvaluesT.length; i++) {
            coords.add(xvaluesT[i]);
            coords.add(zvaluesT[i]);
        }

        for(int i=0; i<xvaluesW.length; i++) {
            coords.add(xvaluesW[i]);
            coords.add(zvaluesW[i]);
        }
    }

    //Use the array which stores the coordinates to calculate the min and max of X and Y
    public void setTerrainCoords() {
        double biggestX=-1000000;
        double smallestX=1000000;
        double biggestY=-1000000;
        double smallestY=1000000;
        int offset = 3;
        for(int i=0; i<coords.size(); i++) {
            if(i%2==0) {
                if(coords.get(i)>biggestX) {
                    biggestX = coords.get(i);
                }
                if(coords.get(i)<smallestX) {
                    smallestX = coords.get(i);
                }
            } else {
                if(coords.get(i)>biggestY) {
                    biggestY = coords.get(i);
                }
                if(coords.get(i)<smallestY) {
                    smallestY = coords.get(i);
                }
            }
        }
        terrainX1 = (int) (smallestX-offset);
        terrainX2 = (int) (biggestX+offset);
        terrainZ1 = (int) (smallestY-offset);
        terrainZ2 = (int) (biggestY+offset);
    }

    public static void main(String[] args) throws IOException {
        physicsEngine testEngine = new physicsEngine(11);
        AstarBot abot = new AstarBot(testEngine,11);
        abot.calculateMove();

    }
}
