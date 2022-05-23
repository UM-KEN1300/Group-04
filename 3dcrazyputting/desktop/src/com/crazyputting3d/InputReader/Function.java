package com.crazyputting3d.InputReader;
public class Function{
public double getHeightFunction(double x, double y){
 return Math.exp(-(x*x+y*y)/40);
}
}
