package com.crazyputting3d;
import java.io.IOException;
import java.util.ArrayList;
import com.crazyputting3d.Objects.AdamsStateVector;
import com.crazyputting3d.Objects.Sandpit;
import com.crazyputting3d.Objects.StateVector;
import com.crazyputting3d.Objects.Tree;
import com.crazyputting3d.MathSolvers.RungeKutta4;
import com.crazyputting3d.MathSolvers.RungeKutta2;
import com.crazyputting3d.MathSolvers.AdamsBashforth;
import com.crazyputting3d.MathSolvers.AdamsMolton;
import com.crazyputting3d.MathSolvers.DormandPrince;
import com.crazyputting3d.MathSolvers.EulersMethod;
import com.crazyputting3d.MathSolvers.VerletsMethod;
import com.crazyputting3d.InputReader.Search;
import com.crazyputting3d.InputReader.cheat;

/**
 * The physics engine simulates the real-life motion of the ball in a golf
 * course.
 * author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen
 * van Gelder, Trinh Le,
 * Gabrijel Radovcic, Elza Strazda
 * version 1.0
 * since 2021-03-11
 */

public class physicsEngine {
    public static double h = 0.0001; // we decide on step size
    private double x0; // given in input file
    private double y0;
    private double xt;
    private double yt;
    private double r;
    private double muk;
    private double mus;
    private double muks;
    private double muss;
    private double boundXStart;
    private double boundXEnd;
    private double boundYStart;
    private double boundYEnd;
    public int counter = 0;
    public int speedCounter = 0;
    public final double g = 9.81;
    private Search search;
    public Tree[] tree_storage;
    public Sandpit[] sand_storage;
    public ArrayList<Double> coords = new ArrayList<Double>();
    public Double[] ball_coordinates_x = new Double[500000];
    public Double[] ball_coordinates_y = new Double[500000];
    private boolean flag = false;
    private double slopex;
    private double slopey;
    private AdamsStateVector initialVector;
    private double closestEuklidiandistance = Double.MAX_VALUE;
    private double initialDistance;
    private RungeKutta4 rk4 = new RungeKutta4(); 
    private RungeKutta2 rk2 = new RungeKutta2(); 
    private EulersMethod euler = new EulersMethod(); 
    private DormandPrince dormandP = new DormandPrince(); 
    private VerletsMethod verlets= new VerletsMethod(); 




    /**
     * Constructor initiates variables according to input file data. These include:
     * x and y starting position of the ball and hole, kinetic and static friction
     * for grass and sand.
     * Initiates a storage for all present sand pits and trees.
     * Sets terrain coordinates.
     */

    public physicsEngine() throws IOException {
        search = new Search("input.txt");
        this.x0 = search.get_x0();
        this.y0 = search.get_y0();
        coords.add(x0);
        coords.add(y0);
        this.xt = search.get_xt();
        this.yt = search.get_yt();
        this.r = search.get_r();
        this.muk = search.get_muk();
        this.mus = search.get_mus();
        tree_storage = this.tree_builder();
        sand_storage = this.sand_builder();
        this.muks = search.get_muks();
        this.muss = search.get_muss();
        for (int i = 0; i < sand_storage.length; i++) {
            coords.add(sand_storage[i].getXEnd());
            coords.add(sand_storage[i].getXStart());
            coords.add(sand_storage[i].getYEnd());
            coords.add(sand_storage[i].getYStart());
        }
        coords.add(xt);
        coords.add(yt);
        for (int i = 0; i < tree_storage.length; i++) {
            coords.add(tree_storage[i].getXStart());
            coords.add(tree_storage[i].getYStart());
        }
        setTerrainCoords();
        initialDistance = Math.hypot(x0-xt, y0-yt);
    }

    /**
     * setVelocities() updates the vector once the ball stops, and re-starts the
     * engine over again.
     * param new velocities v0x and v0y the ball will have once it is hit with the
     * stick.
     */

    public void setVelocities(double v0x, double v0y) {
        ball_coordinates_x = new Double[500000];
        ball_coordinates_y = new Double[500000];
        counter = 0;
        StateVector newv = new StateVector(x0, y0, v0x, v0y);
        start(newv, false);
    }

    public StateVector setVelocitiesForBot(double x, double y, double v0x, double v0y) {
        StateVector newv = new StateVector(x, y, v0x, v0y);
        closestEuklidiandistance = Math.hypot(x-xt, y-yt);
        return start(newv, true);
    }

