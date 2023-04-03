package org.example.panth.panth;

import static java.lang.Math.sqrt;
//J(u)=-(-8.1953*u*u+16.2999\cdot u+24.2977)/(9.1366*9.1366+3.2129*u*u*u*u)

public class ParabolicMethod {
//    public static void main(String[] args) {
//        double a = -2.0;
//        double b = 2.0;
//        double eps = 1e-6;
//        double x1 = a;
//        double x2 = (a + b) / 2;
//        double x3 = b;
//        double f1 = J(x1);
//        double f2 = J(x2);
//        double f3 = J(x3);
//        double x_min;
//        double f_min;
//        int iter = 0;
//
//        do {
//            double a1 = (f2 - f1) / (x2 - x1);
//            double a2 = ((f3 - f1) / (x3 - x1) - (f2 - f1) / (x2 - x1)) / (x3 - x2);
//            x_min = 0.5 * (x1 + x2 - a1 / a2);
//            f_min = J(x_min);
//            if (x_min > x2) {
//                if (f_min < f2) {
//                    x1 = x2;
//                    x2 = x_min;
//                    f1 = f2;
//                    f2 = f_min;
//                } else {
//                    x3 = x_min;
//                    f3 = f_min;
//                }
//            } else {
//                if (f_min < f2) {
//                    x3 = x2;
//                    x2 = x_min;
//                    f3 = f2;
//                    f2 = f_min;
//                } else {
//                    x1 = x_min;
//                    f1 = f_min;
//                }
//            }
//            iter++;
//            System.out.format("u = %.6f\n", x_min);
//            System.out.format("J(u) = %.6f\n", f_min);
//        } while (Math.abs(x3 - x1) > eps);
//
//        System.out.println("Минимум функции J(u) = (-8.1953*u*u + 16.2999+24.2977)/(9.1366*9.1366 + 3.2129*u*u*u*u) на отрезке [-2; 2]:");
//        System.out.format("u = %.6f\n", x_min);
//        System.out.format("J(u) = %.6f\n", f_min);
//        System.out.format("Количество итераций: %d\n", iter);
//    }
//
//    public static double J(double u) {
//        return (-8.1953 * u * u + 16.2999*u + 24.2977) / (9.1366 * 9.1366 + 3.2129 * u * u * u * u);
//    }

    public static void main(String[] args) {
//        squareMethod();
        cubeMethod();
    }

    private static void cubeMethod() {
        double x1 = -2;
        double x2 = 2;
        double eps = 1e-6;
        double h = 0.01;
        double x3 = (x1 + x2) / 2;
        x1 = x3;
        double D1 = fs(x1);
        if (D1 > 0) {
            h = -h;
        }
        x2 = x1 + h;
        double D2 = fs(x2);
        if ((D2 - D1) / h < 0) {
            System.out.println("ПОШЕЛ НАХУЙ");
        }
        double y1, y2, y3;
        double zm;

        y1 = f(x1);
        y2 = f(x2);

        do {
            double z1 = x1 - x2;
            double p = (D1 - D2 - 2 * (y1 - y2 - D2 * z1) / z1) / (z1 * z1);
            double q = (D2 - D1 + 3 * (y1 - y2 - D2 * z1)/z1)/z1;
            double r = D2;
            if (q*q - 3*p*r < 0) {
                zm = -q/(3*p);
            } else {
                zm = (-q + sqrt(q*q - 3*p*r))/(3*p);
            }

            x1 = x2;
            y1 = y2;
            D1 = D2;
            x2 = x2 + zm;
            y2 = f(x2);
            D2 = fs(x2);

        } while (Math.abs(zm) > eps);

        double min = (f(x1) + f(x2)) / 2;

        System.out.println("Минимум функции: " + min);
        System.out.printf("%s - %s", x1, x2);
    }

    private static void squareMethod() {
        double x1 = -2;
        double x2 = 2;
        double eps = 1e-6;

        double x3 = (x1 + x2) / 2;
        double y1, y2, y3;
        double zm;

        y1 = f(x1);
        y2 = f(x2);
        y3 = f(x3);

        do {
            double z1 = x1 - x3;
            double z2 = x2 - x3;

            double p = ((y1 - y3) * z2 - (y2 - y3) * z1) / (z1 * z2 * (z1 - z2));
            double q = ((y1 - y3) * z2 * z2 - (y2 - y3) * z1 * z1) / (z1 * z2 * (z2 - z1));
            double r = y3;
            zm = -q / (2 * p);

            x1 = x2;
            y1 = y2;
            x2 = x3;
            y2 = y3;
            x3 = x3 + zm;
            y3 = f(x3);

        } while (Math.abs(zm) > eps);

        double min = (f(x1) + f(x2) + f(x3)) / 3;

        System.out.println("Минимум функции: " + min);
    }

    public static double f(double u) {
        return -(-8.1953 * u * u + 16.2999 * u + 24.2977) / (9.1366 * 9.1366 + 3.2129 * u * u * u * u);
    }

    public static double fs(double u) {
        double var = 9.1366 * 9.1366 + 3.2129 * u * u * u * u;
        return -(-16.3906 * u + 16.2999) * var - (-8.1953 * u * u + 16.2999 * u + 24.2977) * (4 * 3.2129 * u * u * u) / (var * var);
    }
}

//import java.util.function.Function;
//
//public class ParabolicMethod {
//    public static void main(String[] args) {
//        double a = -2;
//        double b = 2;
//        double e = 0.001;
//
//        Function<Double, Double> func = u -> (-8.1953 * u * u + 16.2999 * u + 24.2977) / (9.1366 * 9.1366 + 3.2129 * u * u * u * u);
//
//        double x1 = a + (b - a) / 3;
//        double x2 = b - (b - a) / 3;
//        double x3 = (x1 + x2) / 2;
//
//        double f1 = func.apply(x1);
//        double f2 = func.apply(x2);
//        double f3 = func.apply(x3);
//
//        double xm = (x1 + x2) / 2;
//        double fm = func.apply(xm);
//
//        while (Math.abs(x2 - x1) >= e) {
//
//            double a0 = f1;
//            double a1 = (f2 - f1) / (x2 - x1);
//            double a2 = ((f3 - f1) / (x3 - x1) - a1) / (x3 - x2);
//
//            double u = 0.5 * (x1 + x2 - a1 / a2);
//            double fu = func.apply(u);
//
//            if (fu < fm) {
//                xm = u;
//                fm = fu;
//            }
//            if (u < xm) {
//                x2 = u;
//                f2 = fu;
//            } else {
//                x1 = xm;
//                f1 = fm;
//                xm = u;
//                fm = fu;
//            }
//        }
//        System.out.println("Minimum is at u = " + xm);
//        System.out.println("Minimum value is " + fm);
//    }
//
//    private static double calculateJ(double u) {
//        return (-8.1953 * u * u + 16.2999 * u + 24.2977) / (9.1366 * 9.1366 + 3.2129 * u * u * u * u);
//    }
//}