package com.company;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    private double t0;
    private double t1;
    private double dt;
    private double x0;
    private double v0;
    private double m;
    protected double omega;

    public Converter(double t0, double t1, double dt, double x0, double v0, double m, double omega) {
        this.t0 = t0;
        this.t1 = t1;
        this.dt = dt;
        this.x0 = x0;
        this.v0 = v0;
        this.m = m;
        this.omega = omega;
    }
    public Converter(){}
    public List<Double> computeOscillations(String springExpr) {
        Spring s = SpringArray.equivalentSpring(springExpr);
        return s.move(t0, t1, dt, x0, v0, m);
    }

    public int ampl(List<Double> coords) {
        return FT.Amplitude(coords, dt, 1000,omega);
    }
}