    public double getXt(){
        return xt;
    }
    public double getYt(){
        return yt;
    }
    public double getrOfHole(){
        return r;
    }
    public double getX0(){
        return x0;
    }
    public double getY0(){
        return y0;
    }
    public double get_closestEuclideanDistance(){
        return closestEuklidiandistance;
    }

    /**
     * tree_builder() creates a storage of tree objects for all trees in the input
     * file.
     * returns the Tree storage as Tree[].
     */

    public Tree[] tree_builder() {
        double[] x = search.get_treeX();
        double[] y = search.get_treeY();
        double[] r = search.get_treeR();
        Tree[] tree_storage = new Tree[x.length];
        for (int i = 0; i < x.length; i++) {
            tree_storage[i] = new Tree(x[i], y[i], r[i]);
        }
        return tree_storage;
    }

    /**
     * sand_builder() creates a storage of sand pit objects for all sand pits in the
     * input file.
     * returns the Sand pit storage as SandPit[].
     */

    public Sandpit[] sand_builder() throws IOException {
        double[] x = search.get_sandPitX();
        double[] y = search.get_sandPitY();
        double muks_main = search.get_muks();
        double muss_main = search.get_muss();
        Sandpit[] sand_storage = new Sandpit[x.length / 2];
        int cnt = 0;
        for (int i = 0; i < x.length; i += 2) {
            sand_storage[cnt] = new Sandpit(x[i], x[i + 1], y[i], y[i + 1], muks_main, muss_main);
            cnt++;
        }
        return sand_storage;
    }

    /**
     * h() is used to get the height function value.
     * param values for x and y, to be input once calling cheat.getHeightFunction(x,
     * y).
     * returns the height function value according to x and y.
     */

    public double h(double x, double y) {
        cheat cheat = new cheat();
        return cheat.getHeightFunction(x, y);
    }

    /**
     * hxderivated() is used to get the derivative of the height function in terms
     * of x.
     * param values for x and y, to be input once calling h(x, y).
     * returns derivative of the function in terms of x.
     */

    public double hxderivated(double x, double y) {
        double dx = 0.000000000001;
        double derivative = (h(x + dx, y) - h(x, y)) / dx;
        return derivative;
    }

    /**
     * hyderivated() is used to get the derivative of the height function in terms
     * of y.
     * param values for x and y, to be input once calling h(x, y).
     * returns derivative of the function in terms of y.
     */

    public double hyderivated(double x, double y) {
        double dy = 0.000000000001;
        double derivative = (h(x, y + dy) - h(x, y)) / dy;
        return derivative;
    }

    /**
     * isFinish() determines whether the ball coordinates are within the range of
     * the hole coordinates.
     * param values for x and y coordinates of the ball.
     * returns true if it is within the hole range, and false if it is outside.
     */

    public boolean isFinish(double x, double y) {
        if (Math.sqrt((x - xt) * (x - xt) + (y - yt) * (y - yt)) < r) {
            flag = true;
            return true;
        } else {
            return false;
        }
    }

    /**
     * isInSandPit() determines whether the ball coordinates are within the range of
     * any of the sandpit coordinates.
     * param values for x and y coordinates of the ball.
     * Runs through the sand pit storage and for each sandpit it checks whether the
     * ball is inside or outside.
     * returns true if it is within any sandpit, and false if it is outside.
     */

    public boolean isInSandPit(double x, double y) {
        for (int i = 0; i < sand_storage.length; i++) {
            Sandpit sand = sand_storage[i];
            if (sand.isInSandPit(x, y)) {
                return true;
            }
        }
        return false;
    }

    /**
     * isInWater() determines whether the ball coordinates are in the water.
     * param values for x and y coordinates of the ball.
     * Gets the height function value for param x and y, and checks if it is smaller
     * than 0. If so, it means it is present in water.
     * returns true if the ball is in water, and false if it is not.
     */

