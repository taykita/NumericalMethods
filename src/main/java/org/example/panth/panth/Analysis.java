package org.example.panth.panth;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class Analysis {

    public static final int n = 5;
    private static final double eps = 0.00001;

    private static double J(double u) {
        return (u - n)*(u - n);
    }

    private static double z(double u) {
        return J(u) + 2*random() - 1;
    }

    private static double a(int n) {
        return 1.0/n;
    }

    private static double c(int n) {
        return 1.0/pow(n, 0.25);
    }

    private static double getU(double u, double a, double c) {
        return u - a/c * (z(u + c) - z(u - c));
    }

    public static void main(String[] args) {
        List<Double> us = new ArrayList<>();
        us.add(7.0);
        us.add(getU(us.get(0), a(1), c(1)));
        int i = 2;
        while (abs(us.get(us.size() - 1) - us.get(us.size() - 2)) > eps ) {
            us.add(getU(us.get(us.size() - 1), a(i), c(i)));
            i += 1;
        }

        System.out.printf("J(%.5f) = %.5f", us.get(us.size() - 1), J(us.get(us.size() - 1)));
    }
}
