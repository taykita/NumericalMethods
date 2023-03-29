package org.example.panth.panth;

import static java.lang.Math.*;

public class GoldenRation {
    //    private static final double[] a = {8.3186, 6.0411, 16.8350, 18.9312, 5.1617};    private static final double[] a = {0, 0, -16.8350, 0, 5.1617};
    private static final double[] a = {-3.2129, 16.2999, 9.1366, -8.1953, -24.2977};
    static double kef1 = (3 - sqrt(5)) / 2;
    static double kef2 = (sqrt(5) - 1) / 2;

    static double epsilon = 0.0000001;

    private static double f(double x) {
        double res = 0;

//        for (int i = 0; i <= 4; i++) {
//            res += a[i] * pow(x, 4 - i);
//        }
        res = -3.2129*x*x -24.2977;
        return res;
    }

    public static void goldenRatio(double u1, double u2, double a, double b) {
        if (abs(a - b) > epsilon) {
            if (f(u1) > f(u2)) {
                b = u2;
                double v3 = a + kef1 * (b - a);

                if (u1 < v3) {
                    u2 = v3;
                } else {
                    u2 = u1;
                    u1 = v3;
                }
            } else {
                a = u1;
                double v3 = a + kef1 * (b - a);

                if (v3 < u2) {
                    u1 = v3;
                } else {
                    u1 = u2;
                    u2 = v3;
                }
            }
            System.out.println(a + " " + b);
            goldenRatio(u1, u2, a, b);

        } else {
            System.out.printf("u = %f, f(u) = %f", (u1 + u2) / 2, f((u1 + u2) / 2));
        }
    }

    public static void main(String[] args) {
        double a = -5;
        double b = 7;
        double u1 = a + kef1 * (b - a);
        double u2 = a + kef2 * (b - a);

        System.out.println(a + " " + b);
        goldenRatio(u1, u2, a, b);
    }
}