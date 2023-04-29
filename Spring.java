package com.company;

import java.util.ArrayList;
import java.util.List;

public class Spring{
    private double k;

    public Spring(){
        setK(1.0);
    }

    public Spring(double k){
        setK(k);
    }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    public double[] move(double t, double dt, double x0, double v0){
        int size = (int)(t/dt)+1;
        double[] coordinates = new double[size];
        double o = Math.sqrt(k);

        for(int i = 0; i <= t; i+= dt){
            double xi = x0 * Math.cos(o * (double)(i)) + ((v0/o) * Math.sin(o*(double)i));
            coordinates[i] = xi;
        }
        return coordinates;
    }

    public double[] move(double t, double dt, double x0){
        int size = (int)(t/dt)+1;
        double[] coordinates = new double[size];
        double o = Math.sqrt(k);

        for(int i = 0; i <= t; i+= dt){
            double xi = x0 * Math.cos(o * (double)(i));
            coordinates[i] = xi;
        }
        return coordinates;
    }

    public double[] move(double t0, double t1, double dt, double x0, double v0){
        int size = (int)((t1-t0)/dt) + 1;
        double[] coordinates = new double[size];
        double o = Math.sqrt(k);
        int i = 0;

        for(double t = t0; t <= t1; t += dt){
            double xt = (x0 * Math.cos(o*t)) + ((v0/o)*Math.sin(o*t));
            coordinates[i] = xt;
            i++;
        }
        return coordinates;
    }

    public List<Double> move(double t0, double t1, double dt, double x0, double v0, double m){
        List<Double> coordinates = new ArrayList<>();
        double o = Math.sqrt(k/m);
        int i = 0;

        for(double t = t0; t <= t1; t += dt){
            double xt = (x0 * Math.cos(o*t)) + ((v0/o)*Math.sin(o*t));
            coordinates.set(i, xt);
            i++;
        }
        return coordinates;
    }

    @Override
    public String toString() {
        return Double.toString(k);
    }
}

