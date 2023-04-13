package com.company;

public class Spring {
    private double k;

    public Spring(){
        this.k = 1.0;
    }

    public Spring(double k){
        this.k = k;
    }

    public double getStiffness(){
        return this.k;
    }

    public void setStiffness(double k){
        this.k = k;
    }

    public double[] move(double t, double dt, double x0, double v0){
        int n = (int) Math.ceil(t/dt);
        double[] x = new double[n];
        double[] v = new double[n];

        x[0] = x0;
        v[0] = v0;

        for(int i = 1; i < n; i++){
            double val = x[i-1]*(-this.k);
            x[i] = x[i-1] + v[i-1]*dt;
            v[i] = v[i-1] + val*dt;
        }
        return x;
    }

    public double[] move(double t, double dt, double x0){
        return move(t,dt,x0,0.0);
    }

    public double[] move(double t0, double tl, double dt, double x0, double v0){
        //TODO
    }

    public double[] move(double t0, double tl, double dt, double x0, double v0, double m){
        //TODO
    }




}
