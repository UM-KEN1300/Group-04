package com.crazyputting3d.Bots;
import com.crazyputting3d.Objects.StateVector;
import com.crazyputting3d.physicsEngine;

/**
 * Brute Force Bot class. 
 * author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 *          Gabrijel Radovčić, Elza Strazda
 * version 1.0
 * since   2022-05-11
 */

public class BruteForceBot extends Bot{

    public BruteForceBot(physicsEngine engine) {
        super(engine);
        System.out.println("brute bot");
    }

    public StateVector calculateMove() {
        double angle = Math.acos(Math.abs(xt - x0) / Math.sqrt(Math.pow(x0 - xt, 2) + Math.pow(y0 - yt, 2)));
        double euklidianDistance = Double.MAX_VALUE;
        double minVx = Double.MAX_VALUE;
        double minVy = Double.MAX_VALUE;
        StateVector min = new StateVector(x0, y0, minVx, minVy);
        for (double v = 5; v >= 0; v -= 0.01) {
            for (int i = 0; i < 180; i++) {
                for (int j = 0; j < 2; j++) {
                    double newangle = angle;
                    System.out.println(v);
                    if (j == 0) {
                        newangle = angle + i * pi / 180.0;
                    } else {
                        newangle = angle - i * pi / 180.0;
                    }
                    double vx = Math.cos(newangle) * v;
                    double vy = Math.sin(newangle) * v;
                    StateVector temp = engine.setVelocitiesForBot(x0, y0, vx, vy);
                    double tempEuclidianDistance = Math
                            .sqrt(Math.pow(temp.getX() - xt, 2) + Math.pow(temp.getY() - yt, 2));
                    if (tempEuclidianDistance < radius) {
                        min.setVX(vx);
                        min.setVY(vy);
                        System.out.println(v);
                        return min;
                    }
                    if (tempEuclidianDistance < euklidianDistance) {
                        min = temp;
                        min.setVX(vx);
                        min.setVY(vy);
                        euklidianDistance = tempEuclidianDistance;
                    }
                }
            }
        }
        return min;
    }
}
