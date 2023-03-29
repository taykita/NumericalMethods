package org.example.panth.panth;

public class BrokenLine {
    private static final double a = 0.0806;
    private static final double b = 12.1478;
    private static final double c = 17.5999;
    private static final double d = 64.4501;
    private static final double e = 202.8204;

    private static final double L = 10000;

    private static final double epsilon = 0.00001;

    private static double J(double u) {
        double res = d * Math.sin(b * u + e) + c * Math.exp(a * u);
        return -res;
    }

    private static double g(double u, double v) {
        return J(v) - L * Math.abs(u - v);
    }

    private static double p(double u, double u0, double u1) {
        return Math.max(g(u, u0), g(u, u1));
    }

    public static void main(String[] args) {
        double xOpt = minJ(0, 5);
        System.out.printf("u = %f, f(u) = %f\n", xOpt, J(xOpt));
    }

    private static double minJ(double a, double b) {
        double xOpt = (J(a) - J(b) + L * (a + b)) / (2 * L);
        double p = (J(a) + J(b) + L * (a - b)) / 2;

        double delta = (J(xOpt) - p) / (2 * L);
        while ((2 * L * delta) > epsilon) {
            double x1 = xOpt - delta;
            double x2 = xOpt + delta;

            if (J(x1) < J(x2)) {
                xOpt = x1;
            } else {
                xOpt = x2;
            }
            p = (J(xOpt) + p) / 2;
            delta = (J(xOpt) - p) / (2 * L);
        }
        return xOpt;
    }
}