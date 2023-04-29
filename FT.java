package com.company;

import java.util.ArrayList;
import java.util.List;


public class FT {
    public static double a(List<Double> coordinates, double omega, double dt, int j, int N) {
        double sum = 0;
        for (int n = 0; n < N; n++) {
            sum += coordinates.get(n) * Math.cos(j * omega * n * dt);
        }
        return 2.0 / N * sum;
    }

    public static double b(List<Double> coordinates, double omega, double dt, int j, int N) {
        double sum = 0;
        for (int n = 0; n < N; n++) {
            sum += coordinates.get(n) * Math.sin(j * omega * n * dt);
        }
        return 2.0 / N * sum;
    }

    public static int Amplitude(List<Double> coordinates, double dt, int N, double omega) {
        List<Double> a = new ArrayList<>();
        List<Double> b = new ArrayList<>();
        double ampl = 0;
        int k = 0;

        for (int j = 0; j < coordinates.size(); j++) {
            a.add(a(coordinates, omega, dt, j, N));
            b.add(b(coordinates, omega, dt, j, N));
            System.out.println("a" + j + ": " + a.get(j));
            System.out.println("b" + j + ": " + b.get(j));

            if (Math.sqrt(Math.pow(a.get(j), 2) + Math.pow(b.get(j), 2)) > ampl) {
                ampl = Math.pow(a.get(j), 2) + Math.pow(b.get(j), 2);
                k = j;
            }
        }
        return k;
    }
}
