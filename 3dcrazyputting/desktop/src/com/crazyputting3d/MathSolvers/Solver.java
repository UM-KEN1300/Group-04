package com.crazyputting3d.MathSolvers;
import  com.crazyputting3d.Engine.physicsEngine;
import com.crazyputting3d.Objects.StateVector;

/**
 * Solver Abstract class. 
 * author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 * Gabrijel Radovčić, Elza Strazda
 * version 3.0
 * since   2022-05-11
 */

public abstract class Solver {
    /**
     * This is the abstract class for the mathmatical solvers.
     * It contains the gravity's constant and two method which need to be implemented.
     */
    public final double g = 9.81;
    protected physicsEngine engine;
    public Solver(physicsEngine engine){
        this.engine = engine;
    }
    public abstract StateVector run(StateVector v,double m);
}
