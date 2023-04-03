package org.example.panth.panth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Tangent {    //f\left(x\right)=0.0806x^{4}+12.1478x^{3}+17.5999x^{2}+64.4501x+202.8204\left\{7\le x\le9\right\}
    private static final double[] koe = {0.0806, 12.1478, 17.5999, 64.4501, 202.8204};
    private static final double[] dkoe = {0.3224, 36.4434, 35.1998, 64.4501};

    private static final double epsilon = 0.0001;

    private static double f(double x) {
        double res = 0;
        double l = koe.length - 1;

        for (int i = 0; i <= l; i++) {
            res += koe[i] * pow(x, l - i);
        }
        return res;
    }

    private static double df(double x) {
        double res = 0;
        double l = dkoe.length - 1;

        for (int i = 0; i <= l; i++) {
            res += dkoe[i] * pow(x, l - i);
        }
        return res;
    }

    private static double func(double a, double b) {
        return (f(a) - f(b) + b * df(b) - a * df(a)) / (df(b) - df(a));
    }

    public static void main(String[] args) {
        double a = 5;
        double b = 7;
        double u;

        do {
            u = func(a, b);

            if (df(u) > 0) {
                b = u;
            } else {
                a = u;
            }
        } while (b - a > epsilon);

        System.out.printf("u = %f, f(u) = %f\n", u, f(u));
    }
}