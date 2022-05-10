package com.crazyputting3d.Objects;

/**
 * Adams State Vector object.
 * author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 *          Gabrijel Radovčić, Elza Strazda
 * version 1.0
 * since   2022-05-11
 */

public class AdamsStateVector {

    StateVector zero;
    StateVector one;
    StateVector two;
    StateVector three;

    /**
     * Constructor assigns each StateVector an x-position, y-position, x-velocity and y-velocity.
     */

    public AdamsStateVector(StateVector zero , StateVector one, StateVector two, StateVector three){
        this.zero = zero;
        this.one = one;
        this.two = two;
        this.three = three;
    }

    /**
     * Setters and getters.
     */

    public StateVector getFirst(){return zero;}
    public StateVector getSecond(){
        return one;
    }
    public StateVector getThird(){
        return two;
    }
    public StateVector getFourth(){
        return three;
    }
    public void setFirst(StateVector zero){
        this.zero = zero;
    }
    public void setSecond(StateVector one){this.one = one;}
    public void setThird(StateVector two){
        this.two = two;
    }
    public void setFourth(StateVector three){
        this.three = three;
    }

    /**
     * toString() returns StateVector in a string format for testing purposes.
     */

    public String toString(){
        return zero+"||"+one+"||"+two+"||"+three;
    }
}