    public boolean isInWater(double x, double y) {

        if (h(x, y) < 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * isInTree() determines whether the ball coordinates are within the range of
     * any of the tree coordinates.
     * param values for x and y coordinates of the ball.
     * Runs through the tree storage and for each tree it checks whether the ball is
     * within its range or not.
     * returns true if it is within any tree, and false if it is outside.
     */

    public boolean isInTree(double x, double y) {
        for (int i = 0; i < tree_storage.length; i++) {
            Tree tree = tree_storage[i];
            if (tree.isInTree(x, y)) {
                return true;
            }
        }
        return false;
    }

    /**
     * isInTerrain() determines whether the ball is within the bounds of the
     * terrain.
     * param values for x and y coordinates of the ball.
     * Checks if the the ball is in the range of boundXStart, boundXEnd,
     * boundYStart, boundYEnd.
     * returns true if it is in the terrain, and false if it is outside.
     */

    public boolean isInTerrain(double x, double y) {
        if (boundXStart < x && boundXEnd > x && boundYStart < y && boundYEnd > y) {
            return true;
        }
        return false;
    }

    /**
     * start() used to return the ball to initial position once it hits the water
     * param current vector state
     * returns initial vector state if in water, or current vector state otherwise.
     * 
     * @param <Calculable>
     * @param <EquationSolver>
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // for (i nt i = 0; i < 1000; i++) {
        StateVector vector = new StateVector(0, 0, 1, 0);
        physicsEngine engine = new physicsEngine();
        vector = engine.start(vector, true);
        double error = Math.abs((vector.getX() / 0.5096839959) - 1);
        System.out.println(vector.getX() + "," + error);
        // h = h + 0.0001;
        // }
    }

    public StateVector start(StateVector v, boolean botPlays) {
        Adams(v, muk);
        x0 = v.getX();
        y0 = v.getY();
        StateVector newV = trajectory(v, botPlays);
        if (newV == null) {
            if (!botPlays) {
                ball_coordinates_x[counter] = x0;
                ball_coordinates_y[counter] = y0;
                counter++;
            }
            return v;
        }
        if (!botPlays) {
            x0 = newV.getX();
            y0 = newV.getY();
            ball_coordinates_x[counter] = x0;
            ball_coordinates_y[counter] = y0;
            counter++;
            System.out.println(newV);
        }
        return newV;
    }




  
    public void Adams(StateVector a0, double m){
        StateVector a1;
        StateVector a2;
        StateVector a3;
        a1 = rk4.run(a0, m);
        a2 = rk4.run(a1, m);
        a3 = rk4.run(a2,m);

        // adams
        initialVector = new AdamsStateVector(a0,a1,a2,a3);
    }
    public StateVector AdamsBashforth(StateVector a, double m){
        StateVector a0;
        StateVector a1;
        StateVector a2;
        StateVector a3;
        a0 = initialVector.getFirst();
        a1 = initialVector.getSecond();
        a2 = initialVector.getThird();
        a3 = initialVector.getFourth();
        a = EulersMethod(a,m);
        if(terminates(a.getVX(),a.getVY(),m)){return a;}

        double ax0 = acelerationX(a0.getX(), a0.getY(), a0.getVX(), a0.getVY(), m);
        double ay0 = acelerationY(a0.getX(), a0.getY(), a0.getVX(), a0.getVY(), m);
        double ax1 = acelerationX(a1.getX(), a1.getY(), a1.getVX(), a1.getVY(), m);
        double ay1 = acelerationY(a1.getX(), a1.getY(), a1.getVX(), a1.getVY(), m);
        double ax2 = acelerationX(a2.getX(), a2.getY(), a2.getVX(), a2.getVY(), m);
        double ay2 = acelerationY(a2.getX(), a2.getY(), a2.getVX(), a2.getVY(), m);
        double ax3 = acelerationX(a3.getX(), a3.getY(), a3.getVX(), a3.getVY(), m);
        double ay3 = acelerationY(a3.getX(), a3.getY(), a3.getVX(), a3.getVY(), m);

        double x = a3.getX() + 1/ 24.0 * h * (55 * a3.getVX() - 59 * a2.getVX() + 37 * a1.getVX() - 9 * a0.getVX());
        double y = a3.getY() + 1/ 24.0 * h * (55 * a3.getVY() - 59 * a2.getVY() + 37 * a1.getVY() - 9 * a0.getVY());
        double vx = a3.getVX() + 1/ 24.0 * h * (55 * ax3 - 59 * ax2 + 37 * ax1 - 9 * ax0);
        double vy = a3.getVY() + 1/ 24.0 * h * (55 * ay3 - 59 * ay2 + 37 * ay1 - 9 * ay0);
        StateVector a4 = new StateVector(x,y,vx,vy);
        a4 = AdamsMolton(a0,a1,a2,a3,a4,m);
        return a4;
    }
    public StateVector AdamsMolton(StateVector a0, StateVector a1, StateVector a2, StateVector a3 ,StateVector a4, double m){
        double ax0 = acelerationX(a0.getX(), a0.getY(), a0.getVX(), a0.getVY(), m);
        double ay0 = acelerationY(a0.getX(), a0.getY(), a0.getVX(), a0.getVY(), m);
        double ax1 = acelerationX(a1.getX(), a1.getY(), a1.getVX(), a1.getVY(), m);
        double ay1 = acelerationY(a1.getX(), a1.getY(), a1.getVX(), a1.getVY(), m);
        double ax2 = acelerationX(a2.getX(), a2.getY(), a2.getVX(), a2.getVY(), m);
        double ay2 = acelerationY(a2.getX(), a2.getY(), a2.getVX(), a2.getVY(), m);
        double ax3 = acelerationX(a3.getX(), a3.getY(), a3.getVX(), a3.getVY(), m);
        double ay3 = acelerationY(a3.getX(), a3.getY(), a3.getVX(), a3.getVY(), m);
        double ax4 = acelerationX(a4.getX(), a4.getY(), a4.getVX(), a4.getVY(), m);
        double ay4 = acelerationY(a4.getX(), a4.getY(), a4.getVX(), a4.getVY(), m);

        double x = a3.getX() + 1/ 720.0 * h * (251*a4.getVX() + 646 * a3.getVX() - 264 * a2.getVX() + 106 * a1.getVX() - 19 * a0.getVX());
        double y = a3.getY() + 1/ 720.0 * h * (251*a4.getVY() + 646 * a3.getVY() - 264 * a2.getVY() + 106 * a1.getVY() - 19 * a0.getVY());
        double vx = a3.getVX() + 1/ 720.0 * h * (251*ax4 + 646 * ax3 - 264 * ax2 + 106 * ax1 - 19 * ax0);
        double vy = a3.getVY() + 1/ 720.0 * h * (251*ay4 + 646 * ay3 - 264 * ay2 + 106 * ay1 - 19 * ay0);
        StateVector a5 = new StateVector(x,y,vx,vy);
        initialVector.setFirst(a1);
        initialVector.setSecond(a2);
        initialVector.setThird(a3);
        initialVector.setFourth(a5);
        return a5;
    }


    public StateVector EulersMethod(StateVector a, double m) {
        double x = a.getX(); // x(n+1) = x(n) + h*x(n) derivated
        double y = a.getY(); // y(n+1) = y(n) + h*y(n) derivated
        double vx = a.getVX(); // vx(n+1) = vx(n) + h*vx(n) derivated
        double vy = a.getVY(); // vy(n+1) = vy(n) + h*vy(n) derivated
        a.setX(h * vx + x);
        a.setY(h * vy + y);
        double ax = acelerationX(x, y, vx, vy, m);
        double ay = acelerationY(x, y, vx, vy, m);
        a.setVX(vx + h * ax);
        a.setVY(vy + h * ay);
        return a;
    }

    public double acelerationX(double x, double y, double vx, double vy, double m) {
        return -g * hxderivated(x, y) - m * g * vx / (Math.sqrt(vx * vx + vy * vy));
    }

    public double acelerationY(double x, double y, double vx, double vy, double m) {
        return -g * hyderivated(x, y) - m * g * vy / (Math.sqrt(vx * vx + vy * vy));
    }

    /**
     * EulersMethod() recursively calculates final positions of the ball by using
     * the well-known Euler's method.
     * param current Vector.
     * returns final vector.
     */

    public StateVector trajectory(StateVector v, boolean botPlays) {
        while (true) {
            double m = 0;
            double ms = 0;
            double x = v.getX();
            double y = v.getY();
            double vx = v.getVX();
            double vy = v.getVY();

            if (!botPlays) {
                int velocity = (int) Math.sqrt(vx * vx + vy * vy);
                if (speedCounter % ((0.005 / h) * (velocity + 5)) == 0) {
                    ball_coordinates_x[counter] = x;
                    ball_coordinates_y[counter] = y;
                    counter++;
                }
                speedCounter++;
            }
            if (isFinish(x, y)) {
                return v;
            } else if (isInSandPit(x, y)) {
                m = muks;
                ms = muss;
            } else {
                m = muk;
                ms = mus;
            }
            v = rk4.run(v,m);
            if(botPlays){
                slopex = slopex + hxderivated(v.getX(), v.getY()) * h/(m*g)+h/2;
                slopey = slopey + hyderivated(v.getX(), v.getY()) * h/(m*g)+h/2;
            }
            if(botPlays&&(closestEuklidiandistance>Math.hypot(v.getX()-xt,v.getY()-yt))){
                closestEuklidiandistance = Math.hypot(v.getX()-xt,v.getY()-yt);
            }
            if(botPlays&&(closestEuklidiandistance==initialDistance)){
                closestEuklidiandistance = Math.hypot(v.getX()-xt,v.getY()-yt)+Math.hypot(x-xt,y-yt);
            }
            if (isInWater(v.getX(), v.getY())) {
                if (!botPlays) {
                    ball_coordinates_x[counter] = x;
                    ball_coordinates_y[counter] = y;
                    counter++;
                }
                return null;
            }

            double staticFriction = Math.sqrt(Math.pow(hxderivated(v.getX(), v.getY()), 2) + Math.pow(hyderivated(v.getX(), v.getY()), 2));
            if (terminates(vx, vy, m)) {
                v.setX(x + v.getVX() * h);
                v.setY(y + v.getVY() * h);
                if (ms > staticFriction) {
                    if (!botPlays) {
                        ball_coordinates_x[counter] = x;
                        ball_coordinates_y[counter] = y;
                        counter++;
                    }
                    return v;
                }
            }
            if (isInTree(v.getX(), v.getY())) {
                if (!botPlays) {
                    ball_coordinates_x[counter] = x;
                    ball_coordinates_y[counter] = y;
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
                    counter++;
                }
                v.setVX(h);
                v.setVY(h);
                v.setX(x);
                v.setY(y);
                return v;
            }

        }
    }

    public boolean terminates(double vx, double vy, double m) {
        if ((vx < h && vx > -h ) && (vy < h && vy > -h )){
            return true;
        }
        return false;
    }

    /**
     * get_ball_coordinatesX() stores all x coordinates of the ball from start to
     * end to be retrieved by game3d.
     * returns float[] array of all ball x-coordinates.
     */

    public float[] get_ball_coordinatesX() {
        ArrayList<Double> words_x = new ArrayList<>();
        for (int i = 0; i < ball_coordinates_x.length; i++) {
            if (ball_coordinates_x[i] != null) {
                words_x.add(ball_coordinates_x[i]);
            }
        }
        float[] temp_FINAL = new float[words_x.size()];
        for (int s = 0; s < temp_FINAL.length; s++) {
            double d = words_x.get(s);
            temp_FINAL[s] = (float) d;
        }
        return temp_FINAL;
    }

    /**
     * get_ball_coordinatesY() stores all y coordinates of the ball from start to
     * end to be retrieved by game3d.
     * returns float[] array of all ball y-coordinates.
     */

    public float[] get_ball_coordinatesY() {
        ArrayList<Double> words_y = new ArrayList<>();
        for (int i = 0; i < ball_coordinates_y.length; i++) {
            if (ball_coordinates_y[i] != null) {
                words_y.add(ball_coordinates_y[i]);
            }
        }
        float[] temp_FINAL = new float[words_y.size()];
        for (int s = 0; s < temp_FINAL.length; s++) {
            double d = words_y.get(s);
            temp_FINAL[s] = (float) d;
        }
        return temp_FINAL;
    }
 
    /**
     * isInHole() determines whether the ball is in the hole or not.
     * returns flag.
     */

    public boolean isInHole() {
        return flag;
    }

    /**
     * getTrees() is used to access tree_storage easily from game3d.
     * returns tree_storage.
     */

    public Tree[] getTrees() {
        return tree_storage;
    }

    /**
     * Use the array which stores the coordinates to calculate the min and max of X
     * and Y.
     */

    public void setTerrainCoords() {
        double biggestX = -1000000;
        double smallestX = 1000000;
        double biggestY = -1000000;
        double smallestY = 1000000;
        int offset = 1;
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
        boundXStart = (float) (smallestX - offset);
        boundXEnd = (float) (biggestX + offset);
        boundYStart = (float) (smallestY - offset);
        boundYEnd = (float) (biggestY + offset);
    }

    public double getSlopex() {
        return slopex;
    }

    public double getSlopey() {
        return slopey;
    }
}