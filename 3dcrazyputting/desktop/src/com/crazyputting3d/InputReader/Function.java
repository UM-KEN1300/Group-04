package com.crazyputting3d.InputReader;
public class Function{
public double getHeightFunction(int level,double x, double y){
switch (level){
case 1: return 0;
case 2: return 0.4*(0.9-Math.exp(-(Math.pow(x,2)+Math.pow(y,2))/8));
case 3: return 0.4*(0.9-Math.exp(-(Math.pow(x,2)+Math.pow(y,2))/8));
case 4: return Math.cos(Math.cos(x*y/3)-8);
case 5: return 0.35*Math.sin(x)+0.45*Math.cos(y);
case 6: return 0.4*(0.9-Math.exp(-0.2*(Math.pow(x,2)+Math.pow(y,2))/8));
case 7: return 0.4*(0.9-Math.exp(-(Math.pow(x,2)+Math.pow(y,2))/8));
case 8: return 0;
case 9: return 0.4*(0.9-Math.exp(-(Math.pow(x,2)+Math.pow(y,2))/8));
case 10: return 0.4*(0.9-Math.exp(-(Math.pow(x,2)+Math.pow(y,2))/8));
case 11: return 0;
case 12: return 0;
case 13: return 0;
case 14: return 0;
case 15: return 0;
default: return 0;
}
}
}
